package org.example.mianshi.queue;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * 作者:     carter
 * 创建日期:  2020/3/30 17:39
 * 描述:     生产者消费者例子
 */

public class ConsumerProduceApp {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static class Consumer extends Thread {

        private BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {

            String msg = "";
            do {
                try {
                    msg = blockingQueue.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(msg);

            } while (!Objects.equals(msg, "quit"));

        }
    }

    public static class Producer extends Thread {

        private BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {

            IntStream.rangeClosed(1, 100)
                    .forEach(i -> {
                        try {
                            blockingQueue.put("msg" + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });


            try {
                blockingQueue.put("quit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
