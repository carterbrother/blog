# 9 kafka消息分区机制原理

# 背景
kafka如何支撑海量消息的集中写入？

答案就是消息分区。


核心思想是：负载均衡，采用合适的分区策略把消息写到不同的broker上的分区中；


其它的产品中有类似的思想。

比如monogodb, es 里面叫做 shard;   hbase叫region,  cassdra叫vnode;




# 消息的三层结构


如下图：
 
 即  topic -> partition -> message ; 
 
![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587564037229-a01bdbcd-f2c5-4c49-aee3-0695f372c501.png#align=left&display=inline&height=265&margin=%5Bobject%20Object%5D&name=image.png&originHeight=530&originWidth=894&size=73860&status=done&style=none&width=447)

topic是逻辑上的消息容器；


partition实际承载消息，分布在不同的kafka的broke上；


message即具体的消息。
# 分区策略


## round-robin轮询

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587566486501-386bfa59-30f3-4dd0-91a3-6c903abdc7b1.png#align=left&display=inline&height=243&margin=%5Bobject%20Object%5D&name=image.png&originHeight=486&originWidth=1172&size=87821&status=done&style=none&width=586)


消息按照分区挨个的写。




## randomness随机分区


随机的找一个分区写入，代码如下：

```java
List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
return ThreadLocalRandom.current().nextInt(partitions.size());
```

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587566567078-c7adca88-675c-41fb-ad90-22dc14ba4746.png#align=left&display=inline&height=215&margin=%5Bobject%20Object%5D&name=image.png&originHeight=430&originWidth=1158&size=88165&status=done&style=none&width=579)


## key


相同的key的消息写到固定的分区中

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587566894588-7025e7e4-fd5f-48f0-9b5a-3d61ead70284.png#align=left&display=inline&height=224&margin=%5Bobject%20Object%5D&name=image.png&originHeight=448&originWidth=1132&size=96840&status=done&style=none&width=566)


## 自定义分区


必须完成两步：

1，自定义分区实现类，需要实现org.apache.kafka.clients.producer.Partitioner接口。


主要是实现下面的方法：


```java
int partition(String topic, Object key, byte[] keyBytes, 
              Object value, byte[] valueBytes, Cluster cluster);
```


比如按照区域分区。


```java
List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
return partitions.stream().filter(p -> isSouth(p.leader().host()))
    .map(PartitionInfo::partition).findAny().get();
```


2，显示配置生产者端的参数partitioner.class为具体的类




系统默认：如果消息有key,按照key分区策略，否则按照轮询策略。


# 小结


kafka的分区实现消息的高吞吐量的主要依托，主要是实现了写的负载均衡。可以指定各种负载均衡算法。
负载均衡算法非常重要，需要极力避免消息分区不均的情况，可能给消费者带来性能瓶颈。


小结如下：


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587567593728-f8e8b9ed-b443-454b-a58c-608c2ea68945.png#align=left&display=inline&height=686&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1372&originWidth=1844&size=306355&status=done&style=none&width=922)
