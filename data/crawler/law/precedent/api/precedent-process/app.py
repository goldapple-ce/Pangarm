from kafka import KafkaConsumer, KafkaProducer
import json
from bs4 import BeautifulSoup
import os
import requests
import re
from langchain import LLMChain
from langchain.prompts import PromptTemplate
from langchain_core.output_parsers import JsonOutputParser
from langchain.schema.runnable import RunnablePassthrough
from langchain.chat_models import ChatOpenAI
from langchain.embeddings import HuggingFaceEmbeddings
from dotenv import load_dotenv
load_dotenv()


KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
GPT_KEY = os.environ.get("GPT_KEY")




device = "cuda" if os.environ.get("DEVICE") in ["gpu", "GPU"] else "cpu"

KAFKA_CONSUME_TOPIC = "precedent-api-list"
# KAFKA_PRODUCE_TOPIC = "precedent_proceesed_with_vector"
KAFKA_PRODUCE_TOPIC = "precedent-process"
# FILE_STORE_PATH = "/app/data"
FILE_STORE_PATH = "./data"

assert KAFKA_BOOTSTRAP_SERVER != None
assert GPT_KEY != None

print(KAFKA_BOOTSTRAP_SERVER,GPT_KEY,device)

consumer = KafkaConsumer(
    KAFKA_CONSUME_TOPIC,
    group_id="precedent_api_list_crawler",  # Consumer 그룹 ID
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,
    auto_offset_reset='earliest',  # 소비자 그룹이 처음으로 오프셋을 설정할 때 어떻게 처리할지 설정
    max_poll_records=10
)


producer = KafkaProducer(
    acks=0,  # 메시지 전송 완료에 대한 체크
    compression_type='gzip',  # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,  # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x: json.dumps(x).encode('utf-8')  # 메시지의 값 직렬화
)

model_name = "jhgan/ko-sroberta-multitask"  # (KorNLU 데이터셋에 학습시킨 한국어 임베딩 모델)


model_kwargs = {'device': device}
encode_kwargs = {'normalize_embeddings': False}

embedding_model = HuggingFaceEmbeddings(
    model_name=model_name,
    model_kwargs=model_kwargs,
    encode_kwargs=encode_kwargs
)


template = """다음과 같은 맥락을 사용하여 질문에 대답하십시오.
만약 답을 모르면 null이라고 하고 답을 지어내려고 하지 마십시오.
답변은 템플릿에 맞게 json 형식으로 대답하시오.
맨 첫 3줄까지 등장하는 판결번호는 원심판결번호가 아님.
맥락: {content}
질문 : 취지, 원고의 주장, 피고의 주장, 원고, 피고, 일어난 사실, 법원의 판단, 원심판결번호, 주문, 요약, 키워드들
템플릿 :
[
"purport": 취지
"opinion": [
"plaintiff": 원고의 주장,
"defendant": 피고의 주장,
],
"parties": [
"plaintiff": 원고,
"defendant": 피고,
],
"fact": 일어난 사실,
"judgement": 법원의판단,
"originalJudgement": [
"caseNumber": 원심판결번호
],
"conclusion": 주문,
"summary": 요약,
"keywordList": [키워드들]
]"""

prompt = PromptTemplate.from_template(template)
llm = ChatOpenAI(model_name="gpt-3.5-turbo-16k",
                 temperature=0, openai_api_key=GPT_KEY)
chain = prompt | llm | JsonOutputParser()


def get_response_by_precSeq(precSeq):
    params = {
        "precSeq": precSeq,
        "mode": 0
    }
    url = "https://www.law.go.kr/LSW/precInfoP.do"
    response = requests.get(url, params=params)
    return response


def parse_raw_html(response):
    html = response.text
    soup = BeautifulSoup(html, 'html.parser')
    data = soup.find("div", {"id": "contentBody"})
    return str(data)


def save_html(file_name, html):
    with open(f"{FILE_STORE_PATH}/html/{file_name}.html", "w", encoding="utf8") as f:
        f.write(html)


def save_json(file_name, json_data):
    with open(f"{FILE_STORE_PATH}/json/{file_name}.json", "w",encoding="utf8") as f:
        json.dump(json_data, f, ensure_ascii=False, indent=4)


def parse_info(json_data):
    info = {}

    info['caseNumber'] = json_data['사건번호']
    info['caseName'] = json_data['사건명']
    info['judgementDate'] = json_data['선고일자']
    info["type"] = {}
    info['type']['incident'] = json_data['사건종류명']
    info['type']['courtName'] = json_data['법원명']
    info['type']['verdict'] = json_data['선고']
    return info


def parse_relate(html):
    # 참조 판례
    soup = BeautifulSoup(html, "html.parser")
    relateLawSet = set()
    relatePrecedentSet = set()
    linkList = soup.findAll("a", {"href": "#AJAX"})
    before = ""
    for i in linkList:
        relate = i['onclick']
        relate = relate.replace(");", "").strip()
        if (relate.startswith("javascript:fncLawPop")):
            relate = relate.replace("javascript:fncLawPop(", "")
            try:
                start, end = re.search(pattern, i.text).span()
                name = i.text[start:]
                before = name.strip()
            except:
                name = before + i.text
            relate = relate.replace("''", "")
            relate = f"[{relate}]👵{name}"
            relateLawSet.add(relate)
    #         relate = eval(relate)
        else:
            relate = relate.replace(
                "javascript:showCase(", "").strip().strip("'")
            relatePrecedentSet.add(relate)

    relatePrecedentList = [{"caseNumber": i} for i in relatePrecedentSet]

    relateLawList = []
    for i in relateLawSet:
        temp, j = i.split("👵")
        try:
            i = eval(temp)
            t = f"{i[0]} {j}"
            # print(i)
            relateLawList.append(
                {
                    "lawName": t,
                    "searchNumber": i[2],
                    "searchName": i[0],
                    "searchType": i[1],
                    "searchKey": i[3]
                }
            )
        except:
            pass
    relateLawList = sorted(relateLawList, key=lambda x: x["lawName"])

    return {
        "lawList": relateLawList,
        "precedentList": relatePrecedentList

    }


def get_html_text(html):
    soup = BeautifulSoup(html)
    text = soup.get_text()
    text = re.sub("\s{2,}", "\n", text)
    return text


while True:
    for message in consumer:
        key = message.key.decode('utf-8') if message.key else None
        value = message.value.decode('utf-8') if message.value else None
        try:
            value = json.loads(value)
            precSeq = value['판례일련번호']
            response = get_response_by_precSeq(precSeq)
            html = parse_raw_html(response)
            relate = parse_relate(html)
            info = parse_info(value)
            caseNumber = info["caseNumber"]
            isPdf = False
            body = html
            text = get_html_text(html)
            save_html(caseNumber, html)
            vector = embedding_model.embed_query(text)

            response = {

                "info": info,
                "relate": relate,
                "caseNumber": caseNumber,
                "vector": vector,
                "isPdf": isPdf,
                "body": body
            }
            out = chain.invoke({"content": text})
            for key in out.keys():
                response[key] = out[key]
            producer.send(KAFKA_PRODUCE_TOPIC, value=response)
            producer.flush()
            save_json(caseNumber, response)

        except KeyboardInterrupt:
            exit(0)
        except Exception as ex:
            pass