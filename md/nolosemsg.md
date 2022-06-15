# 11 如何保证kafka消息不丢失



![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587863842505-07cad83b-8fe8-4162-9e88-505064d5b0da.png#align=left&display=inline&height=432&margin=%5Bobject%20Object%5D&name=image.png&originHeight=432&originWidth=720&size=160518&status=done&style=none&width=720)
# 背景
 
这里的kafka值得是broker,broker消息丢失的边界需要对齐一下：

1 已经提交的消息

2 有限度的持久化


如果消息没提交成功，并不是broke丢失了消息；

有限度的持久化（broker可用）


# 生产者丢失消息


`producer.send(Object msg) ;` 
这个发送消息的方式是异步的；fire and forget,发送而不管结果如何；


失败的原因可能有很多，比如网络抖动，发送消息超出大小限制；




怎么破呢？永远使用带有返回值值的消息发送方式，即 `producer.send(msg,callback)` 


通过callback可以准确的告诉你消息是否发送成功了，发送失败了你也可以有处置方法；


网络抖动： 重发

发送消息超出大小：调整消息大小进行发送


这种情况并不是broker丢失消息了，是producer的消息没有提交成功。


# 消费者丢失消息


kafka消费消息的模型:

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587916091297-87a084cb-3237-438c-bbe3-8f3ac8bb0b08.png#align=left&display=inline&height=622&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1243&originWidth=2041&size=142582&status=done&style=none&width=1020.5)


即消费消息，设置好offset，类比一下：



| kafka动作 | 读书动作 |
| --- | --- |
| 消费 | 看书 |
| offset位移 | 书签 |





说明时候消费者丢失数据呢？即先更新位移，再消费消息，如果消费程序出现故障，没消费完毕，则丢失了消息，此时，broker并不知道。


怎么破？总是先消费消息，再更新位移；这种可能带来消息重复消费的问题，但是不会出现消息丢失问题；








## 多线程消费丢失消息


即开启了位移自动提交，多线程处理的时候，如果有一个线程出现问题，但是还是提交了位移，会发生消息丢失。


怎么破？ 关闭自动提交位移，消费者端配置参数：enable.auto.commit=false




# 调优broker参数防止消息丢失


主要通过调整配置来保证kafka消息不丢失。



| 配置参数 | 说明 |
| --- | --- |
| 消费者端enable.auto.commit=false | 禁止手动提交位移，避免多线程消费的时候出现消息丢失 |
| 生产者端acks = all | 即配置所有的partition副本都收到消息了才返回提交消息成功 |
| 生产者端retries=N | 即出现问题比如网络抖动的重试次数 |
| broker端replication.factor >= 3 | 消息分区的副本数多少个 |
| broker端min.insync.replicas > 1 | 消息写入多少个副本才算已提交 |
| broker端replication.factor > min.insync.replicas | 保证可用性，如果相等，则任何一个副本挂了，则整个分区无法工作 |
| broker端unclean.leader.election.enable = false | 数据缺失太多的borker不能成为leader，防止数据丢失 |




然后就是producer发送消息一定要使用带回调函数的方法，并对发送失败的情况进行处理。

同时写consumer程序的时候先消费再提交；


# 小结


本节先限定了kafka消息不丢失是针对broker的，基本要求是：消息已提交到broker,而且至少有一个broker可用；


然后从生产者，消费者，配置参数这三个方面介绍了如何防止kafka的消息丢失。






