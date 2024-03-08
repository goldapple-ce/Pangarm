import re
import json
import requests
from kafka import KafkaProducer
import os
KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
KAFKA_PRODUCE_TOPIC = os.environ.get("KAFKA_PRODUCE_TOPIC")
assert KAFKA_BOOTSTRAP_SERVER!= None
assert KAFKA_PRODUCE_TOPIC != None

def getResponseByPageId(pageId):
    cookies = {
        'WMONID': 'Ve3q0NLwDQF',
        'JSESSIONSCW': 'KnKs0ANCNQstroxEP0vccGTbGHP1541avwVuk3fFABsfZwMwnmX1zfvMaHXXnzGp.BJEUWS05_servlet_SCWWW',
    }

    headers = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8',
        'Accept-Language': 'ko-KR,ko;q=0.5',
        'Cache-Control': 'max-age=0',
        'Connection': 'keep-alive',
        'Content-Type': 'application/x-www-form-urlencoded',
        # 'Cookie': 'WMONID=Ve3q0NLwDQF; JSESSIONSCW=KnKs0ANCNQstroxEP0vccGTbGHP1541avwVuk3fFABsfZwMwnmX1zfvMaHXXnzGp.BJEUWS05_servlet_SCWWW',
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
        'pageSize': '5',
        'pageIndex': pageId,
        'seqnum': '0',
        'cbub_code': '',
        'searchOption': '',
        'searchWord': '',
    }

    response = requests.post(
        'https://www.scourt.go.kr/portal/dcboard/DcNewsListAction.work',
        params=params,
        cookies=cookies,
        headers=headers,
        data=data,
    )
    html = response.text
    pattern = r"fn_view\('(\d+)','(\d+)'\)"
    # 정규식을 사용하여 파일 이름과 파일 설명 추출
    matches = re.findall(pattern, html)

    # 추출된 결과 출력
    return matches


def saveLog(arr):
    with open("/app/data/fileLog.txt","a") as f:
        for i in arr:
            data = {"seqnum":i[0],"cbub_code":i[1]}
            data = json.dumps(data,ensure_ascii=False)
            f.write(data+"\n")


def produceKafka(arr):
    for i in arr:
        data = {"seqnum":i[0],"cbub_code":i[1]}
        producer.send(KAFKA_PRODUCE_TOPIC, value=data)
        producer.flush() 

producer = KafkaProducer(
    acks=0, # 메시지 전송 완료에 대한 체크
    compression_type=None, # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER, # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x:json.dumps(x).encode('utf-8') # 메시지의 값 직렬화
)


i = 1
while True:
    
    matches = getResponseByPageId(i)
    if not matches:
        break
    i+=1
    saveLog(matches)
    produceKafka(matches)
    