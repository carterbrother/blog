#!/bin/bash

java -server -Xms64m -Xmx256m -Dserver.port=9999 -Dserver.servlet.context-path=/data -Dspring.profiles.active=dev -jar /tmp/data_tool_app.jar &
