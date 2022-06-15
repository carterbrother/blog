#!/usr/bin/env bash



docker build  -t  springboot/helloWorldApp:V1 -f ./Dockerfile

docker run -d -p 8080:8080 --name helloWorldApp   springboot/helloWorldApp:V1  java -Dserver.port=8080 -jar /app/app.jar