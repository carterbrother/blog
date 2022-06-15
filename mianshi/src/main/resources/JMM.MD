# 24  简单介绍一下JMM（java妹妹）？

![](https://cdn.nlark.com/yuque/0/2020/png/186661/1585639487746-8fb3f94b-fc6b-453e-8df4-da2dfd826733.png#align=left&display=inline&height=375&name=image.png&originHeight=375&originWidth=589&size=31033&status=done&style=none&width=589)

java提供的自动垃圾收集机制大大提高了程序员的开发效率。
但是自动垃圾收集不是万能的，明确jvm的内存结构，工作机制是设计高扩展应用的基础。
也是诊断jvm运行时问题的必备技能。

我是李福春，我在准备面试，今天的题目是：

**jvm的内存结构是怎样的？哪些区域容易发生OOM?**

答：分3部分回答，具体如上图所示：
1，线程私有的：分为虚拟机栈，本地方法栈，程序计数器；
2，线程共享的：分为堆，方法区（元数据，常量池）
3，jvm之外的：分为直接内存和 code cache ;

然后为每个部分分别展开说明一下。
虚拟机栈： 每个线程的一次方法执行都对应一个虚拟机栈的栈帧，包含方法入参，操作数，动态链接，方法正常结束或者异常退出的定义
本地方法栈：线程调用本地方法对应一个本地方法栈，跟虚拟机栈在同一块区域；
程序计数器：记录线程正在执行的jvm指令地址
堆：java的对象生成区域，所有的线程共享，是垃圾收集的重点照顾区域，可以进一步分为新声代，老年代，xms设置的是堆大小；
方法区：放了java的类对象定义，以及常量池；



发生OOM的区域如图所示，除了程序计数器部分，其它的部分都可能直接或者间接的引起OOM（OutOfMemoryError）;

分别说明如下：

1，堆， 如内存泄露，堆大小设置不合理，运行期间对象的回收不及时，都可能引起堆的OOM；

2，栈：栈的无限递归调用直接导致StackOverFlowError,jvm会重新扩展栈空间，引起OOM；

3，方法区：老版本常量池的回收非常不积极；比如字符串缓存堆积；

4，给的元数据空间不足以及直接内存不足也是可能引起OOM;



# 小结

本节画了java的jvm的内存结构图，分别分析了每一块的用途；

然后针对每一块可能产生OOM的原因做了分类。


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1585640588921-517baaf2-b741-4076-bcf5-a04536223f5d.png#align=left&display=inline&height=725&name=image.png&originHeight=1220&originWidth=1500&size=202280&status=done&style=none&width=892)
