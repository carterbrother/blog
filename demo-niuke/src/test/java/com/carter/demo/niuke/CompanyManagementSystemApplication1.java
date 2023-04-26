package com.carter.demo.niuke;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CompanyManagementSystemApplication1 {



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
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("answer", true));
            bw.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert bw != null;
            bw.close();
        }
    }

    public static void answer() {
          //Please complete codes.

        new Thread(()-> startServer()).start();

        new Thread(()-> startClient()).start();
        

    }

    public static void startClient(){

        try (Socket socket = new Socket("localhost", 8888);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            // 接收来自服务器的消息
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received message from server: " + line);

                // 将通信内容交替写入文件
                if (line.equals("exit")) {
                    break;
                }
                fileWrite(line);
                String message = "Hello,  I am client!" + System.lineSeparator();

                System.out.println("send message to server: " + message);

                writer.write(message);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public static void startServer(){

        try (ServerSocket serverSocket = new ServerSocket(8888);
             Socket socket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            // 向客户端发送消息
            String message = "用户名：alice；密码：789"+System.lineSeparator();
            writer.write(message);
            writer.flush();

            // 接收来自客户端的消息
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received message from client: " + line);

                // 将通信内容交替写入文件
                if (line.equals("exit")) {
                    break;
                }
                fileWrite( line);


                System.out.println("send message to client: " + message);

                writer.write(message);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        File file = new File("answer");
        if (file.exists()) {
            boolean boo = file.delete();
            if (!boo) {
                System.out.println("文件删除失败");
            }
        }
        answer();
    }
}