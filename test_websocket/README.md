# websocket做实时通知

bi查询,文件上传下载，数据更新   

websocket 旨在服务端主动向客户端推送数据，实现系统向在线用户推送消息，可群发，可对指定用户发送

实现业务对接，通过后台业务对接，推送相对应的业务消息给客户端，客户端处理对应业务的消息
目标


# 工程说明

api-websocket 后端工程，无任何依赖，启动即可； springboot + gradle

vite-page  给予vue3+vite+vue-route写的前端工程，对接websocket
