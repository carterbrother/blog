# 27 新冠攻击人类？什么攻击java平台？

![攻击怪兽](https://cdn.nlark.com/yuque/0/2020/png/186661/1585839138618-1cf65f31-4a23-4c5e-a8fc-6e8fcd3fb957.png#align=left&display=inline&height=435&name=image.png&originHeight=435&originWidth=500&size=327328&status=done&style=none&width=500)
java作为基础平台安装在各种移动设备，PC,小型机，分布式服务器集群，各种不同的操作系统上。所以，对java平台层面的攻击也是最多的。

我是李福春，我在准备面试，今天的题目是：

Java平台的注入攻击有哪些？

答：攻击需要找到安全漏洞，所谓的安全漏洞是：绕过安全系统限制或者利用程序瑕疵。
java平台常见的注入攻击方式如下。

# 注入攻击



| 攻击方式 | 说明 | 防护 |
| --- | --- | --- |
| sql注入攻击 | 非法sql通过参数注入到正常sql后面，拼接成了攻击sql | 1,增加输入参数校验；
2，使用PrepareStatement;
3,给系统使用的数据库用户配置表的读写权限； |
| shell注入攻击 | 利用的是java的Runtime.exec(String)执行shell命令 | 选择的方式传入参数，而非输入 |
| xml注入 | xml含有动态内容，比如xpath使用不当容易造成攻击 |  |
| ldap注入 | 允许动态内容协议，利用特殊的动态命令攻击 |  |
| xss注入 | 跨站点攻击，比如发布一条微博，放入一条读取用户cockie的js代码；正常用户访问的时候，可能该js会把相关的用户信息暴露给攻击者 | 增加入参过滤 |



此外还有中间人攻击：即恶意用户在局域网段发送广播，声称自己是服务器，受害用户直接发送数据包给恶意用户，恶意用户可以劫持受害用户，泄露个人敏感数据。


hash碰撞攻击，利用程序的漏洞，发送一组引起hashTable,hashmap的hash值失效的攻击，消耗完cpu；


# Java的攻击防护


java的平台层面防护如下图：


![java平台的基础防护](https://cdn.nlark.com/yuque/0/2020/png/186661/1585838317977-91149836-f77f-4efa-a73d-4a8d6dd2c4b2.png#align=left&display=inline&height=746&name=image.png&originHeight=1050&originWidth=1050&size=160974&status=done&style=none&width=746)

其次还提供了安全api：
比如加密解密API;
鉴权API;
HTTPS安全通信API；


最后jdk集成了一些安全工具：
keytool集中管理秘钥，整数，keysotore;
还有jarsigner对jar进行签名；

# 小结

本篇回答了java平台的注入攻击的种类，然后介绍了java平台提供的安全防护手段。

![小结脑图](https://cdn.nlark.com/yuque/0/2020/png/186661/1585839001137-6e1acac5-ce3b-4af8-a7ed-5e0f1309a92c.png#align=left&display=inline&height=655&name=image.png&originHeight=1310&originWidth=1216&size=224219&status=done&style=none&width=608)
