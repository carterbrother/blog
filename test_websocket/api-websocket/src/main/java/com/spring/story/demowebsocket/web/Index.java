package com.spring.story.demowebsocket.web;

import cn.hutool.json.JSONUtil;
import com.spring.story.demowebsocket.WebSocketEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Index {

    private final WebSocketEndpoint webSocketEndpoint;

    public Index(WebSocketEndpoint webSocketEndpoint) {
        this.webSocketEndpoint = webSocketEndpoint;
    }

    @GetMapping("/index")
    public String index() {
        return "hello world";
    }

    @GetMapping("/gb")
    public String gb() {
        Map<String,Object> msg = new HashMap<>();
        msg.put("cmd","success");
        msg.put("msg","xxxx处理成功！");
        webSocketEndpoint.pushMessage("",JSONUtil.toJsonStr( msg));

        return "do ok";
    }

    @GetMapping("/ss/{userId}")
    public String ss(@PathParam("userId") String userId) {
        Map<String,Object> msg = new HashMap<>();
        msg.put("cmd","success");
        msg.put("msg","xxxx处理成功！");
        webSocketEndpoint.pushMessage(userId, JSONUtil.toJsonStr(msg));

        return " single do ok";
    }

    
}
