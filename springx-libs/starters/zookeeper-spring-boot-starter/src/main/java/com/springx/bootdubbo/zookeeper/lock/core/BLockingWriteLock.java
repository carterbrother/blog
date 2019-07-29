package com.springx.bootdubbo.zookeeper.lock.core;
/*
 * 说明：TODO
 * @author  carter
 * 创建时间： 2019年07月29日 14:05
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BLockingWriteLock {

    private String _name;
    private String _path;
    private WriteLock writeLock;
    private CountDownLatch _lockAcquieredSignal = new CountDownLatch(1);

    public static final List<ACL> DEFAULT_ACL = ZooDefs.Ids.OPEN_ACL_UNSAFE;


    public BLockingWriteLock(String name, ZooKeeper zooKeeper,String path){
        this(name,zooKeeper,path,DEFAULT_ACL);
    }

    public BLockingWriteLock(String name, ZooKeeper zooKeeper, String path, List<ACL> aclList) {
        _name = name;
        _path = path;
        writeLock = new WriteLock(zooKeeper,path,aclList,new SyncLockListener());
    }

    public void lock() throws KeeperException,InterruptedException {
        writeLock.lock();
        _lockAcquieredSignal.await();

    }


    public boolean lock(long timeout, TimeUnit timeUnit) throws KeeperException,InterruptedException{
        writeLock.lock();
        return  _lockAcquieredSignal.await(timeout,timeUnit);
    }

    public boolean tryLock() throws KeeperException,InterruptedException{
       return lock(1,TimeUnit.SECONDS);
    }

    public void unLock() throws KeeperException,InterruptedException{
        writeLock.unLock();
    }



    private class SyncLockListener implements ILockListener{

        /**
         * 当锁定的时候回调该方法
         */
        @Override
        public void lockAcquired() {
            log.debug("lock acquired by {} on {} ",_name,_path);
            _lockAcquieredSignal.countDown();
        }

        /**
         * 当释放锁的时候回调该方法
         */
        @Override
        public void lockReleased() {
            log.debug("lock released  by {} on {} ",_name,_path);
        }
    }
}
