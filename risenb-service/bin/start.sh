#!/bin/sh

#获取脚本工作路径
work_dir=$(cd `dirname $0`; pwd)   
#echo $work_dir
 
#最小堆内存
min=512m

#最大堆内存
max=2048m

#程序入口
main=com.risenb.main.Start
pn=risenb_service


#LIB=$work_dir/../lib:$JAVA_HOME/jre/lib/ext
LIB=$work_dir/../lib:$work_dir/../jre1.8.0_101/lib/ext

#设置classPath
export CLASSPATH=$CLASSPATH

for i in $work_dir/../lib/ares-preprocess*.jar;  
  do CLASSPATH=${CLASSPATH}:$i
done

CLASSPATH=${CLASSPATH}:$work_dir/../conf

for i in $work_dir/../conf/*;  
  do CLASSPATH=${CLASSPATH}:$i
done

#echo $CLASSPATH
declare -x PATH="/usr/kerberos/sbin:/usr/kerberos/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/usr/X11R6/bin:/usr/lib/jdk/bin:/root/bin"
app=`ps aux | grep $pn | grep -v grep | wc -l` 

#echo $app
if [ "$app" -eq 0 ]
 then 
 {
	#设置JRE
	export PATH=$work_dir/../jre1.8.0_101/bin:$PATH
	java -Dpn=$pn -Djava.ext.dirs=$LIB -cp $CLASSPATH -Xms$min -Xmx$max $main  >/dev/null &
 }
 else
   echo  "Service is already started, exit"
fi
