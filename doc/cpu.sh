#!/bin/bash

# 由crontab触发每分钟执行一次，判断CPU使用率大于阈值时触发dump
# 使用方式：
# 把当前文件放到项目中与start.sh相同的目录
# 修改start.sh 在脚本最后加一行，一般是这一行后边 echo "$APP_NAME is up runnig :)"
# echo "* * * * * sh /export/App/bin/cpu-peak-dump.sh" | crontab -
# 可配置项:
# 触发dump的cpu阈值。default 70
# STACK_DUMP_CPU_THRESHOLD=xxx
# 触发dump时列举的线程数（按使用率由高到低排列) default 10
# STACK_DUMP_THREAD_COUNT=xxx
# 配置方式，使用行云分组的环境变量配置即可
# stack log 存放目录 /export/Logs/
# stack log 文件名: jstack_snapshot_$(date +%Y%m%d%H%M%S).log
# 最后，记得配置相应的日志清理策略

# 设置CPU阈值，当CPU使用率达到该阈值时触发线程快照
CPU_THRESHOLD="${STACK_DUMP_CPU_THRESHOLD:-70}"
THREAD_COUNT="${STACK_DUMP_THREAD_COUNT:-10}"

echo "Current CPU_THRESHOLD is $CPU_THRESHOLD"

JAVA_PID=$(pgrep -d, -x java)
echo "Current JAVA_PID is $JAVA_PID"

if [ -z "$JAVA_PID" ]; then
  echo "No Java process found."
  exit 1
fi


# 使用top命令获取当前CPU使用率，并提取其中的CPU利用率百分比
CPU_USAGE=$(top  -n 1 | grep -A10 "PID USER" | grep java | grep "$JAVA_PID" | awk '{print $9}' | cut -d'.' -f1)

echo "Current Java($JAVA_PID) CPU_USAGE ：$CPU_USAGE"%


# 检查CPU使用率是否超过阈值
if [[ $CPU_USAGE -gt $CPU_THRESHOLD ]]; then

  # 使用top命令查找占用CPU最高的前十个线程，并获取它们的信息
  TOP_THREADS=$(top -H  -n 1 -p "$JAVA_PID" | grep -A$THREAD_COUNT 'PID USER' | head -n $THREAD_COUNT | grep -v 'PID')

  # 使用jstack捕捉JVM线程快照
  # 请将下面的Java进程ID替换为你要监视的Java进程的实际进程ID
  JSTACK_OUTPUT=$(/export/servers/jdk1.8.0_191/bin/jstack "$JAVA_PID")

  JSTACK_OUTPUT_FILE="$(date +%Y%m%d%H%M%S).log"
  echo "当前JAVA进程ID($JAVA_PID)CPU使用率:$CPU_USAGE"% >>$JSTACK_OUTPUT_FILE

  # 获取占用CPU最高的前十个线程的信息，包括线程的PID和堆栈信息，并将它们合并到同一行输出
  echo "Top ${THREAD_COUNT} CPU占用线程信息：" >>$JSTACK_OUTPUT_FILE
  while read -r THREAD_INFO; do
    THREAD_TID=$(echo "$THREAD_INFO" | awk '{print $1}')
    THREAD_NID=$(printf "%x\n" $THREAD_TID)

    THREAD_STACK=$(echo "$JSTACK_OUTPUT" | sed -n "/nid=0x$THREAD_NID /,/^$/p")
    THREAD_CPU_USAGE=$(echo "$THREAD_INFO" | awk '{print $9}')

    echo "=======================================================" >>$JSTACK_OUTPUT_FILE
    echo "线程TID: $THREAD_TID, THREAD_NID:$THREAD_NID, CPU使用率: $THREAD_CPU_USAGE%" >>$JSTACK_OUTPUT_FILE
    echo "$THREAD_STACK" >>$JSTACK_OUTPUT_FILE
  done <<<"$TOP_THREADS"

  #  echo "====all stack as below:====" >>$JSTACK_OUTPUT_FILE
  #  echo "$JSTACK_OUTPUT" >>$JSTACK_OUTPUT_FILE
  echo "捕捉了JVM线程快照并保存到 $JSTACK_OUTPUT_FILE"
fi