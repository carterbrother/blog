# 微内核架构

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587988663638-df1a829a-a0b3-4493-871e-ee5c4624de2d.png#align=left&display=inline&height=345&margin=%5Bobject%20Object%5D&name=image.png&originHeight=345&originWidth=500&size=344282&status=done&style=none&width=500)


如何设计出核心功能稳定，业务功能不断扩展的软件系统，我想到了微内核架构。

# 定义


微内核架构又叫做插件架构，是面向功能进行拆分的可扩展架构。


比如：Eclipse插件，Linux ； 




# 架构图


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587986283309-64505482-a0b8-493f-83af-602925b61209.png#align=left&display=inline&height=267&margin=%5Bobject%20Object%5D&name=image.png&originHeight=267&originWidth=397&size=57113&status=done&style=none&width=397)





| 组成部分 | 说明 |
| --- | --- |
| 核心系统 core system | 与业务无关的通用功能，非常的稳定
比如插件的加载，插件之间的通信 |
| 插件   plugin component | 实现具体的业务逻辑
即把变化封装在插件中，而不影响整体系统的稳定
 |


## 插件管理


插件注册表机制：名字，位置，加载时机；


## 插件连接


指的是插件如何连接到核心系统。



| 插件连接机制 | 说明 |
| --- | --- |
| OSGI |  |
| 消息队列 |  |
| SpringIOC |  |
| RPC |  |
| HTTP |  |

<a name="q3q75"></a>
## 插件间通信


插件之间是解耦的，插件之间通信必须通过核心系统，核心系统提供插件的通信机制；




# OSGI
Open Services Gateway initiative  主动开放服务网关；
OSGi 具备动态化、热插拔、高可复用性、高效性、扩展方便等优点；

最具代表性的是Eclipse的插件，采用的是OSGI规范设计规范。

OSGI框架

| OSGI框架 | 说明 |
| --- | --- |
| apache FLIX |  |
| spring DM |  |
| Equinox | eclipse使用的OSGI框架 |



OSGI架构图

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587987348224-efc08e3f-d94d-450d-800c-14ba0ea33687.png#align=left&display=inline&height=373&margin=%5Bobject%20Object%5D&name=image.png&originHeight=373&originWidth=273&size=50123&status=done&style=none&width=273)

| 层 | 说明 |
| --- | --- |
| Module | 实现插件管理功能
里面的插件叫做Bundle
每个Bundle是一个jar包
jar包中含有MANIFEST.MF，里面描述了插件的信息 |
| Lifecycle | 实现插件连接功能

定义了 Bundle 生命周期的操作（安装、更新、启动、停止、卸载） |
| Service | 实现插件间通信功能
内部有一个服务注册中心，插件需要互相调用从注册中心中找到其它的插件； |



# 规则引擎
属于微内核架构的一种实现，执行引擎对应core system核心系统（微内核），执行引擎执行配置好的业务流程，规则，通过这种方式来支持业务的灵活多变。




工作场景：


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587987934746-cd1691c8-6cff-423a-95ed-0329cc2b2267.png#align=left&display=inline&height=529&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1058&originWidth=1799&size=402439&status=done&style=none&width=899.5)
工作流程：

| 序号 | 动作 |
| --- | --- |
| 1 | 开发人员拆分最小单元的规则，保存到规则库中 |
| 2 | 业务人员根据业务编排已经存在的规则，保存在业务库中 |
| 3 | 规则引擎执行业务流程实现业务功能 |







对应微内核架构的插件核心：



| 插件核心 | 说明 |
| --- | --- |
| 插件管理 | 规则即插件，引擎即内核，规则被引擎加载和执行，规则一般放在数据库中 |
| 插件连接 | 规则语言 |
| 插件间通信 | 规则之间不直接通信，规则之间通过数据流和事件流通过引擎进行通信 |





代表产品 jboss drools, 基于 rete算法实现规则引擎，他的规则语言接近编程语言，一般包装成可视化界面来间接生成。






# 小结


本篇首先定义了微内核架构，即 面向功能拆分的可扩展架构。


以及他的组成部分，即内核和插件。


以及它需要解决的核心问题，插件管理，插件连接，插件间通信；


最后介绍了两种微内核架构，OSGI 和规则引擎 ； 


