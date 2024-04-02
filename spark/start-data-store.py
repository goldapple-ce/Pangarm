from pyspark.sql.types import StructType, StructField, ArrayType, StringType, DoubleType, BooleanType
from pyspark.sql.functions import from_json, col
from pyspark.sql import SparkSession
import os
from dotenv import load_dotenv
load_dotenv()
KAFKA_BOOTSTRAP_SERVER = os.environ["KAFKA_BOOTSTRAP_SERVER"]
spark = SparkSession.builder.appName("streamData").getOrCreate()
# Kafka에서 데이터 읽기

KAFKA_CONSUME_TOPIC = "precedent-process"
df_kafka = spark \
    .readStream \
    .format("kafka") \
    .option("kafka.bootstrap.servers", KAFKA_BOOTSTRAP_SERVER) \
    .option("subscribe", KAFKA_CONSUME_TOPIC) \
    .load()

# id
schema = StructType([

    StructField("info", StringType()),
    StructField("relate", StringType()),
    StructField("parties", StringType()),
    StructField("originalJudgement", StringType()),
    StructField("purport", StringType()),
    StructField("opinion", StringType()),
    StructField("fact", StringType()),
    StructField("judgement", StringType()),
    StructField("conclusion", StringType()),
    StructField("summary", StringType()),
    StructField("caseNumber", StringType()),
    StructField("keywordList", ArrayType(StringType())),
    StructField("isPdf", BooleanType()),
    StructField("body", StringType()),
    StructField("vector", ArrayType(DoubleType())),
])

# JSON 데이터 파싱
df_parsed = df_kafka.select(from_json(col("value").cast(
    "string"), schema).alias("data")).select("data.*")
query = df_parsed.writeStream\
    .outputMode("append")\
    .format("parquet")\
    .option("path", "/data/precedent")\
    .option("checkpointLocation", "/data/checkpoints/precedent")\
    .start()

query.awaitTermination()
