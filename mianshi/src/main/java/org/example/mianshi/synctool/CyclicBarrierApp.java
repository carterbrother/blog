package org.example.mianshi.synctool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * 创建日期:  2020/3/30 14:49
 * 描述:     cyclicBarrier的应用
 *
 * @author lifuchun
 */

public class CyclicBarrierApp {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, CyclicBarrierApp::run);

        IntStream.rangeClosed(1, 30)
                .forEach(i -> new MyThread(cyclicBarrier).start());

    }

    private static void run() {
        System.out.println("reset , start again !");
    }

    public static class MyThread extends Thread {

        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("do my work!");

        }
    }

}
