package org.example.mianshi.synctool;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 创建日期:  2020/3/30 14:38
 * 描述:     countDownLatch的例子
 *
 * @author lifuchun
 */

public class CountDownLatchApp {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        new Thread(() -> {

            try {
                countDownLatch.await();

                System.out.println("后置任务");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        IntStream.rangeClosed(1, 10)
                .forEach(i -> new MyThread(countDownLatch).start());


    }

    public static class MyThread extends Thread {

        private CountDownLatch countDownLatch;

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            countDownLatch.countDown();

            System.out.println("前置任务");


        }
    }

}
