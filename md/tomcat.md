# 0408 tomcat的性能调优

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586308183887-34665a89-0037-48f4-931a-ef7f50b6e532.png#align=left&display=inline&height=300&name=image.png&originHeight=300&originWidth=575&size=139571&status=done&style=none&width=575)
# 背景
java程序员的开发的java应用程序，一般都会选择使用tomcat发布，但是：
如何充分的掌控tomcat,并让它发挥最优性能呢？


这也是面试的热点问题，结合多年的工作实践，今天总结一下。


# tomcat的使用


## 下载


现在最新的稳定版本是tomcat9, 下载页面：[https://tomcat.apache.org/download-90.cgi](https://tomcat.apache.org/download-90.cgi)


5种下载包的区别



| 下载包种类 | 说明 |
| --- | --- |
| core | 一般下载的版本，用来运行tomcat,提供了管理tomcat的内置应用，
manager,
host-manager:
ROOT:默认页面 |
| full document | tomcat的所有的文档 |
| Deployer | 公用tomcat部署多个应用 |
| Embedded | 内嵌式tomcat, springboot就是使用的内嵌tomcat
只提供了一些jar包
 |
| Source code | 带tomcat的源码和相关的构建资源 |





一般我们选择的是 core包运行tomcat,或者直接选择docker的镜像来运行；


tomcat的目录说明：



| 目录 | 说明 |
| --- | --- |
| bin | 启动脚本，其它功能脚本 |
| lib | tomcat的内部核心jar包 |
| conf | 配置文件目录 |
| webapps | 应用程序目录 |
| logs | 日志目录 |
| temp | 临时文件目录 |
| work | 运行时文件目录 |
| 其它重要文件 | building.txt 如何从源码构建一个tomat包
running.txt 如何运行Tomcat，设置环境变量
release-notes 发布版本说明
listense 证书
notice 注意事项
 |









## 安装


直接解压即可,解压指令：`tar -zxvf tomcat-xxx.tar.gz ` 


## 启动和停止


按照running.txt中的说明指导， 两种方式启动：


1， `sh ${catalina.home}/bin/startup.sh` 
2,  `sh ${catalina.home}/bin/catalina.sh start ` 


对应的两种方式停止tomcat：


1， `sh ${catalina.home}/bin/shutdown.sh`
2,  `sh ${catalina.home}/bin/catalina.sh stop `




## 日志


tomcat产生的日志分成4类


1， catalina.date.out 最近的所有级别的日志；
2，localhost-date.log 错误日志


实时查看日志指令： `tail -f catalina.out`  

## AJP协议


一般用在tomcat跟其它HTTP服务器建立连接。


比如Apache+Tomcat做动静态分离：


apache处理所有的静态资源；


apache通过JK（负载均衡组件）转发动态资源请求到Tomcat,通过AJP协议。






# tomcat的监控


保留默认tomcat下的webapps的 ROOT, host-manager , manager 应用，就可以监控单个tomcat节点的状态。


默认是不可以访问的，需要增加用户和权限才能看到，否则会报403；


增加方法： `conf/tomcat-user.xml` 


```
<role rolename='admin' />
...
<user username='admin' password='admin' roles='admin,admin-gui,admin-script,
manager-script,manager-gui,manager-jmx,manager-status' />
```


监控页面如下图：


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586389634902-48b6457d-9b6d-4b19-b47f-db4f80438d02.png#align=left&display=inline&height=373&name=image.png&originHeight=746&originWidth=2218&size=279868&status=done&style=none&width=1109)
server status: 可以看到tomcat和jvm的版本信息，jvm的分区信息，tomcat内部线程池状态；
![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586389728985-b28415a8-9f7f-44b5-ae24-84db5e5489c7.png#align=left&display=inline&height=726&name=image.png&originHeight=1452&originWidth=2480&size=353454&status=done&style=none&width=1240)


manager-app: 管理tomcat下运行的应用，提供控制按钮，启动，停止，重启，卸载，以及不停服安装新的应用；
![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586389833450-9c1221cd-7110-476d-9ce2-7bfb49d51899.png#align=left&display=inline&height=652&name=image.png&originHeight=1304&originWidth=2362&size=305621&status=done&style=none&width=1181)


host-manger:提供了虚拟主机的管理，即配置别名和二级路径到tomcat的应用。
![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586389942796-0ab23c58-9bb8-4313-afa5-e9e46e13abef.png#align=left&display=inline&height=715&name=image.png&originHeight=1430&originWidth=2428&size=233507&status=done&style=none&width=1214)


# tomcat的IO调优


tomcat9中默认使用的nio处理java的io.
可以从日志中和配置文件中看到。


```
09-Apr-2020 07:46:27.606 信息 [main] org.apache.coyote.AbstractProtocol.start 
开始协议处理句柄["http-nio-8080"]
```


## APR优化IO
使用apr（Apache Portable Runtime）,从操作系统层面解决了异步io的问题，可以大幅度提高性能。
如果linux安装了apr和tomcat-native,则tomcat启动就支持了apr; 


## NIO优化老版本的BIO
老版本的tomcat如果采用了BIO(通过日志可以看出),可以调整为NIO，调整方法：
`conf/server.xml` 


老的配置：


```
<connector protocol="HTTP/1.1" />
```


新的配置：


```
//tomcat6选择nio1
<connector protocol="org.apache.coyote.http11.Http11NioProtocol" />
//tomcat8选择nio2,apr性能更好
<connector protocol="org.apache.coyote.http11.Http11Nio2Protocol" />

<connector protocol="org.apache.coyote.http11.Http11AprProtocol" />
```


# tomcat的线程池调优


tomcat默认不启用线程池，可以启用线程池提高线程的利用率


线程池参数：



| 参数名 | 说明 |
| --- | --- |
| maxThreads | 最大并发数，相当于线程池内部的maxPoolSize |
| minSpareThreads
 | 初始化线程数，相当于线程池的核心线程数
prestartminSpareThreads(开关，trueminSpareThreads才生效) |
| maxIdleTime | 超出核心线程数的线程的最大空闲时间，如果超过这个时间，线程会回收 |
| maxQueueSize | 等待同步队列的大小，相当于线程池的队列容量 |





## 定义线程池
```
    <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
        maxThreads="150" minSpareThreads="4"/>
```




## 配置Connector启用


```
<connector executor="tomcatThreadPool">
```




connector参数



| 参数名 | 说明 |
| --- | --- |
| connectionTimeout | 接受连接之后的等待时间 |
| maxConnections | 最大连接数 |
| enableLookups | true 允许dns查询 |
| maxPostSize | url参数最大容量，默认是2M |
| maxHttpHeaderSize | 请求头的最大容量，默认8K |
| compression | true启用gzip压缩 |
| disableUploadTimeout | 上传超时时间 |
| connectionLinger | 持续使用socket多久才关闭连接 |
| tcpNoDelay | 服务套接字无延迟  |
| urlEncoding | 连接使用的编码，可解决乱码问题 |





# tomcat的jvm参数调优


## GC优化

```
#gc优化
JAVA_GC="-XX:SurvivorRatio=10 
-XX:MaxTenuringThreshold=15 
-XX:NewRatio=2 
-XX:+DisableExplicitGC 
-Djava.security.egd=file:/dev/./urandom"

```



| 参数 | 说明 |
| --- | --- |
| -XX:SurvivorRatio | 年轻代中eden和survivor的比例 |
| -XX:MaxTenuringThreshold | 垃圾回收的门阀值 |
| -XX:NewRatio=2 | 新生代和老年代的比例 |
| -XX:+DisableExplicitGC  | 禁止代码中显示调用gc，让jvm自动垃圾回收 |









## jvm和线程池优化


```
JVM_LEVEL="info"
JVM_Xms="100m"
JVM_Xmx="2048m"
JVM_Xmn="600m"
JVM_Xss="256k"
TOMCAT_acceptCount=4096 线程可以接受的请求数量
TOMCAT_maxThreads=512 最大线程数
TOMCAT_minSpareThreads=512 初始线程数
```



| 属性 | 说明 |
| --- | --- |
| -Dlog.level | 设置tomcat的默认日志级别 |
| -Dxms -Dxmx | jvm使用的堆最小值和最大值，一般设置为一样，避免垃圾回收之后重新分配内存 |
| -Dxss | 每个线程的栈空间大小 |
| -Dxmn | 设置堆的年轻代大小 |





