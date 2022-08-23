package com.spring.history.demo_cache.core.redisson;

import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Date 2021/1/29 16:34
 * @Author by Quine
 */

@Component
public class RedissonConfig {


    private final String NC_COMM_REDIS = "PF.redis-common-config";
    Config redisCommon;


    public RedissonConfig(){

        ConfigChangeListener changeListener = new ConfigChangeListener() {
            //配置变更
            public void onChange(ConfigChangeEvent changeEvent) {

                RedissonManager.createRedisson(configToJson(redisCommon));
            }
        };
        redisCommon = ConfigService.getConfig(NC_COMM_REDIS);
        RedissonManager.createRedisson(configToJson(redisCommon));
    }

    public JSONObject configToJson(Config config) {
        Set<String> keys = config.getPropertyNames();
        JSONObject item = new JSONObject();
        for (String key : keys) {
            item.put(key, config.getProperty(key, ""));
        }
        return item;
    }

}
