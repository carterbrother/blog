package com.carter.demo.niuke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class CompanyManagementSystemApplication {
    public static String readFile(String FilePath) throws IOException {
        BufferedReader br = null;
        String str = null;
        StringBuilder strb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(FilePath));
            while ((str = br.readLine()) != null) {
                strb.append(str).append("\n");
            }
        } catch (FileNotFoundException f) {
            System.out.println(FilePath + " does not exist");
            return null;
        } catch (IOException e) {
        } finally {
            if (br != null) {
                br.close();
            }
        }
        String result = strb.toString();
        int length = result.length();
        return result.substring(0, length - 1);
    }

    public static void fileWrite(String data) throws IOException {
//Please complete codes.

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("answer", true));
        ) {
            bw.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void answer() {
//Please complete codes.
        List<String> ns = new ArrayList<>(52);
        IntStream.range(1, 52).forEachOrdered(i -> ns.add(i + ""));

        List<String> zf = new ArrayList<>(26);

        for (char c = 'A'; c <= 'Z'; c++) {
            zf.add(c + "");
        }

        Object lock = new Object();
        Thread t1 = new Thread(new OutputThread(ns, "n", lock));
        Thread t2 = new Thread(new OutputThread(zf, "d", lock));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("answer"))) {
            t1.start();
            t2.start();

            while (true) {
                synchronized (lock) {
                    lock.wait(); // 等待另一个线程输出
                    lock.notify(); // 通知另一个线程继续输出

                    if (!t1.isAlive() && !t2.isAlive()) {
                        break; // 两个线程都结束了，退出循环
                    }

                    writer.flush(); // 刷新输出缓冲区
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        List<String> ns = new ArrayList<>(52);
//        IntStream.range(1,52).forEachOrdered(i->ns.add(i+""));
//
//        List<String> zf = new ArrayList<>(26);
//
//        for (char c = 'A'; c <= 'Z'; c++) {
//            zf.add(c+"");
//        }
//
//
//        new Thread(()->printN(ns)).start();
//
//        new Thread(()->printZ(zf)).start();


    }
//    static ReentrantLock lock =new ReentrantLock(true);
//
//    public static void printN(List<String> ns){
//
//        int start=-1;
//
//        while (true){
//            if (start>=(ns.size()-1)){
//                break;
//            }
//
//           if(lock.tryLock()){
//               try {
//                   System.out.print(ns.get(++start));
//                   System.out.print(ns.get(++start));
//               }finally {
//                   lock.unlock();
//               }
//
//
//           }else {
//               try {
//                   TimeUnit.MILLISECONDS.sleep(10);
//               } catch (InterruptedException e) {
//                   throw new RuntimeException(e);
//               }
//           }
//
//
//        }
//
//
//    }
//
//    public static void printZ(List<String> zf){
//        int start=-1;
//
//        while (true) {
//
//            if (start>=(zf.size()-1)){
//                break;
//            }
//
//            if (lock.tryLock()) {
//                try {
//                    System.out.print(zf.get(++start));
//                } finally {
//                    lock.unlock();
//                }
//
//
//            } else {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        File file = new File("answer");
        if (file.exists()) {
            boolean boo = file.delete();
            if (!boo) {
                System.out.println("文件删除失败");
            }
        }
        answer();
    }

    public static class OutputThread implements Runnable {
        private final List<String> list;
        private final String handleType;
        private final Object lock;

        public OutputThread(List<String> list, String handleType, Object lock) {
            this.list = list;
            this.handleType = handleType;
            this.lock = lock;
        }

        int nStart = -1;

        @Override
        public void run() {

            while (true) {
                if (nStart > (list.size())) {
                    break;
                }
                synchronized (lock) {

                    switch (handleType) {
                        case "n": {
                            System.out.print(list.get(++nStart));
                            System.out.print(list.get(++nStart));
                            break;
                        }
                        default: {
                            System.out.print(list.get(++nStart));
                            break;
                        }
                    }

                    try {
                        lock.notify(); // 通知另一个线程
                        lock.wait(); // 等待另一个线程输出
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lock.notify(); // 通知另一个线程结束

                }
            }
        }
    }

}