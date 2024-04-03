#!/bin/bash
docker build -t news-crawler .
docker run --rm  --env-file .env news-crawler