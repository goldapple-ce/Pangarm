## 실행 방법

1. 먼저 [CHAT GPT API](https://platform.openai.com/api-keys) 에서 API 키를 발급받아줍시다.

2. `.env` 파일을 열고 `GPT_KEY` 내용을 채워줍시다.

3. `.env` 파일을 열고 `kafka` 서버를 채워줍시다.

    - ex) 카프카를 클러스터링 중이고, `kafka01.example:9092`, `kafka02.example:9092` 를 사용중이라면 `KAFKA_BOOTSTRAP_SERVER=kafka01.example:9092, kafka02.example:9092`

4. `GPU` 를 사용하여 판례 데이터 세트를 생성 하시려면 `DEVICE`에 `GPU` 를, 그게 아니라면 `CPU` 를 작성해주세요.

```
DEVICE=
GPT_KEY=
KAFKA_BOOTSTRAP_SERVER=

```

내용이 채워졌다면 `run.sh` 를 실행해주세요.
실행 결과는 `data/html`, `data/json` 폴더에서 확인하실수 있습니다.
