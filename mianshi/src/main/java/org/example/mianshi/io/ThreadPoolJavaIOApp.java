package org.example.mianshi.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 说明：传统流式io 客户端连接服务端例子
 * @author carter
 * 创建时间： 2020年03月25日 9:58 下午
 **/

public class ThreadPoolJavaIOApp {

    public static void main(String[] args) {

        final Server server = new Server();
        new Thread(server).start();

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), server.getPort());
        ) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bufferedReader.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Server implements Runnable {

        private ExecutorService threadPool = Executors.newFixedThreadPool(4);

        private ServerSocket serverSocket;

        public int getPort() {
            return serverSocket.getLocalPort();
        }

        @Override
        public void run() {

            try (ServerSocket serverSocket = new ServerSocket(0);) {

                this.serverSocket = serverSocket;
                while (true) {
                    final Socket socket = serverSocket.accept();

                    threadPool.submit(new RequestHandler(socket));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                threadPool.shutdown();
            }

        }

        private class RequestHandler implements Runnable {
            private Socket socket;

            public RequestHandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try (
                        final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                ) {

                    printWriter.write("hello world");
                    printWriter.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
