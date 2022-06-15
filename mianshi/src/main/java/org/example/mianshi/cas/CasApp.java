package org.example.mianshi.cas;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 作者:     carter
 * 创建日期:  2020/3/31 10:04
 * 描述:     基于CAS更新数据库的索引
 */

public class CasApp {


    public static void main(String[] args) {

    }

    public static class AtomicBTreePartition{

        private volatile long lock;

        private static final AtomicLongFieldUpdater<AtomicBTreePartition> lockFieldUpdater
                =AtomicLongFieldUpdater.newUpdater(AtomicBTreePartition.class,"lock");

        public void acquireLock(){

            long t = Thread.currentThread().getId();

            while (!lockFieldUpdater.compareAndSet(this, 0, 1)){
                //数据库操作
            }
        }

        public void releaseLock(){

        }

    }

}
