# 28 如何写出安全的java代码

![防护口罩](https://cdn.nlark.com/yuque/0/2020/png/186661/1585901874676-5cbbfc60-95ec-444b-94e9-0b06566341b1.png#align=left&display=inline&height=500&name=image.png&originHeight=500&originWidth=500&size=436788&status=done&style=none&width=500)


对jdk，jvm，java应用程序的攻击多种多样？那么从java程序员的角度，如何写出安全的代码呢？


我是李福春，我在准备面试，今天的题目是：如何写出安全的java代码？

答：这个需要从功能设计到实现细节综合考虑，所谓的不安全，是攻击者利用jdk，jvm，java应用程序的瑕疵，或者是架构设计的缺陷进行攻击。


以dos攻击作为例子，分析一下如何写出安全的java代码。


dos攻击是攻击者利用大量的机器发送请求，把目标java应用的的带宽或者其它资源（cpu,内存，磁盘）耗尽，导致正常用户无法正常访问。攻击方式举例如下：


1，早期的jdk版本，利用可以设置线程的优先级，攻击者让大量线程做一些消耗资源的事情，这个可以通过升级jdk版本解决。

2，一些网站的cpu密集型接口没有限制用户的使用，攻击者滥用这些接口，耗尽cpu资源；解决方法：限制用户使用cpu密集型的接口频率或者时间。

3，文件上传没有限制，攻击者会耗尽系统的内存和外存；解决方法：限制用户的文件上传次数和频率

4，其它资源的消耗，比如文件描述符，数据库连接，再入锁没有及时释放，解决办法：显示释放资源，try-finally；

# 安全代码

在开发阶段指定安全代码的规范，可以参考ali的java规范手册。下面举几个例子说明一下安全问题的出现场景。

## 防止数值类型溢出
```java
// a, b, c都是int类型的数值
if (a + b < c) {
// …
}
```


改进


```java
if (a < c – b)
```


## 避免暴露敏感信息


```java
try {
// 业务代码
} catch (Exception e) {
throw new RuntimeException(hostname + port + “ doesn’t response”);

```

改进：不输出敏感信息到异常中；

## 序列化

序列化一般出现在系统之间信息交换的场景，主要避免敏感数据的泄露。

1，发送出去的时候，对敏感字段增加transiant字段保护起来，防止被序列化；

2，接受信息的时候，在readObject方法中药实现相同的安全和数据检查；

开发完成之后，可以使用相关的插件进行代码扫描，比如findbugs，第一时间发现可能的安全漏洞，修复不安全的代码段。

也可以结合团队安全部门的规范和工具放代码质量扫描系统，比如sonar;

部署java程序之后，要针对使用的jdk版本，及时的更新jdk的版本，升级版本自动的修复了很多针对java的漏洞；

# 小结

本篇回答了java的攻击产生的主要原因，然后以dos攻击威力，列举了几种攻击的情况。

结合软件研发的生命周期，在不同的阶段给出了一些写出安全java代码保证java应用安全的工具和建议。

![小结脑图](https://cdn.nlark.com/yuque/0/2020/png/186661/1585901826993-3f35cd44-a2af-44fa-bf9b-6c593420a33c.png#align=left&display=inline&height=1270&name=image.png&originHeight=1270&originWidth=1664&size=255960&status=done&style=none&width=1664)
