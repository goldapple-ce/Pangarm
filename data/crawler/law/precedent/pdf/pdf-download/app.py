import re
import requests
import json
from kafka import KafkaProducer
from kafka import KafkaConsumer
from json import JSONDecodeError
import os
import requests

KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
KAFKA_CONSUME_TOPIC = os.environ.get("KAFKA_CONSUME_TOPIC")
KAFKA_CONSUME_GROUP = os.environ.get("KAFKA_CONSUME_GROUP")
assert KAFKA_BOOTSTRAP_SERVER!= None
assert KAFKA_CONSUME_TOPIC!= None
assert KAFKA_CONSUME_GROUP != None
def downloadPdf(file):
    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8',
        'Accept-Language': 'ko-KR,ko;q=0.6',
        'Cache-Control': 'max-age=0',
        'Connection': 'keep-alive',
        'Content-Type': 'application/x-www-form-urlencoded',
        'Origin': 'https://www.scourt.go.kr',
        'Referer': 'https://www.scourt.go.kr/',
        'Sec-Fetch-Dest': 'document',
        'Sec-Fetch-Mode': 'navigate',
        'Sec-Fetch-Site': 'same-site',
        'Sec-Fetch-User': '?1',
        'Sec-GPC': '1',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36',
        'sec-ch-ua': '"Chromium";v="122", "Not(A:Brand";v="24", "Brave";v="122"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Windows"',
    }

    data = f'file={file}&path=003&downFile=as.pdf'

    response = requests.post('https://file.scourt.go.kr//AttachDownload', headers=headers, data=data)
    return response

def savePdf(file,response):
    with open(f"/app/data/pdf/{file}","wb") as f:
        f.write(response.content)

def saveLog(file):
    file = file.replace(".pdf",".txt")
    with open(f"/app/data/log/log.txt","a") as f:
        f.write(file+"\n")

consumer = KafkaConsumer(
    KAFKA_CONSUME_TOPIC,
    group_id=KAFKA_CONSUME_GROUP,  # Consumer 그룹 ID
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,
    auto_offset_reset='earliest',  # 소비자 그룹이 처음으로 오프셋을 설정할 때 어떻게 처리할지 설정
    max_poll_records=10
)
while True:
    for message in consumer:
            
            # 메시지의 key와 value 출력
        key = message.key.decode('utf-8') if message.key else None
        value = message.value.decode('utf-8') if message.value else None
        value = value.strip()
        try:
            value = json.loads(value)
            response = downloadPdf(value['file'])
            savePdf(value['file'],response)
            saveLog(value['file'])
        except JSONDecodeError as ex:
            continue
    