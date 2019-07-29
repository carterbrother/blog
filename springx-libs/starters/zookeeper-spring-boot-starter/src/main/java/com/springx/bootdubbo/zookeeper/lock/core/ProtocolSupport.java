package com.springx.bootdubbo.zookeeper.lock.core;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * 说明： 协议的基类，提供一些zk和同步重试操作相关的高层次帮助方法，如果zk的连接关闭的时候；
 * @author  carter
 * 创建时间： 2019年07月29日 13:19
 */
@Slf4j
class ProtocolSupport {
    @Getter
    protected final ZooKeeper zooKeeper;

    private AtomicBoolean closed = new AtomicBoolean(false);
    @Getter
    @Setter
    private long retryDelay = 500L;
    private int retryCount = 10 ;
    @Getter
    @Setter
    private List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE;

    public ProtocolSupport(ZooKeeper zooKeeper){
        this.zooKeeper = Objects.requireNonNull(zooKeeper,"zookeeper should not null");
    }

    public void close(){
        if (closed.compareAndSet(false,true)){
            doClose();
        }
    }

    private void doClose() {

    }

    protected Object retryOperation(ILockZooKeeperOperation operation) throws KeeperException,InterruptedException
    {
        KeeperException keeperException = null;

        for (int i=0 ;i< retryCount; i++){
            try {
                return operation.execute();
            }catch (KeeperException.SessionExpiredException e1){
                log.warn("session expire for zookeeper:{} , so reconnectin due to {} ",zooKeeper,e1,e1);
                throw  e1;
            }catch (KeeperException.ConnectionLossException e2){
                if (Objects.isNull(keeperException)){
                    keeperException = e2;
                }
                log.debug("attempt {} , failed with connection los so , attempting to reconnect : {}",i,e2,e2);
                retryDelay(i);
            }
        }

        if (Objects.nonNull(keeperException)){
            throw keeperException;
        }

        return null;

    }

    protected void ensurePathExists(String path){
        ensurePathExists(path,null,aclList, CreateMode.PERSISTENT);
    }

    private void ensurePathExists(String path, final byte[] data, List<ACL> aclList, CreateMode flags) {
        try {
            retryOperation(()->{
                Stat stat = zooKeeper.exists(path,false);
                if (Objects.nonNull(stat)){
                    return true;
                }
                zooKeeper.create(path,data,aclList,flags);
                return true;
            });
        }catch (KeeperException e1){
            log.warn("catch : {} ",e1,e1);
        }catch (InterruptedException e2){
            log.warn("catch : {} ",e2,e2);
        }
    }

    protected  boolean isClosed(){
        return closed.get();
    }


    private void retryDelay(int attemptCount) {
        if (attemptCount > 0){
            try {
                TimeUnit.MILLISECONDS.sleep(attemptCount * retryDelay);
            } catch (InterruptedException e) {
                log.debug("failed to sleep : {} ",e,e);
            }
        }
    }

}
