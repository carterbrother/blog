# 1 为什么要使用微服务？



# 微服务




微服务是一种架构风格：
把单体系统拆分成各种微服务（进程集群里面），服务之间通过HTTP或者RPC协议进行通信。
服务内部是围绕某一个问题领域的业务，有自己单独的业务流程，数据存储，自动化测试，和自动化独立部署机制。




解决单体系统的难题：
开发端：部分业务的修改要修改整个项目， 开发维护成本高，容易出错，不利于团队协作；
运维线：部分业务的上线影响整体服务质量，运维无法精确评估系统资源的需求量；


带来的问题：

1，运维需要维护数量庞大的进程；

2，接口的业务流程拉长，一致性比较更难以控制；

3，分布式的复杂性：网络延迟，异步消息，分布式事务等；


基于敏捷项目管理和自动化部署可以应对这些问题。




# springcloud整体介绍


基于springboot实现的微服务架构开发工具。


提供了这些分布式问题的解决方案：



| 配置管理 | spring-cloud-config 
spring-cloud-archaius
spring-cloud-consul |
| --- | --- |
| 服务治理 | spring-cloud-eurek
spring-cloud-consul |
| 断路器 | spring-cloud-hystrix |
| 智能路由 | spring-cloud-zuul |
| 负载均衡 | spring-cloud-ribbon,
spring-cloud-feign |
| 控制总线 | spring-cloud-bus
spring-cloud-stream |
| 全局锁 | spring-cloud-cluster
spring-cloud-zookeeper |
| 服务跟踪 | spring-cloud-sleuth |



# springboot带来了什么？


1，提供了一个开发微服务的脚手架（idea的initializer创建springcloud的微服务），减少了从0开始搭建项目的问题；


2，并非重写spring或者替代spring,主要是提供了自动化配置简化原有的样板配置


3，快速开发，提供了各种starter集成其它的组件和解决依赖管理问题


4，轻松部署，内置了web容器，轻松跟docker融合；




涵盖了项目的构建，开发，测试阶段；


# springboot快速使用


idea的initializer创建springcloud的微服务


开发一个rest接口


开发接口的单元测试代码


例子代码点我获取！


## 工程结构



| 路径 | 说明 |
| --- | --- |
| src/main/java | XApplication.java是程序的入口
其它的代码放在跟它同级或者下级目录中，会自动扫描到spring的容器中 |
| src/main/resources | 放应用的配置文件application.properties
静态文件 static 
templates(模板文件) |
| src/test/java | 放单元测试代码 |





## 依赖处理


1， parent处理方式


2，dependencyManagemant处理方式




## 运行


1，java -jar x.jar 运行 正式环境


2，idea提供调试运行； 开发环境


3，maven的spring-boot：run插件运行  开发环境；




# springboot配置


自动化配置是springboot最大的亮点。




配置的加载优先级如下：


1，命令行中的参数 ； 


2， 系统环境变量中的SPRING_APPLICATION_JSON配置；


3，JNDI属性： java:comp/env


4，java的操作系统属性 System.getProperties();


5,  操作系统的环境变量


6，jar包外部的 application-${profile}.properties 


7, jar包内部的 application-${profile}.properties 


8， [@Configuration注解修改过的类](#) [@PropertySource注解定义的属性](#) 


9， SpringApplication.setDefaultProperties()

## 多环境配置


application.properties放通用配置，指定激活 dev环境


在其他的环境中提供差异化的配置，发布的时候通过命令行指定环境spring.profiles.active=prod;






# springboot监控


微服务是的进程的数量增多，必须有一套自动化的监控运维机制来收集微服务的运行指标，进行监控和预警。


spring-boot-starter-actuator 来进行监控。


并配置开启的端点。


常见的监控端点： /health /beans /mappings






# 小结

首先宏观上回答了为什么微服务会出现，解决了什么问题？

然后初步介绍了spring-cloud带来了什么？

接着从spring-cloud的基础出发，即springboot分析了springboot带来了什么，简单实用，配置和监控；

springboot带来了什么？


以及快速使用springboot开发接口的过程；


简单介绍了 工程结构，依赖的处理方式 ， 运行指令等细节；


然后基于配置，介绍了配置参数的加载顺序，多环境下的最佳实践。


最后介绍了微服务继续的自动监控和运维机制 actuator ，收集微服务的端点信息。








