#!/bin/bash

APP_NAME="example-1.0-SNAPSHOT.jar"
LOG_FILE="application.log"

# 从命令行参数获取profile
PROFILE=$1

if [ -z "$PROFILE" ]
then
  echo "Please provide a profile as a command line argument."
  exit 1
fi

# 检查应用是否已经在运行...
# (略)

# 启动应用并指定profile，并将日志输出到文件
nohup java -jar example-1.0-SNAPSHOT/example-1.0-SNAPSHOT.jar &




