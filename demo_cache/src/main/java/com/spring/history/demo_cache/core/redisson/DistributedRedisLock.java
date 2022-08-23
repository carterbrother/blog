package com.spring.history.demo_cache.core.redisson;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/1/27 14:57
 * @Author by Quine
 */
public class DistributedRedisLock {

    private static Logger log = LoggerFactory.getLogger(DistributedRedisLock.class);
    //从配置类中获取redisson对象
    private static Redisson redisson = RedissonManager.getRedisson();

    private static final String LOCK = "lock:";

    //加锁
    public static boolean acquire(String lockName,long waitTime,long leaseTime,TimeUnit timeUnit) throws InterruptedException {
        //声明key对象
        String key =  LOCK + lockName;
        //获取锁对象
        RLock myLock = redisson.getLock(key);

        boolean b = myLock.tryLock(waitTime, leaseTime, timeUnit);
        return  b;
    }
    //锁的释放
    public static void release(String lockName){
        //必须是和加锁时的同一个key
        String key =  LOCK + lockName;
        //获取所对象
        RLock myLock = redisson.getLock(key);
        //释放锁（解锁）
        if(myLock.isLocked()){ // 是否还是锁定状态
            if(myLock.isHeldByCurrentThread()){ // 是当前执行线程的锁
                myLock.unlock();//释放锁
            }
        }

    }

}
