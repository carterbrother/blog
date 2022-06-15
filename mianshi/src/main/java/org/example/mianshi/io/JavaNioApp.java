package org.example.mianshi.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * 说明：nio的客户端连接服务端例子
 * @author carter
 * 创建时间： 2020年03月25日 10:32 下午
 **/

public class JavaNioApp {


    public static void main(String[] args) {

        new Server().start();

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
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
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

                serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));

                serverSocketChannel.configureBlocking(false);

                final Selector selector = Selector.open();

                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {

                    selector.select();
                    selector.selectedKeys().forEach(selectionKey -> {
                        sayHelloWorld((ServerSocketChannel) selectionKey.channel());
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        private void sayHelloWorld(ServerSocketChannel channel) {

            try (SocketChannel socketChannel = channel.accept()) {

                socketChannel.write(Charset.defaultCharset().encode("hello world nio"));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
