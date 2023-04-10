package com.carter.demo.niuke;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 两个线程分别打印奇数和偶数
 * @author Carter.li
 * @createtime 2023/4/7 14:06
 */
public class TestThread {

    public static void main(String[] args) {




         ReentrantLock lock = new ReentrantLock(true);

         Thread ou = new Thread(new Runnable(){

             @Override
             public void run() {
                 int start=1,end=100;
                 for (;start<=end;start++){

                            synchronized (TestThread.class){


                             if (start%2 ==0){
                                 System.out.println(Thread.currentThread().getName()+ " 打印偶数 ： "+start);
                             }

                            }



                 }

             }
         });


        Thread ji = new Thread(new Runnable(){

            @Override
            public void run() {
                int start=1,end=100;
                for (;start<=end;start++){

                    synchronized (TestThread.class) {
                        if (start % 2 == 1) {
                            System.out.println(Thread.currentThread().getName() + " 打印奇数 ： " + start);
                        }
                    }


                }

            }
        });


        ji.start();
        ou.start();

    }


    public static class MyThread extends Thread{


        private Integer num=0;

        private ReentrantLock lock;

        public MyThread(ReentrantLock lock){
            this.lock = lock;
        }





        @Override
        public void run() {

            while (true){
            if(lock.tryLock()){
                try{
                    if (num>0){
                        System.out.println(num);
                    }
                }finally {
                    lock.unlock();
                    num=0;
                }
            }else {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


            }
        }


        public void setNum(Integer num) {
            this.num = num;
        }


    }

}
