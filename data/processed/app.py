import json
import os
from kafka import KafkaProducer
from dotenv import load_dotenv
load_dotenv()


KAFKA_BOOTSTRAP_SERVER = os.environ.get("KAFKA_BOOTSTRAP_SERVER")

KAFKA_PRODUCE_TOPIC = "precedent-api-list"
FILE_STORE_PATH = "./data"
assert KAFKA_BOOTSTRAP_SERVER != None

KAFKA_PRODUCE_TOPIC = "precedent-process"
producer = KafkaProducer(
    acks=0,  # 메시지 전송 완료에 대한 체크
    compression_type="gzip",  # 메시지 전달할 때 압축(None, gzip, snappy, lz4 등)
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVER,  # 전달하고자 하는 카프카 브로커의 주소 리스트
    value_serializer=lambda x: json.dumps(
        x, ensure_ascii=False).encode('utf-8')  # 메시지의 값 직렬화
)

for file_name in os.listdir(FILE_STORE_PATH):
    with open(f"{FILE_STORE_PATH}/{file_name}", "r", encoding="utf8") as f:
        json_data = json.load(f)

    producer.send(KAFKA_PRODUCE_TOPIC, value=json_data)
    producer.flush()
