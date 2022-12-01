package com.spring.story.demo.cache.pre.config;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/18  16:02
 **/
@Data
public class TokenCheckConfig {

    private String whiteUrlSet;

    private RedissonProperties redis;


    public static void main(String[] args) {


        TokenCheckConfig tokenCheckConfig = new TokenCheckConfig();
        tokenCheckConfig.setWhiteUrlSet("POST:/rbac/login/upLogin|POST:/rbac/login/scancodeLogin|GET:/actuator|POST:/rbac/oauth/getOauth2Url|GET:/rbac/oauth/qrConfig|POST:/asyn/wxNotify/**|GET:/asyn/wxNotify/**");

        RedissonProperties redissonProperties = new RedissonProperties("redis://svc-redis.dev.svc.cluster.local:6379","",0,"apisix-java-plugin",10);
        tokenCheckConfig.setRedis(redissonProperties);

        System.out.println(StrUtil.replaceChars(JSONUtil.toJsonStr(tokenCheckConfig),"\"","\\\"") );


    }

}
