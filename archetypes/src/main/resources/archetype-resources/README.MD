# 运行

配置apollo必须参数

```java
-Denv=beta
-Dapollo.meta=http://beta.eureka.apollo.lexin.com
-Dapp.id=rest-saasuser
```


|配置key|说明|
|---|---|
|env|环境，在apollo中可以配置多个环境的配置管理|
|app.id|在apollo中的应用的名字|
|apollo.meta|获取配置元数据的地址|




apollo的地址需要连接vpn,外网无法访问；
