package org.example.mianshi.synctool;

import java.util.concurrent.Semaphore;

/**
 * 创建日期:  2020/3/30 14:24
 * 描述:     信号量应用
 *
 * @author lifuchun
 */

public class SemaphoreApp {


    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(0);

        for (int i = 1; i <= 10; i++) {
            new MyThread(semaphore).start();
        }

        System.out.println("go ```");
        semaphore.release(5);


    }

    public static class MyThread extends Thread {

        private Semaphore semaphore;

        public MyThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {

            try {

                semaphore.acquire();

                System.out.println(System.currentTimeMillis() + " :" + Thread.currentThread().getName() + " -execute ```");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }


        }
    }


}
