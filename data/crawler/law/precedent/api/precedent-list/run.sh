#!/bin/bash
mkdir data
mkdir data/log
docker build -t precedent-api-list-python .
docker run --rm  --env-file .env -v ./data/log:/app/data/log precedent-api-list-python