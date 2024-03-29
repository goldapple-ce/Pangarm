from kafka import KafkaConsumer
import json
from bs4 import BeautifulSoup
import os
import requests

KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
assert KAFKA_BOOTSTRAP_SERVER!= None
def get_response_by_precSeq(precSeq):
    params = {
        "precSeq":precSeq,
        "mode":0
    }
    url = "https://www.law.go.kr/LSW/precInfoP.do"
    response = requests.get(url,params=params)
    return response

def parse_raw_html(response):
    html = response.text
    soup = BeautifulSoup(html, 'html.parser')
    data = soup.find("div",{"id":"contentBody"})
    return str(data)

def saveHtml(precSeq,html):
    with open(f"/app/data/html/{precSeq}.html" ,"w",encoding="utf8") as f:
        f.write(html)

def saveLog(precSeq):
    with open(f"/app/data/log/log.txt","a") as f:
        f.write(f"{precSeq}\n")


consumer = KafkaConsumer(
    "precedent_api_list",
    group_id="precedent_api_list_crawler",  # Consumer 그룹 ID
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,
    auto_offset_reset='earliest',  # 소비자 그룹이 처음으로 오프셋을 설정할 때 어떻게 처리할지 설정
    max_poll_records=10
)

while True:
    for message in consumer:
        key = message.key.decode('utf-8') if message.key else None
        value = message.value.decode('utf-8') if message.value else None
        try:
            value = json.loads(value)
            precSeq = value['판례일련번호']
            response = get_response_by_precSeq(precSeq)
            html = parse_raw_html(response)
            saveHtml(precSeq,html)
            saveLog(precSeq)
        
        except Exception as ex:
            print(ex)
            pass