package org.example.mianshi.thread;

/**
 * 作者:     carter
 * 创建日期:  2020/3/30 10:24
 * 描述:     runnable方式启动线程，并等待结束
 */

public class RunnableApp {

    public static void main(String[] args) {

        Runnable runnable = ()->System.out.println("hello world");

        Thread thread = new Thread(runnable);

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
