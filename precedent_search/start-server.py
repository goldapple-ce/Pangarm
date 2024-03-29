import json
from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col,lit,max,desc
from pyspark.sql.types import StructType, StructField, IntegerType, ArrayType, StringType,DoubleType
from pyspark.sql.functions import udf
import numpy as np
from urllib.parse import urlparse, parse_qs

import http.server
import socketserver
from http.server import BaseHTTPRequestHandler, HTTPServer
import json
from langchain.embeddings import HuggingFaceEmbeddings
model_name = "jhgan/ko-sroberta-multitask"  # (KorNLU 데이터셋에 학습시킨 한국어 임베딩 모델)
model_kwargs = {'device': 'cpu'}
encode_kwargs = {'normalize_embeddings': False}
embedding_model = HuggingFaceEmbeddings(
    model_name=model_name,
    model_kwargs=model_kwargs,
    encode_kwargs=encode_kwargs
)


def cos_sim(v1, v2):
    """두 벡터 v1, v2의 코사인 유사도를 계산합니다."""
    dot_product = np.dot(v1, v2)
    norm_v1 = np.linalg.norm(v1)
    norm_v2 = np.linalg.norm(v2)
    return int(dot_product / (norm_v1 * norm_v2)*100) + 100 //2

cos_sim_udf = udf(cos_sim,IntegerType())
spark = SparkSession.builder.appName("precedent-start").getOrCreate()
df = spark.read.parquet("/data/precedent")
print(df.count())
df  = df.repartition(14) #가용 노드의 갯수만큼 재 파티셔닝 진행하여 최적화
df.cache()

def search(request):
    
    content = request['content']
    count = request['count']
    vector = embedding_model.embed_query(content)
    pandasDF = df.withColumn("score",cos_sim_udf(lit(vector), df["vector"])) \
        .sort(col("score").desc()).select(
        col("score"),
        col("info"),
        col("summary"),
        # col("judgement"),
        #col("conclusion"),
        col("keywordList")).limit(count).toPandas()
    response = {"data":[]}
    for i in range(len(pandasDF)):
        object ={}
        object["score"] = int(pandasDF["score"][i])
        object["info"] = json.loads(pandasDF["info"][i])
        object["summary"] = pandasDF["summary"][i]
        object["keywordList"] = pandasDF["keywordList"][i]
        response['data'].append(object)

    return response

    


def detail(caseNumber):
    choice = df.where((col("caseNumber") == caseNumber))\
            .select(df.info,
                    df.relate,
                    df.body,
                    df.isPdf
                )
    if choice.count() == 0:
        return None
    
    pandasDF = choice.toPandas()
    response = {}
    response["info"] = json.loads(pandasDF["info"][0])
    response["relate"] = json.loads(pandasDF["relate"][0])
    response["body"] = pandasDF["body"][0]
    response["isPdf"] = json.loads(pandasDF["isPdf"][0])
    return response
    

def summary(caseNumber):
    choice = df.where((col("caseNumber") == caseNumber))\
            .select(df.info,
                    df.relate,
                    df.parties,
                    df.originalJudgement,
                    df.purport,
                    df.opinion,
                    df.fact,
                    df.judgement,
                    df.conclusion,
                    col("summary"),
                    df.keywordList
                )
    if choice.count() == 0:
        return None
    
    pandasDF = choice.toPandas()
    response = {}
    response["info"] = json.loads(pandasDF["info"][0])
    response["relate"] = json.loads(pandasDF["relate"][0])
    response["parties"] = json.loads(pandasDF["parties"][0])
    response["originalJudgement"] = json.loads(pandasDF["originalJudgement"][0])
    response["purport"] = pandasDF["purport"][0]
    response["fact"] = pandasDF["fact"][0]
    response["relate"] = json.loads(pandasDF["relate"][0])
    response["opinion"] = json.loads(pandasDF["opinion"][0])
    response["judgement"] = pandasDF["judgement"][0]
    response["conclusion"] = pandasDF["conclusion"][0]
    response["summary"] = pandasDF["summary"][0]
    return response

class MyRequestHandler(BaseHTTPRequestHandler):
    INVALID_400_REQUEST = {"code": "P002","message": "잘못된 요청입니다."}
    INVALID_404_PAGE_REQUEST = {"code": "P001","message" : "페이지를 찾을수 없습니다."}
    INVALID_404_PRECEDENT_REQUEST = {"code": "P003","message" : "해당 판례를 찾을수 없습니다."}

    
    def do_POST(self):
        if self.path == '/api/precedent/search':
            # 요청의 길이를 확인하여 데이터를 읽음
            content_length = int(self.headers['Content-Length'])
            post_data = self.rfile.read(content_length)

            
            # 요청된 데이터 처리
            try:
                # POST 요청으로 전달된 데이터를 디코딩
                request = json.loads(post_data.decode('utf-8'))
                if not "content" in request:
                    self.return_404_error()
                    return
                if not "count" in request:
                    request["count"] = 4
                response = search(request)
                self.return_response(200,response)
            
            except Exception as ex:
                # JSON 디코딩 오류가 발생한 경우
                self.return_400_error()
            
        else:
            self.return_404_page_error()

    def do_GET(self):
        parsed_url = urlparse(self.path)
        query_params = parse_qs(parsed_url.query)

 
        path = parsed_url.path
        if path == "/api/precedent/summary" :
            caseNumber = query_params.get('caseNumber', [None])[0]  # None을 기본값으로 사용
            if caseNumber == None : 
                self.return_400_error()
                return
            response = summary(caseNumber)
            if(response == None):
                self.return_404_PRECEDENT_error()
                return

            self.return_200_response(response)
        elif path == "/api/precedent/detail":
            caseNumber = query_params.get('caseNumber', [None])[0]  # None을 기본값으로 사용
            if caseNumber == None : 
                self.return_400_error()
                return
            
            
            response = detail(caseNumber)
            if(response == None):
                self.return_404_PRECEDENT_error()
                return
            self.return_200_response(response)
            
        else:
            self.return_404_page_error()

    def return_response(self,code,response):
        self.send_response(code)
        self.send_header('Content-type', 'application/json')
        self.end_headers()
        
        self.wfile.write(json.dumps(response,ensure_ascii=False).encode("utf-8"))

    def return_200_response(self,response):
        self.return_response(200,response)

    def return_404_page_error(self):
        self.return_response(404,self.INVALID_404_PAGE_REQUEST)

    def return_404_PRECEDENT_error(self):
        self.return_response(404,self.INVALID_404_PRECEDENT_REQUEST)

    def return_400_error(self):
        self.return_response(400,self.INVALID_400_REQUEST)

            


def run_server(port=8787):
    server_address = ('', port)
    httpd = HTTPServer(server_address, MyRequestHandler)
    print(f'HTTP 서버가 시작되었습니다. 포트 {port} 번에서 대기 중...')
    httpd.serve_forever()

run_server()