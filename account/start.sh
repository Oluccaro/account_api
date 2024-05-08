#!/bin/bash

./mvnw clean
./mvnw package

docker build -t account-ms .
docker-compose up
