from rest_framework.response import Response
from rest_framework import status
from langchain.embeddings import HuggingFaceEmbeddings
from kafka import KafkaProducer,KafkaConsumer
import json
import re
from hdfs import InsecureClient
import os
from bs4 import BeautifulSoup
import uuid
import  threading
HDFS_URL = os.environ["HDFS_URL"]
HDFS_USER = os.environ["HDFS_USER"]
client = InsecureClient(HDFS_URL, user=HDFS_USER)

with open("data/idToCaseNumber.json", "r", encoding="utf8") as f:
    caseTable = json.load(f)

PRECEDENT_NOT_EXISTS_ERROR = Response(
    {"code": "P004",
     "message": "해당 판례는 존재하지 않습니다."
     },
    status=status.HTTP_400_BAD_REQUEST)

model_name = "jhgan/ko-sroberta-multitask"  # (KorNLU 데이터셋에 학습시킨 한국어 임베딩 모델)
model_kwargs = {'device': 'cpu'}
encode_kwargs = {'normalize_embeddings': False}
embedding_model = HuggingFaceEmbeddings(
    model_name=model_name,
    model_kwargs=model_kwargs,
    encode_kwargs=encode_kwargs
)
producer = KafkaProducer(
    acks=0, # 메시지 전송 완료에 대한 체크
    compression_type='gzip', # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=['kafka2.wookoo.shop:9092',"kafka3.wookoo.shop:9092"], # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x:json.dumps(x).encode('utf-8') # 메시지의 값 직렬화
)

consumer = KafkaConsumer(
    "response",
    group_id='test2',  # Consumer 그룹 ID
    bootstrap_servers=['kafka2.wookoo.shop:9092',"kafka3.wookoo.shop:9092"],
    auto_offset_reset='earliest',  # 소비자 그룹이 처음으로 오프셋을 설정할 때 어떻게 처리할지 설정
)

kafka_response = {}

def kafkaConsume():
    print("start Thread")
    while True:
        for message in consumer:
            key = message.key.decode('utf-8') if message.key else None
            value = message.value.decode('utf-8') if message.value else None
            response = json.loads(value)
            print(value,response)
            kafka_response[response["id"]] = response

thread = threading.Thread(target=kafkaConsume)
thread.start()

def search(search_request):
    # 여기서 서비스레이어가 돌아간다.
    content = search_request['content'].value
    count = search_request['count'].value

    vector = embedding_model.embed_query(content)
    key = str(uuid.uuid4())
    kafkaRequest = {
        "id":key,
        "request" : "search",
        "vector" : vector,
        "count" : count
    }


    print("hahaha")
    producer.send("request",value=kafkaRequest)
    while not key in kafka_response:
       pass

    response = {"data":kafka_response[key]['data']}
    del kafka_response[key]







    # fileList = [(re.sub(r'[^0-9]', '', i[0].metadata['source']) + ".html") for i in
    #             vectorstore.similarity_search_with_score(content, k=count)]
    # #return Response({"data":fileList}, status=status.HTTP_200_OK)
    #
    # # print(j[0].metadata)
    # fileList = [(re.sub(r'[^0-9]', '', i[0].metadata['source']) + ".json", i[1]) for i in
    #             vectorstore.similarity_search_with_score(content, k=count)]
    # data = []
    # keys = ["info", "summary", "judgement", "conclusion", "keywords"]
    #
    # for fileName, score in fileList:
    #     json_data = {"score": score}
    #     with client.read(f"/data/json/{fileName}", encoding="utf8") as f:
    #         t = json.load(f)
    #
    #         for key in keys:
    #             try:
    #                 json_data[key] = t[key]
    #             except:
    #                 json_data[key] = None
    #
    #         if (json_data["keywords"] == None):
    #             json_data["keywords"] = []
    #
    #         data.append(json_data)
    # response = {
    #     "data": data
    # }

    return Response(response, status=status.HTTP_200_OK)

    # content 로 검색 진행


def findSummaryByCaseNumber(caseNumber):
    if not caseNumber in caseTable:
        return PRECEDENT_NOT_EXISTS_ERROR

    caseId = caseTable[caseNumber]
    with client.read(f"/data/json/{caseId}.json", encoding="utf8") as f:
        response = json.load(f)
        return Response(response, status=status.HTTP_200_OK)
    assert False  # Exception 핸들러가 처리


def findDetailByCaseNumber(caseNumber):
    if not caseNumber in caseTable:
        return PRECEDENT_NOT_EXISTS_ERROR

    caseId = caseTable[caseNumber]
    with client.read(f"/data/json/{caseId}.json", encoding="utf8") as f:
        j = json.load(f)
        info = j['info']
        relate = j['relate']

    with client.read(f"/data/html/{caseId}.html", encoding="utf8") as f:
        html = f.read()
        soup = BeautifulSoup(html)
        text = soup.get_text('\n')
        text = re.sub(r'\n{2,}', '\n', text)
        text = re.sub(r'\s{2,}', ' ', text)
        response = {"info": info, "relate" : relate, "data": text, "html": html}
        return Response(response, status=status.HTTP_200_OK)
    assert False  # Exception 핸들러가 처리
