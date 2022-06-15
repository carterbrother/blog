package org.example.mianshi.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年03月25日 10:54 下午
 **/

public class JavaNio2App {

    public static void main(String[] args) {

        new Server().start();

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        ) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bufferedReader.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static class Server extends Thread {


        @Override
        public void run() {

            try {
                AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open()
                        .bind(new InetSocketAddress(InetAddress.getLocalHost(), 9999));

                serverSocketChannel.accept(serverSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                    @Override
                    public void completed(AsynchronousSocketChannel socketChannel,
                                          AsynchronousServerSocketChannel serverSocketChannel1) {
//                        serverSocketChannel1.accept(socketChannel, this);

                        socketChannel.write(Charset.defaultCharset().encode("hello world nio2 "));

                        try {
                            socketChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                        exc.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
