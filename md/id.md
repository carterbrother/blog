# 31 分布式ID设计方案

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586180430300-cfc2b08e-94b1-4903-a243-bffdbc631d85.png#align=left&display=inline&height=296&name=image.png&originHeight=296&originWidth=640&size=109562&status=done&style=none&width=640)


面试中关于分布式的问题很多。（分布式事务，基本理论CAP,BASE,分布式锁）先来一个简单的。




简单说一下分布式ID的设计方案？


首先要明确在分布式环境下，分布式id的基本要求。


1， 全局唯一，在分布式集群下，不同的节点并发生成的分布式id要唯一；

2， 顺序性，分布式id是有序生成


然后给出分布式id的设计方案。


1， 基于数据的自增id生成分布式ID，使用比较简单，缺点是扩展性和可靠性有限；[基于数据库表的自增id](https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md)

2，基于[snowflake](https://github.com/relops/snowflake/blob/master/src/main/java/com/relops/snowflake/Snowflake.java) 算法生成；


snowflake生成的分布式id是一个64位整数；位数标识如下：


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586177119348-3ff89de1-9920-4d95-bbff-0d309ebb0331.png#align=left&display=inline&height=140&name=image.png&originHeight=280&originWidth=1086&size=42392&status=done&style=none&width=543)


1 标识正负

41位，一般使用System.currentTimeMilles()得到；

5 数据中心标识

5 机器ip标识

12 单位毫秒内可以生成的序数极限


snowflake的生成跟时间相关的使用的是System.currentTimeMilles(),跟冬令时没有关系。


# 分布式id的进一步要求


可靠性： 即高可用
紧凑性： 64位的整数比较长，不太紧凑，作为索引，存储不占优势。
有意义： 可以放入业务标识或者时间


# snowflake的缺点


受时间影响：需要保证分布式集群的时间同步，即NTP ; 
可以预测到：容易按照时间规律预测到，进而影响安全性；




# 小结


回答了分布式id的基本要求，已经常用方案。


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586179314426-a0b007aa-3484-40d8-b3be-7efc29392a2a.png#align=left&display=inline&height=675&name=image.png&originHeight=1350&originWidth=1380&size=188106&status=done&style=none&width=690)
