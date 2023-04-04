package com.zengame.platweb.demo2;

import cn.hutool.json.JSONUtil;
import io.vertx.core.AbstractVerticle;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/12/12  17:50
 **/
public class Server extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(httpServerRequest -> {

            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("ret", 1);
            dataMap.put("msg", "hello , vert.x !");

            httpServerRequest.response().putHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                    .end(JSONUtil.toJsonStr(dataMap));

        }).listen(8555);
    }
}
