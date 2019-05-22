/**
 *
 */
package com.springx.bootdubbo.starter.cache.core.lock;

/**
 * @author <a href="mailto:carterbrotherge@gmail.com">carterbrother</a>
 * @description <br>
 * @date 2016年7月22日
 */
public interface LockCaller<T> {

    /**
     * 持有锁的操作
     *
     * @return
     */
    T onHolder();

    /**
     * 等待锁的操作
     *
     * @return
     */
    T onWait();
}
