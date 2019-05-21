package com.springx.bootdubbo.common.util;

import java.util.Random;

/**
 * 全局唯一ID生成器
 */
public class IdWorkerUtil {

    //开始该类生成ID的时间截
    private final long startTime = 1463834116272L;

    //机器id所占的位数
    private long workerIdBits = 4L;

    //数据标识id所占的位数
    private long datacenterIdBits = 4L;

    //支持的最大机器id
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    //支持的最大数据标识id
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    //序列在id中占的位数
    private long sequenceBits = 10L;

    //机器id向左移的位数
    private long workerIdLeftShift = sequenceBits;

    //数据标识id向左移的位数
    private long datacenterIdLeftShift = workerIdBits + workerIdLeftShift;

    //时间截向左移的位置
    private long timestampLeftShift = datacenterIdBits + datacenterIdLeftShift;

    //生成序列的掩码
    private long sequenceMask = -1 ^ (-1 << sequenceBits);

    private long workerId;

    private long datacenterId;

    //同一个时间截内生成的序列数，初始值是0，从0开始
    private long sequence = 0L;

    //上次生成id的时间截
    private long lastTimestamp = -1L;

    private IdWorkerUtil(long workerId, long datacenterId) {
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException(
                    String.format("workerId[%d] is less than 0 or greater than maxWorkerId[%d].", workerId, maxWorkerId));
        }
        if (datacenterId < 0 || datacenterId > maxDatacenterId) {
            throw new IllegalArgumentException(
                    String.format("datacenterId[%d] is less than 0 or greater than maxDatacenterId[%d].", datacenterId, maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }


    private long tilNextMillis() {
        long timestamp = timeGen();
        if (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    //生成id
    private synchronized long getNextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //表示是同一时间截内生成的id
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //说明当前时间生成的序列数已达到最大
            if (sequence == 0) {
                //生成下一个毫秒级的序列
                timestamp = tilNextMillis();
                //序列从0开始
                sequence = 0L;
            }
        } else {
            //新的时间截，则序列从0开始
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - startTime) << timestampLeftShift) //时间截部分
                | (datacenterId << datacenterIdLeftShift) //数据标识id部分
                | (workerId << workerIdLeftShift) //机器id部分
                | sequence; //序列部分
    }

    private static IdWorkerUtil instance;
    private static Object locker = new Object();

    private static IdWorkerUtil getInstance() {
        if (instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    Random random = new Random(System.nanoTime());
                    long workerId = random.nextInt(16);
                    long dataCenterId = random.nextInt(16);

                    instance = new IdWorkerUtil(workerId, dataCenterId);
                }
            }
        }

        return instance;
    }

    public static long nextId() {
        return IdWorkerUtil.getInstance().getNextId();
    }
}