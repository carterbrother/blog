# bootdubbo
基于springboot + dubbo封装的微服务框架

## 技术点
maven + springboot + dubbo 

## 分布式组价
定时任务 elastic-job 待测试
缓存     redis
消息队列  kafka
持久化操作  XMyBatis
配置组件    zookeeper
文件上传     filesystem   使用7牛或者阿里云oss存储
分布式配置中心   disconf 
## 功能增强
service-support  健康检查等
rest-support    rest接口增强   完成
dubbo-support   dubbo增强
conf-support    分布式配置中心增强
auth-support    登录封装，单点登录

## 公共组件
commons 

## 日志组件
log4j2

## 部署
jekins + docker + elk 

## 完成排期

V1     commons组件,Rest组件,dubbo组件，文档swagger组件      0522
V2     redis组件，kafka组件，log组件               
V3     定时任务组件，持久化组件，配置中心组件
V4     文件上传组件,健康检查组件，配置中心组件
V5     单点登录，zooker锁组件，demo组件
V6     docker步数脚本，日志中心搭建，jenkins搭建
V7     微服务架构图，开发流程，团队沟通等



# 测试
demo-service  测试rest
demo-job      测试job封装
