# 실행 환경

해당 환경은 `Ubuntu 22.04`, `hadoop-3.3.6`, `spark-3.5.0` 에서 정상 실행되는것을 확인 했습니다.

해당 메뉴얼은 단일 노드 환경에서 실행됨을 가정하고 작성된 배포 메뉴얼 입니다.

그 외에 멀티 노드 환경에선 각 환경에 맞게 설정해주세요.

모든 환경은 `hadoop` 과 `spark` 를 제외한 모든 프로그램은 `docker` 상에서 실행 됩니다.

## 서버 구성

서버는 `hadoop` 및 `spark` 로 구성된 클러스터 14 대, `kafka` 클러스터 2대, `nginx`, `springboot (메인서버, 메일 발송서버)`, `mysql` 용 1대, 판례 수집 및 전처리 서버 5대, `elastic search` 용 1대로 총 물리적 서버가 22대를 사용하여 구성하였습니다.

## 구성 포트

-   18080 : 메인 서버 spring boot
-   28080 : 메일 서버 spring boot
-   9092 : kafka
-   3306 : mysql
-   2181 : zookeeper
-   9200 : elastic search
-   8787 : 판례 정보 검색 서버 (spark 에서 점유)

# 실행 전 설정

아래 단계를 설정 하기 전에 먼저 `hadoop` 과 `spark`설치가 필요합니다. 미리 설치해주세요.

## spark 용 설정

`spark` 폴더의 `.env`[링크](../spark/.env) 파일의 내용을 아래와 같이 작성해주세요

```
KAFKA_BOOTSTRAP_SERVER=localhost:9092
```

그 후 `spark` 폴더에 있는 `requirements.txt` [링크](../spark/requirements.txt) 를 통해 pip 의존성 목록을 설치해주세요

```
pip install -r requirements.txt
```

## 데이터 전처리용 설정

이미 전처리된 데이터 세트가 있습니다. 하지만 전처리된 데이터세트를 사용하지 않고 직접 분산환경을 통해 작업 하고 싶으신분들은 아래 가이드를 따라 해주세요.

### data/crawler/law/precedent/api/precedent-list

1. 먼저 [국가법령정보 공동활용 API](https://open.law.go.kr/LSO/openApi/guideList.do) 에서 API 키를 발급받아줍시다. 여기서 나온 키를 `precedentkey`로 가정하고 작성하겠습니다
2. `data/crawler/law/precedent/api/precedent-list/.env`[링크](../data/crawler/law/precedent/api/precedent-list/.env) 파일을 열고 아래와 같이 작성해주세요.

```
API_KEY=precedentkey
KAFKA_BOOTSTRAP_SERVER=localhost:9092
```

자세한 설정이 궁금하다면 [여기](../data/crawler/law/precedent/api/precedent-list/ReadMe.md) 를 확인해보세요.

### data/crawler/law/precedent/api/precedent-process

1. 먼저 [CHAT GPT API](https://platform.openai.com/api-keys) 에서 API 키를 발급받아줍시다. 여기서 나온 키를 `mykey` 로 가정하고 작성하겠습니다.
2. `data/crawler/law/precedent/api/precedent-process/.env`[링크](../data/crawler/law/precedent/api/precedent-process/.env) 파일을 열고 아래와 같이 작성해주세요.
3. `GPU` 를 사용하여 판례 데이터 세트를 생성 하시려면 `DEVICE`에 `GPU` 를, 그게 아니라면 `CPU` 를 작성해주세요.

```
DEVICE=CPU
GPT_KEY=mykey
KAFKA_BOOTSTRAP_SERVER=localhost:9092
```

멀티 노드 환경에서 설정이 궁금하다면 [여기](../data/crawler/law/precedent/api/precedent-process/ReadMe.md) 를 확인해보세요.

## 메일서버 설정

메일 서버는 `smtp` 설정이 필요합니다.
각각 [네이버](https://help.naver.com/service/30029/contents/21344?lang=ko) , [다음](https://cs.daum.net/faq/266/12145.html#17989), [구글](https://support.google.com/a/answer/176600?hl=ko) 에서 `smtp` 설정을 사용함으로 설정해주세요.

`네이버`를 사용중이고 이메일이 `apple@naver.com` 이고, 네이버 비밀번호가 `1234` 임을 가정하고 작성하겠습니다.

`exec/mail/.env` 파일[링크](./mail/.env)을 아래와 같이 수정해주세요.

```
MAIL_HOST=smtp.naver.com
MAIL_PORT=587
MAIL_USERNAME=apple
MAIL_PASSWORD=1234
MAIL_FROM=apple@naver.com
```

## 메인서버 설정

`exec/backend/.env`[링크](./backend/.env) 파일을 아래와 같이 수정해주세요.
`SPRING_JWT_SECRET` 은 영어 숫자 아무 글자나 작성해주세요. 여기서는 `mysecret1234567890` 으로 가정하고 작성하겠습니다.
`DATABASE`는 `mysql` 을 사용할 예정이고 외부에 전혀 노출되지 않으니 걱정하지 않으셔도 됩니다.

```
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
DATABASE_URL=jdbc:mysql://database:3306/precedent
DATABASE_USER=mysql
DATABASE_PASSWORD=mysqlpassword
SPRING_JWT_SECRET=mysecret1234567890
SPRING_JWT_ACCESS-EXPTIME=10000000000000000
SPRING_JWT_REFRESH-EXPTIME=1000000
ELASTIC_SEARCH_HOST=elastic
ELASTIC_SEARCH_PORT=9200
PRECEDENT_CLIENT_URL=http://host.docker.internal:8787
```

## MYSQL 설정

`exec/mysql/.env`[링크](./mysql/.env)파일을 아래와 같이 수정해주세요.

외부에 전혀 노출되지 않으니 걱정하지 않으셔도 됩니다.

```
MYSQL_USER=mysql
MYSQL_DATABASE=precedent
MYSQL_PASSWORD=mysqlpassword
MYSQL_ROOT_PASSWORD=mysqlpasswordroot
```

## 메일배치처리 설정

`data/mail/.env`[링크](../data/mail/.env) 파일을 아래와 같이 수정해주세요

```
ELASTIC_SEARCH_URL=http://host.docker.internal:9200
MAIL_SERVER_URL=http://host.docker.internal:28080
SERVER_URL=https://example.com
```

자세한 내용은 [여기](../data/mail/README.md) 를 참고해주세요

## Nginx 설정

해당 항목은 `example.com` 을 기준으로 배포 메뉴얼을 작성하겠습니다.

먼저 `example.com` 도메인의 `A 레코드` 를 IP로 설정해주세요.

그 후, `/etc/letsencrypt` 폴더에 `ssl 인증서` 가 있어야 합니다. 없으신분들은 미리 다운로드 받아주세요.

`exec/nginx/conf.d/default.conf`[링크](./nginx/conf.d/default.conf) 파일의 내용을 본인 환경에 맞게 작성해주세요.

추가할 부분은 `server_name` 과 `ssl_certificate` ,`ssl_certificate_key`, 맨 마지막 줄의 `add_header` 쪽 입니다.

```
server {
    listen 443 ssl;
    server_name example.com;

    ssl_certificate /etc/letsencrypt/live/example.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/example.com/privkey.pem;

    root /usr/share/nginx/html;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html; # 슬래시를 제거하여 수정
    }

    location /api {
                proxy_pass http://backend:8080;
                proxy_set_header Host $host:$server_port;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;

                proxy_http_version 1.1;
                proxy_request_buffering off;
                proxy_buffering off; # Required for HTTP-based CLI to work over SSL
                add_header 'X-SSH-Endpoint' 'example.com' always;
    }

    location /api/member/all-member-subscribe-info {
                return 403;
    }
}
```

## 뉴스 수집 설정

`data/crawler/newspaper/singlenode/.env` [링크](../data/crawler/newspaper/singlenode/.env) 파일을 아래와 같이 설정해주세요

```
ELASTIC_SEARCH_URL=host.docker.internal:9200
```

# 실행하기

최초 실행은 다음과 같은 과정이 필요합니다.

1. Docker 설치
2. Kafka, 엘라스틱 서치, MySQL, 메인서버, 메일서버, Nginx 설치 및 실행
3. Hadoop 설치 및 실행
4. Spark 설치 및 실행
5. `spark` 폴더의 `start-data-store.py` 실행 [가이드 문서](../spark/README.md)
6. 판례 데이터 수집 및 적재
7. `spark` 폴더의 `start-server.py` 실행 [가이드 문서](../spark/README.md)
8. 뉴스 데이터 수집

## Docker 설치

[공식문서](https://docs.docker.com/desktop/install/linux-install/) 를 통해 설치해주세요.

## Kafka, 엘라스틱 서치, MySQL, 메인서버, 메일서버, Nginx 설치 및 실행

위의 사전 준비를 모두 다 따라했으면 환경변수가 잘 세팅되었을 것 입니다.

`exec` 폴더의 `run.sh` 를 실행하거나, `exec` 폴더에서 `docker-compose up` 을 타이핑 해주세요.

`Docker` 를 통해 자동으로 실행 됩니다.

## Hadoop 설치 및 실행

[공식문서](https://hadoop.apache.org/) 를 통해 설치 해주세요.

설치 후 `hadoop 설치폴더/sbin` 폴더에서 `./start-all.sh` 를 실행하여 `hadoop` 을 실행해주세요.

## Spark 설치 및 실행

[공식문서](https://spark.apache.org/) 를 통해 설치 해주세요.

설치 후 `spark 설치폴더/sbin` 폴더에서 `./start-all.sh` 를 실행하여 `spark` 를 실행해주세요.

## `spark` 폴더의 `start-data-store.py` 실행

`spark` 폴더로 이동 후 아래 명령어를 타이핑해주세요.

```

spark-submit --master yarn --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.1 start-data-store.py

```

`kafka` 에서 판례 정보를 저장할 준비가 되었습니다.

자세한 정보는 [여기](../spark/README.md) 에서 확인해주세요.

## 판례 데이터 수집 및 적재

판례 데이터는 두가지 방법으로 적재 할 수 있습니다

1. 판례 데이터를 직접 GPT 를 통해 가공 후 직접 적재
2. 미리 가공된 데이터를 적재

### 1.판례 데이터를 직접 GPT 를 통해 가공 후 직접 적재

[여기](../data/crawler/law/precedent/api/precedent-list/ReadMe.md)가이드를 참고하여[run.sh](../data/crawler/law/precedent/api/precedent-list/run.sh) 를 실행합니다.

그 후, [여기](../data/crawler/law/precedent/api/precedent-process/ReadMe.md)가이드를 참고하여 [run.sh](../data/crawler/law/precedent/api/precedent-process/run.sh) 를 실행합니다.

`데이터 전처리용 설정` 을 따라했다면 [run.sh](../data/crawler/law/precedent/api/precedent-list/run.sh),[run.sh](../data/crawler/law/precedent/api/precedent-process/run.sh) 를 바로 실행하면 됩니다.

자동으로 `spark`를 통해 적재 됩니다.

### 2. 미리 가공된 데이터를 적재

약 `1500`개의 미리 전처리된 데이터세트를 작성해놓았습니다. `data/processed/.env` [링크](../data/processed/.env) 를 아래와 같이 수정해주세요.

```

KAFKA_BOOTSTRAP_SERVER=host.docker.internal:9092

```

그 후 [run.sh](../data/processed/run.sh) 를 실행해주세요.

자동으로 폴더가 생긴 후, 압축을 해제 하고 카프카에 전송을 시작합니다.

## `spark` 폴더의 `start-server.py` 실행

`spark` 폴더로 이동 후 아래 명령어를 타이핑해주세요.

```
spark-submit --master yarn start-server.py
```

자동으로 서버가 실행 될것입니다.

자세한 정보는 [여기](../spark/README.md) 에서 확인해주세요.

## 뉴스 수집 실행

`data/crawler/newspaper/singlenode/run.sh` [링크](../data/crawler/newspaper/singlenode/run.sh) 를 실행 해주세요.

자동으로 docker 환경에서 실행됩니다.
