# 10 kafka高吞吐量之消息压缩


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587743483393-25f38e3d-6e7d-4539-976c-40161a570fa1.png#align=left&display=inline&height=321&margin=%5Bobject%20Object%5D&name=image.png&originHeight=321&originWidth=500&size=304644&status=done&style=none&width=500)

# 背景


保证kafka高吞吐量的另外一大利器就是消息压缩。就像上图中的压缩饼干。


压缩即空间换时间，通过空间的压缩带来速度的提升，即通过少量的cpu消耗来减少磁盘和网络传输的io。










# 消息压缩模型


消息格式V1


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587741795616-87689eb4-6ff5-4507-ba18-32516245afe4.png#align=left&display=inline&height=187&margin=%5Bobject%20Object%5D&name=image.png&originHeight=374&originWidth=1382&size=22741&status=done&style=none&width=691)


kafka不会直接操作单条消息，而是直接操作一个消息集合。








消息格式V2:


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1587742146866-a0e8681d-5a13-443d-b136-b7fc1394ca1a.png#align=left&display=inline&height=141&margin=%5Bobject%20Object%5D&name=image.png&originHeight=282&originWidth=1354&size=29841&status=done&style=none&width=677)










1, 抽取了消息的公共部分放到消息集合中；去掉每条消息的公共部分，减少了总体积。


2，消息的CRC校验由对每一条消息，移动到了对消息集合进行校验，减少了校验次数，节省了cpu;


3, 对单个消息进行压缩，放到消息的body字段 pk 对消息集合整个进行压缩 更好的压缩效果；








压缩过程模型









| 端 | 动作 | 配置参数和说明 |
| --- | --- | --- |
| producer | 压缩 | compression.type=gzip |
| broker | 解压缩，压缩 | compression.type=gzip
一般情况，直接原样保存；
常规会原样解压缩，进行校验，然后
例外情况：会进行解压缩，然后重新压缩
1，producer端和broker端配置的压缩算法不一样；
2，消息格式转换；（丧失了zerocopy的优势）

 |
| consumer | 解压缩 | 直接获取压缩消息，从消息头中获取解压缩算法，进行解压缩处理 |









# 压缩算法比较


如何衡量一个压缩算法的好坏。



| 指标 | 说明 |
| --- | --- |
| 压缩比 | 原文件和压缩文件体积比例 |
| 压缩解压缩吞吐量 | 压缩，解压缩的速度 |







常见的压缩算法对比：


Zstandard 算法（简写为 zstd）。它是 Facebook 开源的一个压缩算法，能够提供超高的压缩比



| 对比维度 | 结果 |
| --- | --- |
| 压缩比 | zstd > LZ4 > GZIP > Snappy |
| 压缩解压缩吞吐量 | LZ4 > Snappy > zstd  GZIP |





启用压缩场景




如果cpu负载比较高，不适合启用压缩；


如果带宽不足，而cpu负载不高，最适合启用压缩，节约大量的带宽；


尽量避免消息格式不一致带来的解压缩消耗。




# 小结




压缩的目的是较少空间占用，带来传输速度的提升，但是需要消耗一定的cpu ；


是一种提高kafka消息吞吐量的有效办法。


本节回顾了新版的kafka是如何对消息进行压缩的，压缩和解压缩的流程是怎样的，


然后对比了常见的4种压缩算法，根据具体的使用场景来选择是否启用压缩，以及选择合适的压缩算法。


然后给出了压缩的配置参数，在producer和borker端都可以使用compression.type来设置。






