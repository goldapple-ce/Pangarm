tar -zxvf data.tar.gz
docker build -t precedent-processed-upload .
docker run --rm  --env-file .env -v ./data:/app/data precedent-processed-upload