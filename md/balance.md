# 24 负载均衡



今天的问题是：负载均衡是为了解决什么问题？有哪几种实现负载均衡的方式？


# 解决的问题


> 把用户的请求分发到多台服务器（更多的资源），解决高并发的问题。



# 硬件负载均衡


成本比较高，并发数在百万级别，比如F5;




# 软件负载均衡


## HTTP重定向负载均衡


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1582766234994-e7fc0212-af7c-4649-96ed-4dfce1b9b25d.png#align=left&display=inline&height=434&name=image.png&originHeight=434&originWidth=583&size=57531&status=done&style=none&width=583#align=left&display=inline&height=434&originHeight=434&originWidth=583&status=done&style=none&width=583)




负载均衡过程如上图：


1 请求到负载均衡服务器，


2 根据均衡算法，返回304，重定向到实际负责计算的服务器ip到浏览器，


3 然后浏览器直接请求实际负责计算的服务器，


4 最后实际计算的服务器响应到客户端浏览器。


缺点：


1，请求经过两次服务器（负载均衡服务器，实际计算服务器），增加了响应时间。


2，暴露了实际负载的服务器公网ip到浏览器,安全性比较低。


java代码实现：


```
response.sendRedirct("http://ip/xxxService?parama=a");
```


## DNS负载均衡


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1582766485055-3ed78c8f-b7a3-4a8f-b903-1e5e234f547c.png#align=left&display=inline&height=429&name=image.png&originHeight=429&originWidth=641&size=59032&status=done&style=none&width=641#align=left&display=inline&height=429&originHeight=429&originWidth=641&status=done&style=none&width=641)
负载均衡过程如上图：


1， 用户请求域名，请求到DNS服务器；


2，DNS服务返回解析的IP地址到客户端；并不暴露到浏览器进行重定向；


3，客户端拿到返回的负责计算的服务器IP，请求服务器；


4，计算服务器返回响应信息；


改进：


1，不用每次都请求负载的ip,可以缓存起来，重复使用，提高性能；


2，dns不用暴露实际计算的服务器ip（不是采用重定向的方式暴露在浏览器，而且做了二次负载均衡，内网的IP不会暴露出来）,安全性略好；


异地多活采用的这种方式，一个ip解析到不同区域的IP，实现第一层级的负载均衡，然后基于区域ip做二级的负载均衡；


## 反向代理负载均衡


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1582766715769-3d6c0dcb-2431-4b97-8de3-5f10e92b980c.png#align=left&display=inline&height=416&name=image.png&originHeight=416&originWidth=617&size=54558&status=done&style=none&width=617#align=left&display=inline&height=416&originHeight=416&originWidth=617&status=done&style=none&width=617)


负载均衡过程如下：


1，客户端发起请求到负载均衡服务器，负载均衡服务器根据算法得到负载的IP； 


2，负载均衡服务器构造请求，请求内网负载的计算服务器；


3， 计算服务器返回响应结果到负载均衡服务器；


4，负载均衡服务器返回响应结果给到客户端。




比如nginx, apache


缺点：基于http层做的负载均衡，是一个比较重的协议，效率略低；


一般适用于比较小的集群，10+规模；


## IP层的负载均衡


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1586489163135-19382092-07a8-4850-a9a0-f508dd80a3bc.png#align=left&display=inline&height=356&name=image.png&originHeight=712&originWidth=1244&size=143371&status=done&style=none&width=622)
对网络层的IP地址进行替换，不需要在http层工作，直接在操作系统内核的IP数据包中替换地址。效率比基于HTTP层的反向代理高。
负载均衡过程：


1，客户端请求负载均衡服务器；


2，负载均衡服务器修改目的ip为内网机器的IP ；


3， 内网机器计算完毕，响应的IP改为负载均衡服务器ip的内网地址；


4，负载均衡服务器修改响应的IP为自己的外网IP ,返回结果给到客户端；


缺点：


请求和响应度需要经过负载均衡服务器进行ip层替换，响应数据会成为后期的瓶颈。




## 数据链路层负载均衡
![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1582767046394-2cc89103-aad7-4baa-86d7-9d4ad4a3085d.png#align=left&display=inline&height=349&name=image.png&originHeight=349&originWidth=628&size=42310&status=done&style=none&width=628)
解决响应数据体量过大效率低的问题。
通过修改数据链路层的mac地址，ip使用的是虚拟IP，来实现负载均衡。


负载均衡过程：


1，客户端请求负载均衡服务器；


2，负载均衡服务器替换mac地址为计算服务器，Ip为负载均衡服务器ip；


3， 计算服务器直接响应数据到客户端；




这种负载均衡方式吞吐量最高，大型互联网公司都是采用这种负载均衡方式。


LVS负载均衡是结合了IP层和数据链路层的负载均衡方式。linux通过配置可以实现这两种负载均衡方式。




# 小结


本篇从负载均衡出发的目的出发，即为了应对高并发，把请求分摊到更多的服务器上。


从简单到复杂，依次介绍了HTTP重定向实现负载均衡，DNS实现负载均衡，反向代理实现负载均衡，IP层实现负载均衡，数据链路层实现负载均衡。一图胜千言，给出了负载均衡的过程。


一般常用的是DNS和数据链路层负载均衡。




![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1582767205541-5857129d-cae5-4090-be72-b0051b9903da.png#align=left&display=inline&height=1346&name=image.png&originHeight=1346&originWidth=1902&size=303749&status=done&style=none&width=1902)
