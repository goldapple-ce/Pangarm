#!/bin/bash
mkdir data
mkdir data/html
mkdir data/json
docker build -t precedent-api-process-python .
docker run --rm  --env-file .env -v ./data:/app/data precedent-api-process-python