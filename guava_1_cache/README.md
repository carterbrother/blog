# 需求背景

    有一些资源，比如说数据库连接池，（mysql,mongo,redis）和 第三方服务的连接池(minio)
    或者第三方操作的token有一定时效。

    初始化非常昂贵，所以需要缓存；而且可能存在多个，所以缓存需要支持多个key;

    用完了一段时间之后不操作又非常浪费，所以必须定时的释放，下次再使用，再次初始化加入缓存；

# 实现方式1  static Map + @Scheduled + @Synchronized
    这种方式不需要依赖别的库，springWeb框架包含所有；
    代码示例见: static.map 包下；

# 实现方式2  guavaCache + @Synchronized
    这种方式需要引入guava ，一般的springboot程序都会引用进来；
    代码示例见: cache包下：



# 小结
    有啥问题可以给我留言。



