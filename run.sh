#!/bin/sh

PID=$(ps aux | grep java | grep guodao212 | grep -v grep | awk '{ print $2 }')
if [ -e "$PID" ]; then
   echo "Killing exiting running guodao212 process with PID [${PID}]"
   kill -9 $PID
fi

export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
MYDIR=$(dirname $0)
cd $MYDIR
./gradlew clean jettyRun
