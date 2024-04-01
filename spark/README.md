# 실행 가이드

## 실행 하기 앞서

---

해당 코드는 PySpark 환경에서 작성된 코드며, PySpark는 Hadoop YARN 상에서 실행됩니다.

그렇기 때문에 먼저 [Hadoop](https://hadoop.apache.org/) 과 [Spark](https://spark.apache.org/) 가 선행설치 되어야 합니다.

실제 프로덕션 환경은 `14대`의 `Hadoop cluster` 로 구성되었습니다.

## 코드 및 실행 안내

### start-data-store.py

해당 코드는 전처리된 판례 정보를 `Kafka` 에서 `Consume` 하여 `HDFS` 에 적재하는 코드 입니다.

```sh
spark-submit --master yarn --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.0.1 start-data-store.py
```

를 실행 시키면 `Kafka` 의 `precedent-process` `Topic` 에서 `Consume` 하여 `HDFS` 의 `data/precedent` 디렉토리에 `parquet` 형태로 적재시킵니다.

### start-server.py

해당 코드는 `start-data-store.py` 를 통해 전처리된 판례 정보가 저장되면 `HDFS`에서 해당 코드를 읽고, `Rest API` 를 서빙하는 서버가 실행됩니다.

기본적으로 `8787` 번 포트를 사용하여 서버가 실행됩니다.

아래는 실행 방법 입니다.

```sh
spark-submit --master yarn --executor-memory [YOUR_SPARK_NODE_RAM_SIZE] --executor-cores [YOUR_SPARK_NODE_CORE_SIZE] --num-executors [YOUR_SPARK_NODE_SIZE] start-server.py
```

ex ) 만약 10대의 노드를 사용중이고 각 노드가 4개의 CPU 와 10GB 의 램 사용을 원한다면

```sh
spark-submit --master yarn --executor-memory 10G --executor-cores 4 --num-executors 10 start-server.py
```
