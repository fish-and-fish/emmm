#!/bin/bash

export ACTIVE=schedule,email
export SERVER="target/userServer-1.0-SNAPSHOT"
export MODE="standalone"
export PORT=8083
export TIMEZONE=GMT-04:00
export BASE_DIR="/root/update/warehouse"
export LOG_NAME="start.out"
export CUSTOM_SEARCH_LOCATIONS=file:/root/pacific-backend/warehouse/config/


cygwin=false
darwin=false
os400=false
case "`uname`" in
CYGWIN*) cygwin=true;;
Darwin*) darwin=true;;
OS400*) os400=true;;
esac
error_exit ()
{
    echo "ERROR: $1 !!"
    exit 1
}
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=$HOME/jdk/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/usr/java
[ ! -e "$JAVA_HOME/bin/java" ] && JAVA_HOME=/opt/taobao/java
[ ! -e "$JAVA_HOME/bin/java" ] && unset JAVA_HOME

echo ${JAVA_HOME}

if [ -z "$JAVA_HOME" ]; then
  if $darwin; then

    if [ -x '/usr/libexec/java_home' ] ; then
      export JAVA_HOME=`/usr/libexec/java_home`

    elif [ -d "/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home" ]; then
      export JAVA_HOME="/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home"
    fi
  else
    JAVA_PATH=`dirname $(readlink -f $(which javac))`
    if [ "x$JAVA_PATH" != "x" ]; then
      export JAVA_HOME=`dirname $JAVA_PATH 2>/dev/null`
    fi
  fi
  if [ -z "$JAVA_HOME" ]; then
        error_exit "Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better!"
  fi
fi


while getopts ":m:s:p:a:" opt
do
    case $opt in
        a)
            ACTIVE=$OPTARG;;
        m)
            MODE=$OPTARG;;
        s)
            SERVER=$OPTARG;;
        p)
            PORT=$OPTARG;;
        ?)
        echo "Unknown parameter"
        exit 1;;
    esac
done

export JAVA_HOME
export JAVA="$JAVA_HOME/bin/java"

JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx2g -Xmn512m"
JAVA_OPT="${JAVA_OPT} -Dwarehouse.standalone=true"
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"
JAVA_OPT="${JAVA_OPT} -XX:-UseLargePages"
JAVA_OPT="${JAVA_OPT}  -Duser.timezone=${TIMEZONE}"
JAVA_OPT="${JAVA_OPT} -Xlog:gc*:file=${BASE_DIR}/logs/warehouse_gc.log:time,tags:filecount=10,filesize=102400"
JAVA_OPT="${JAVA_OPT} -Dwarehouse.home=${BASE_DIR}"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/${SERVER}.jar"
JAVA_OPT="${JAVA_OPT} ${JAVA_OPT_EXT}"
JAVA_OPT="${JAVA_OPT} --spring.config.additional-location=${CUSTOM_SEARCH_LOCATIONS}"
JAVA_OPT="${JAVA_OPT} --server.max-http-header-size=524288"
JAVA_OPT="${JAVA_OPT} --spring.profiles.active=${ACTIVE} --server.port=${PORT}"

echo  "nohup "$JAVA" ${JAVA_OPT} warehouse.warehouse >> ${BASE_DIR}/logs/${LOG_NAME} 2>&1 &"

#echo "warehouse is starting,you can check the ${BASE_DIR}/logs/start.out"