## 실행 방법

1. 먼저 [국가법령정보 공동활용 API](https://open.law.go.kr/LSO/openApi/guideList.do) 에서 API 키를 발급받아줍시다.

2. `.env` 파일을 열고 `API_KEY` 내용을 채워줍시다.

3. `.env` 파일을 열고 `kafka` 서버를 채워줍시다.
    - ex) 카프카를 클러스터링 중이고, `kafka01.example:9092`, `kafka02.example:9092` 를 사용중이라면 `KAFKA_BOOTSTRAP_SERVER=kafka01.example:9092, kafka02.example:9092`

```
API_KEY={YOUR_API_KEY}
KAFKA_BOOTSTRAP_SERVER={YOUR_KAFKA_BOOTSTAR_SERVER}
```

내용이 채워졌다면 `run.sh` 를 실행해주세요.
실행 중인 파일은 data/log에서 확인하실수 있습니다.
