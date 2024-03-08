import re
import json
import requests
from kafka import KafkaProducer
import os
KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")
KAFKA_CONSUME_TOPIC = os.environ.get("KAFKA_CONSUME_TOPIC")
KAFKA_CONSUME_GROUP = os.environ.get("KAFKA_CONSUME_GROUP")
KAFKA_PRODUCE_TOPIC = os.environ.get("KAFKA_PRODUCE_TOPIC")
assert KAFKA_BOOTSTRAP_SERVER!= None
assert KAFKA_CONSUME_TOPIC!= None
assert KAFKA_CONSUME_GROUP != None
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



i = 1
while True:
    
    response = getResponseByPageId(i)
    html = response.text
    matches = re.findall(pattern, html)
    if not matches:
        break
    i+=1
    with open("result.txt","a") as f:
        for match in matches:
            data = {"seqnum" : match[0], "cbub_code" : match[1]}
            send_data = json.dumps(data)
            f.write(send_data+"\n")