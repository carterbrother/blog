package com.springx.bootdubbo;

import com.springx.bootdubbo.zookeeper.lock.core.BLockingWriteLock;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private ZooKeeper zookeeper;

    @Before
    public void setUp() throws Exception {
        zookeeper = new ZooKeeper("localhost:2181", 120, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(String.format("event %s happend", watchedEvent.toString()));
            }
        });
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws KeeperException, InterruptedException {
        BLockingWriteLock bLockingWriteLock = new BLockingWriteLock("a", zookeeper, "/b");

        IntStream.rangeClosed(1, 2000).parallel().forEach(i -> {
            try {
                bLockingWriteLock.lock(3,TimeUnit.SECONDS);

                TimeUnit.SECONDS.sleep(1);

                System.out.println(String.format("任务%执行完毕",i));

            }catch (InterruptedException e1){
                System.out.println("被打断了"+e1.getMessage());
            }catch (Exception ex) {
                System.out.println("获得zk锁异常：" + ex.getMessage());
            } finally {
                try {
                    bLockingWriteLock.unLock();
                } catch (Exception e) {
                    System.out.println("释放zk锁异常：" + e.getMessage());
                }
            }

        });


    }


    @Test
    public void testMutex() {
    }
}
