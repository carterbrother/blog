#!/usr/bin/env bash

SERVER_PORT="8999"
JAR_NAME="demo-shutdown-0.0.1-SNAPSHOT.jar"
JAVA_OPTIONS="-server -Xms256M -Xmx256M -Dserver.port=${SERVER_PORT}"
curl -X POST "http://localhost:${SERVER_PORT}/actuator/shutdown"

echo "SERVER_PORT:${SERVER_PORT}"
echo "JAVA_OPTIONS:${JAVA_OPTIONS}"
echo "JAR_NAME:${JAR_NAME}"
echo "URL:http://localhost:${SERVER_PORT}/actuator/shutdown"
echo "开始关闭旧的应用"

UP_STATUS=1

while(( ${UP_STATUS} > 0 ))
do
    echo "${UP_STATUS}"
    UP_STATUS=$(lsof -i:"${SERVER_PORT}" | wc -l)
    echo "${UP_STATUS}"
done

echo "关闭旧应用完成"

echo "启动新应用"

nohub>"${SERVER_PORT}".log java -jar "${JAVA_OPTIONS}" "${JAR_NAME}".jar 2>&1 &

echo "启动应用中" && tail -20f "${SERVER_PORT}".log