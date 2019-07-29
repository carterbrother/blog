package com.springx.bootdubbo.zookeeper.lock.core;
/*
 * 说明：锁的监听接口
 * @author  carter
 * 创建时间： 2019年07月29日 11:26
 */

 interface ILockListener {
    /**
     * 当锁定的时候回调该方法
     */
    void lockAcquired();

    /**
     * 当释放锁的时候回调该方法
     */
    void lockReleased();

}
