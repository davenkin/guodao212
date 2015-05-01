#!/bin/sh
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
MYDIR=$(dirname $0)
echo $MYDIR
cd $MYDIR
./gradlew clean jettyRun 
