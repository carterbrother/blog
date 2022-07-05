package com.spring.story.demowebsocket;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Carter.li
 * @createtime 2022/7/5 9:41
 */
@Component
@ServerEndpoint("/websocket/{userId}")
@Slf4j
public class WebSocketEndpoint {

    private Session session;
    private String userId;


    private static CopyOnWriteArrayList<WebSocketEndpoint> webSocketEndpoints = new CopyOnWriteArrayList<>();
    private static Map<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        sessionConcurrentHashMap.put(userId, session);
        webSocketEndpoints.add(this);

        log.info("client link coming  【{}】 ,total link num  【{}】", userId, webSocketEndpoints.size());

    }

    @OnClose
    public void onClose() {

        webSocketEndpoints.remove(this);
        sessionConcurrentHashMap.remove(this.userId);
        log.info("link 【{}】 break down ,total link num 【{}】", this.userId, webSocketEndpoints.size());

    }

    @OnMessage
    public void onMessage(String message) {
        log.info("get client 【{}】 message 【{}】", this.userId,JSONUtil.toJsonStr(message));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("client  【{}】 ,error",this.userId,error);
    }


    public void pushMessage(String userId, String message) {
        if (StringUtils.hasLength(userId)) {
            Session session = sessionConcurrentHashMap.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    synchronized (session) {
                        log.info("【websocket message】 single message:" + message);
                        session.getAsyncRemote().sendText(message);
                    }
                } catch (Exception e) {
                    log.error("websocket push message err！", e);
                }

            }
            return;
        }

        try {
            webSocketEndpoints.forEach(ws -> ws.session.getAsyncRemote().sendText(message));
        } catch (Exception e) {
            log.error("websocket push message err！", e);
        }

    }


}
