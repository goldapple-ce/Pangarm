#!/bin/bash
docker build -t mail-scheduler .
docker run --rm  --env-file .env mail-scheduler