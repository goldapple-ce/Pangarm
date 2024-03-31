#!/bin/bash
docker build -t precedent-api-list-python .
docker run --rm  --env-file .env precedent-api-list-python