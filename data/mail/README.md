# 실행 가이드

## 실행 하기 앞서

우분투 및 맥 도커 환경에서 정상 실행 확인된 코드 입니다.

해당 코드는 매일 `오전 8시 30분` 에 `ElasticSearch` 에서 최신 뉴스 데이터를 조회 한 후 사용자에게 메일 전송을 하는 프로그램 입니다. 그렇기 때문에 `ElasticSearch` 가 사전에 실행되어야 합니다.

시작전 `.env` 파일을 자신의 환경에 맞게 수정해주세요

ex) 엘라스틱 서치 주소 : http://localhost:9200, 메일서버 주소 : http://localhost:1234, 배포 서버 주소 : http://your.site.domain

```
ELASTIC_SEARCH_URL=http://localhost:9200
MAIL_SERVER_URL=http://localhost:1234
SERVER_URL=http://your.site.domain
```

## 실행 방법

`sh run.sh` 를 터미널에서 실행 하면 자동으로 빌드 되고 실행됩니다.
