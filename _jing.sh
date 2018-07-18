#!/bin/sh
#export jingdir="C:\Users/jetevenard/Desktop/REPOS/jing-trang_ELS-FORK"
#export CLASSPATH="$jingdir/build/jing.jar"
#for filename in $jingdir/lib/*.jar; do
#	CLASSPATH+=";$filename"
#done
#echo $CLASSPATH
export CLASSPATH="C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\build\jing.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\ant.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\ant-googlecode.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\ant-launcher.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\ant-nodeps.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\ant-trax.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\doccheck.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\gson-2.8.2.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\isorelax.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\javacc.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\resolver-2.9.1.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\saxon9.8he.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\serializer.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\testng.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\xalan-2.7.2.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\xercesImpl-2.9.1.jar;C:\Users\jetevenard\Desktop\REPOS\jing-trang_ELS-FORK\lib\xml-apis.jar"
java com.thaiopensource.relaxng.util.Driver "$@"
