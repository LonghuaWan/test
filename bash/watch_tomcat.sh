#/bin/sh
# mail:wanlonghua@hi-wifi.cn
# date:10/09/2015
export CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
export CATALINA_HOME=/home/admin/tomcat7
export _JAVA_OPTIONS=-Djava.io.tmpdir=/data/tmp
#DEFINE

TomcatID=$(ps -ef |grep tomcat7|grep catalina |grep -v 'grep'|awk '{print $2}')

StartTomcat=$CATALINA_HOME/bin/startup.sh
TomcatCache=$CATALINA_HOME/work

WebUrl=http://localhost:8080/index.html

GetPageInfo=$CATALINA_HOME/logs/TomcatMonitor.info
TomcatMonitorLog=$CATALINA_HOME/logs/TomcatMonitor.log

Monitor()
{
  if [ ! -f $GetPageInfo ];then
      touch $GetPageInfo
  fi
  if [ ! -f $TomcatMonitorLog ];then
      touch $TomcatMonitorLog
  fi
  echo "[info]Monitor tomcat...[$(date +'%F %H:%M:%S')]"
  echo $TomcatID
  if [ $TomcatID ];then
     echo "[info]Tomcat progress ID:$TomcatID"
     TomcatServiceCode=$(curl -s -o $GetPageInfo -m 10 --connect-timeout 10 $WebUrl -w %{http_code})
     if [ $TomcatServiceCode -eq 200 ];then
        echo "[info]Http return code is $TomcatServiceCode,tomcat is working normally."
     else
        echo "[error]Http return code is $TomcatServiceCode,tomcat error see log at $GetPageInfo"
        echo "[error]�restart tomcat"
        kill -9 $TomcatID   
        sleep 3
        #rm -rf $TomcatCache
        $StartTomcat
     fi
  else
     echo "[error]tomcat progress is not exist, start tomcat...."
     echo "[info]$StartTomcat"
     rm -rf $TomcatCache
     $StartTomcat 
  fi
  echo "--------------END----------------"
}
Monitor >>$TomcatMonitorLog
