#!/bin/sh
set -e

PID=$(ps aux | grep java | grep guodao212 | grep -v grep | awk '{ print $2 }')
if [ -n "$PID" ]; then
   echo "Trying to kill exiting running guodao212 process with PID [${PID}]"
   kill -9 $PID
fi

export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8

MYDIR=$(dirname $0)
cd $MYDIR

nohup ./gradlew clean jettyRun >> guodao212.log 2>&1 & 
