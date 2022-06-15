package test.rest;

import com.example.AppResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2019年12月12日 10:56 上午
 **/

public class Main {


    /**
     * @return 配置本地的ip, 端口，应用的contextPath,可以写死，也可以用通过命令行传递，优先命令行
     */
    public static String getBaseUri() {
        return System.getProperty("jersey.base.uri", "http://localhost:8080/webapi/");
    }

    /**
     * @return 创建一个简单http的服务器，方便快速接口测试
     */
    public static HttpServer startServer() {

        ResourceConfig resourceConfig = new AppResourceConfig();

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUri()), resourceConfig);

    }

    /**
     * 启动主方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        final HttpServer httpServer = startServer();

        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\nHit enter to stop it...", getBaseUri()));

        try {
            final int read = System.in.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpServer.shutdownNow();

    }

}
