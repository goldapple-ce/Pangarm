import re
import requests
import json
from kafka import KafkaProducer
from kafka import KafkaConsumer
from json import JSONDecodeError
import os
KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
KAFKA_CONSUME_TOPIC = os.environ.get("KAFKA_CONSUME_TOPIC")
KAFKA_CONSUME_GROUP = os.environ.get("KAFKA_CONSUME_GROUP")
KAFKA_PRODUCE_TOPIC = os.environ.get("KAFKA_PRODUCE_TOPIC")
assert KAFKA_BOOTSTRAP_SERVER!= None
assert KAFKA_CONSUME_TOPIC!= None
assert KAFKA_CONSUME_GROUP != None
assert KAFKA_PRODUCE_TOPIC != None
def getPrecedentSpecific(seqnum,cbub_code):
    cookies = {
        'WMONID': 'Ve3q0NLwDQF',
        'JSESSIONSCW': 'KnKs0ANCNQstroxEP0vccGTbGHP1541avwVuk3fFABsfZwMwnmX1zfvMaHXXnzGp.BJEUWS04_servlet_SCWWW',
    }

    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8',
        'Accept-Language': 'ko-KR,ko;q=0.5',
        'Cache-Control': 'max-age=0',
        'Connection': 'keep-alive',
        'Content-Type': 'application/x-www-form-urlencoded',
        # 'Cookie': 'WMONID=Ve3q0NLwDQF; JSESSIONSCW=KnKs0ANCNQstroxEP0vccGTbGHP1541avwVuk3fFABsfZwMwnmX1zfvMaHXXnzGp.BJEUWS04_servlet_SCWWW',
        'Origin': 'https://www.scourt.go.kr',
        'Referer': 'https://www.scourt.go.kr/portal/dcboard/DcNewsListAction.work?gubun=44',
        'Sec-Fetch-Dest': 'document',
        'Sec-Fetch-Mode': 'navigate',
        'Sec-Fetch-Site': 'same-origin',
        'Sec-Fetch-User': '?1',
        'Sec-GPC': '1',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36',
        'sec-ch-ua': '"Chromium";v="122", "Not(A:Brand";v="24", "Brave";v="122"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Windows"',
    }

    params = {
        'gubun': '44',
    }

    data = {
        'pageSize': '1',
        'pageIndex': '5',
        'seqnum': seqnum,
        'cbub_code': cbub_code,
        'searchOption': '',
        'searchWord': '',
    }

    response = requests.post(
        'https://www.scourt.go.kr/portal/dcboard/DcNewsViewAction.work',
        params=params,
        cookies=cookies,
        headers=headers,
        data=data,
    )
    pattern = r"download\('([^']+)','([^']+)'\)"
    html = response.text

# 정규식을 사용하여 파일 이름과 파일 설명 추출
    matches = re.findall(pattern, html)

    # 추출된 결과 출력
    return matches

def saveLog(arr):
    with open("/app/data/fileLog.txt","a") as f:
        for i in arr:
            data = {"file":i[0],"name":i[1]}
            data = json.dumps(data,ensure_ascii=False)
            f.write(data+"\n")

def sendKafkaPdf(arr):
    for i in arr:
        data = {"file":i[0],"name":i[1]}
        data = json.dumps(data,ensure_ascii=False)
        producer.send(KAFKA_PRODUCE_TOPIC, value=data)
        producer.flush() 


consumer = KafkaConsumer(
    KAFKA_CONSUME_TOPIC,
    group_id=KAFKA_CONSUME_GROUP,  # Consumer 그룹 ID
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,
    auto_offset_reset='earliest',  # 소비자 그룹이 처음으로 오프셋을 설정할 때 어떻게 처리할지 설정
    max_poll_records=10
)
producer = KafkaProducer(
    acks=0, # 메시지 전송 완료에 대한 체크
    compression_type=None, # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER, # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x:json.dumps(x).encode('utf-8') # 메시지의 값 직렬화
)
while True:
    for message in consumer:
        
            # 메시지의 key와 value 출력
        key = message.key.decode('utf-8') if message.key else None
        value = message.value.decode('utf-8') if message.value else None
        value = value.strip()
        try:
            value = json.loads(json.loads(value))
            result = getPrecedentSpecific(value["seqnum"],value["cbub_code"])
            saveLog(result)
            sendKafkaPdf(result)
        except JSONDecodeError as ex:
            continue
    