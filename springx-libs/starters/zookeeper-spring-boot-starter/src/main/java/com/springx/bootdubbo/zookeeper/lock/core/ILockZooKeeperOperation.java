package com.springx.bootdubbo.zookeeper.lock.core;
/*
 * 说明：回调接口尅用在{@link ProtocolSupport}的重试操作
 * @author  carter
 * 创建时间： 2019年07月29日 13:11
 */

import org.apache.zookeeper.KeeperException;

 interface ILockZooKeeperOperation {

    /**
     * 执行操作，可能会被调用多次如果操作的时候zk链接关闭
     * @return 操作的结果或者null
     * @throws KeeperException
     * @throws InterruptedException
     */
    boolean execute() throws KeeperException,InterruptedException;

}
