import xml.etree.ElementTree as ET
import requests
import json
import os
from kafka import KafkaProducer
from dotenv import load_dotenv
load_dotenv()


API_KEY = os.environ.get("API_KEY")
KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")

KAFKA_PRODUCE_TOPIC = "precedent-api-list"
FILE_STORE_PATH = "/app/data"

assert API_KEY != None
assert KAFKA_BOOTSTRAP_SERVER != None
print(API_KEY, KAFKA_BOOTSTRAP_SERVER)

with open(f"{FILE_STORE_PATH}/log/판례목록.txt", "w") as f:
    f.write("")

url = f"https://www.law.go.kr/DRF/lawSearch.do?OC={API_KEY}&target=prec&type=XML"


producer = KafkaProducer(
    acks=0,  # 메시지 전송 완료에 대한 체크
    compression_type="gzip",  # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,  # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x: json.dumps(
        x, ensure_ascii=False).encode('utf-8')  # 메시지의 값 직렬화
)


response = requests.get(url).text
xtree = ET.fromstring(response)

totalCnt = int(xtree.find('totalCnt').text)
page = 1
for i in range(int(totalCnt / 20)):
    try:
        items = xtree[5:]
    except:
        break

    for node in items:
        판례일련번호 = node.find('판례일련번호').text
        사건명 = node.find('사건명').text
        사건번호 = node.find('사건번호').text
        선고일자 = node.find('선고일자').text
        법원명 = node.find('법원명').text
        사건종류명 = node.find('사건종류명').text
        사건종류코드 = node.find('사건종류코드').text
        판결유형 = node.find('판결유형').text
        선고 = node.find('선고').text

        data = {'판례일련번호': int(판례일련번호),
                '사건명': 사건명,
                '사건번호': 사건번호,
                '선고일자': 선고일자,
                '법원명': 법원명,
                '사건종류명': 사건종류명,
                '사건종류코드': 사건종류코드,
                '판결유형': 판결유형,
                '선고': 선고
                }

        producer.send(KAFKA_PRODUCE_TOPIC, value=data)
        producer.flush()
        data = json.dumps(data, ensure_ascii=False)
        with open(f"{FILE_STORE_PATH}/log/판례목록.txt", "a") as f:
            f.write(data+"\n")

    page += 1
    url = f"https://www.law.go.kr/DRF/lawSearch.do?OC={API_KEY}&target=prec&type=XML&page={page}"
    response = requests.get(url).text
    xtree = ET.fromstring(response)
