#!/bin/bash

ps -ef | grep "data_tool_app" | grep -v grep | awk '{print $2}' | xargs kill

echo '关闭data_tool_app成功！'

