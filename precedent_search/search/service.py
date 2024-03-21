from rest_framework.response import Response
from rest_framework import status
from langchain.embeddings import HuggingFaceEmbeddings
from langchain.vectorstores import Chroma
import json
import re
from hdfs import InsecureClient
import os
HDFS_URL = os.environ["HDFS_URL"]
HDFS_USER = os.environ["HDFS_USER"]
client = InsecureClient(HDFS_URL, user=HDFS_USER)



model_name = "jhgan/ko-sroberta-multitask"  # (KorNLU 데이터셋에 학습시킨 한국어 임베딩 모델)
model_kwargs = {'device': 'cpu'}
encode_kwargs = {'normalize_embeddings': False}
embedding_model = HuggingFaceEmbeddings(
    model_name=model_name,
    model_kwargs=model_kwargs,
    encode_kwargs=encode_kwargs
)
vectorstore = Chroma(persist_directory="ml/model", embedding_function=embedding_model)
retriever = vectorstore.as_retriever()


def search(search_request):
    # 여기서 서비스레이어가 돌아간다.
    content = search_request['content'].value
    count = search_request['count'].value

    # print(j[0].metadata)
    fileList = [ (re.sub(r'[^0-9]', '', i[0].metadata['source']) + ".json",i[1]) for i in vectorstore.similarity_search_with_score(content,k=count)]
    data = []
    keys = ["info","summary","judgement","conclusion"]

    for fileName,score in fileList:
        json_data = {"score":score}
        with client.read(f"/data/json/{fileName}",encoding="utf8") as f:
            t = json.load(f)

            for key in keys:
                try:
                    json_data[key] = t[key]
                except:
                    json_data[key] = None
            data.append(json_data)
    response = {
        "data": data
    }

    return Response(response, status=status.HTTP_200_OK)

    # content 로 검색 진행
