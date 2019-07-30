package com.springx.bootdubbo.zookeeper.lock.core;
/*
 * 说明：TODO
 * @author  carter
 * 创建时间： 2019年07月30日 14:35
 */

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Slf4j
public class RetryLock {

    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(100, 3);

    public <R> R doWithRetryLock(String zkServers, String path, Supplier<R> supplier)  throws Exception {

        Assert.isTrue(!Strings.isNullOrEmpty(path), "path不能为空");

        InterProcessMutex mutex = null;
        try (CuratorFramework client = CuratorFrameworkFactory.newClient(Objects.requireNonNull(zkServers, "zkServers connection string can not be null"), retryPolicy);
        ) {
            client.start();
            mutex = new InterProcessMutex(client, String.valueOf(path));
            mutex.acquire();
            return supplier.get();
        }catch (Exception ex) {
            throw  ex;
        }finally
         {
            try {
                if (Objects.nonNull(mutex)) {
                    mutex.release();
                }
            } catch (Exception e) {
                log.error("释放锁异常",e);

            }
        }

    }


    public static void main(String[] args)  {

        RetryLock retryLock = new RetryLock();

        IntStream.rangeClosed(1,200).parallel()
                .forEach(i->{
                    try {
                        String testLockResult = null;
                        while (Strings.isNullOrEmpty(testLockResult)){
                            testLockResult = retryLock.doWithRetryLock("127.0.0.1:2181", "/testLock", () -> {

                                try {
                                    TimeUnit.MILLISECONDS.sleep(i * 20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                return "ok";

                            });
                        }
                        System.out.println(String.format("% 的执行结果是：%s",i,testLockResult));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

    }


}
