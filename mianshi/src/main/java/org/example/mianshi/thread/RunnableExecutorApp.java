package org.example.mianshi.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 作者:     carter
 * 创建日期:  2020/3/30 10:24
 * 描述:     runnable方式启动线程，并等待结束
 */

public class RunnableExecutorApp {

   private static final   ExecutorService threadPool = Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = ()->System.out.println("hello world");
        Object future = threadPool.submit(runnable).get();

    }

}
