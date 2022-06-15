# kafka拦截器

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1588726806200-324e736f-e8c2-4676-9d9f-f8fbf9795b9e.png#align=left&display=inline&height=360&margin=%5Bobject%20Object%5D&name=image.png&originHeight=720&originWidth=1280&size=750786&status=done&style=none&width=640)

导弹拦截，精准防御。

# 背景


拦截器：在不修改应用程序业务逻辑的情况下，一组基于事件的可插拔的逻辑处理链；
类比springMVC的拦截器：

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1588726898886-88efab9c-0e4b-453f-a2ef-3e4e50859f03.png#align=left&display=inline&height=461&margin=%5Bobject%20Object%5D&name=image.png&originHeight=461&originWidth=770&size=29286&status=done&style=none&width=770)





| 拦截时间点 | 说明 |
| --- | --- |
| preHandle | 业务方法调用前 |
| postHandle | 业务方法调用后，返回数据到客户端之前 |
| afterCompletion | 返回数据到客户端之后 |




这些都是通过配置拦截器，插入到应用程序中，实现可插拔的修改业务逻辑；



kafka在0.10.0.0版本中开始引入拦截器。分为生产者拦截器和消费者拦截器，类似责任链的方式编排多个拦截器为一个大拦截器。

配置方法：配置参数

```java

Properties props = new Properties();
List<String> interceptors = new ArrayList<>();
interceptors.add("com.yourcompany.kafkaproject.interceptors.AddTimestampInterceptor"); // 拦截器1
interceptors.add("com.yourcompany.kafkaproject.interceptors.UpdateCounterInterceptor"); // 拦截器2
props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
……
```
| 配置方法 | 说明 |
| --- | --- |
| 生产者拦截器 | interceptor.classes ， 常量： ProducerConfig.INTERCEPTOR_CLASSES_CONFIG
实现类需要实现org.apache.kafka.clients.producer.ProducerInterceptor接口 |
| 消费者拦截器 | 实现类需要实现org.apache.kafka.clients.consumer.ConsumerInterceptor接口
配置方法同生产者拦截器配置方法

 |



注意： 配置拦截器需要制定拦截器的全限定名，并且保证生产者或者消费者客户端能够正确加载到配置的拦截器；

| 使用场景 | 说明 |
| --- | --- |
| 客户端监控 |  |
| 端到端性能检测 | 监控一条消息由生产到消费的延时是kafka用户迫切需要解决的问题；

性能监测代码嵌入到应用代码中不灵活，耦合性太强也不好；

通过拦截器可插拔的配置消息可以快速监控，观测，验证客户端性能指标。 |
| 消息审计 | 多租户私有云消息引擎给全公司提供消息服务：
每条消息是哪个业务方在什么时间发布的
被哪个业务方说明时间点消费掉

通过拦截器实现，强制让所有的生产者，消费者配置该拦截器，实现消息审计的功能； |



# 生产者拦截器


拦截器需要实现org.apache.kafka.clients.producer.ProducerInterceptor

| 时机 | 说明 |
| --- | --- |
| 消息发送之前 | onSend方法里增加装饰内容 |
| 消息提交之后 | onAcknowledgement方法里增加装饰内容 |







# 消费者拦截器
org.apache.kafka.clients.consumer.ConsumerInterceptor

| 时机 | 说明 |
| --- | --- |
| 消息消费之前 | onConsume里增加装饰内容 |
| 提交位移之后 | onCommit方法中增加装饰内容 |






# 实操


实现端到端的性能监控：


处理过程：



| 数据埋点位置 | 说明 |
| --- | --- |
| 生产者发送消息之前 | 更新消息总发送数 |
| 消费者消费消息之前 | 跟新总的消息时长

 消息平均延时 = 总消息消费时长/总消息数量 |





生产者代码：
```java
public class AvgLatencyProducerInterceptor implements ProducerInterceptor<String, String> {


    private Jedis jedis; // 省略Jedis初始化


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        jedis.incr("totalSentMessage");
        return record;
    }


    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    }


    @Override
    public void close() {
    }


    @Override
    public void configure(Map<java.lang.String, ?> configs) {
    }
```


消费者代码：


```java
public class AvgLatencyConsumerInterceptor implements ConsumerInterceptor<String, String> {


    private Jedis jedis; //省略Jedis初始化


    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        long lantency = 0L;
        for (ConsumerRecord<String, String> record : records) {
            lantency += (System.currentTimeMillis() - record.timestamp());
        }
        jedis.incrBy("totalLatency", lantency);
        long totalLatency = Long.parseLong(jedis.get("totalLatency"));
        long totalSentMsgs = Long.parseLong(jedis.get("totalSentMessage"));
        jedis.set("avgLatency", String.valueOf(totalLatency / totalSentMsgs));
        return records;
    }


    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
    }


    @Override
    public void close() {
    }


    @Override
    public void configure(Map<String, ?> configs) 
```


配置到拦截器到对应的生产者和消费者对象，即简单的实现了平均消息延时的端到端性能统计。

# 小结


类比AOP是Spring提供的核心功能，即面向切面编程，可以把跟业务逻辑无关的安全，审计，性能相关功能放到切面增强中实现。
对Kafka进行一些可插拔的功能增强可以通过拦截器实现。


本篇介绍了kafka的拦截器的使用方法，以及通过实例展示了具体的用法，希望对团队使用的kafka做一些增强功能的时候可以利用这个点去扩展。




![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1588744755152-9605db26-56d2-463c-9584-7e6af93bc8c3.png#align=left&display=inline&height=424&margin=%5Bobject%20Object%5D&name=image.png&originHeight=848&originWidth=2034&size=168062&status=done&style=none&width=1017)
