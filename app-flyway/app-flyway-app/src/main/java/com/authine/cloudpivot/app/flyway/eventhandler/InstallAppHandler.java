package com.authine.cloudpivot.app.flyway.eventhandler;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.event.EventHandler;
import com.alibaba.cola.event.EventHandlerI;
import  com.authine.cloudpivot.app.flyway.dto.domainevent.InstallAppEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Handle customer created event
 *
 * @author frankzhang
 * @date 2019/04/09
 */
@Slf4j
@EventHandler
public class InstallAppHandler implements EventHandlerI<Response, InstallAppEvent> {


    @Override
    public Response execute(InstallAppEvent event) {

        log.info("发生了领域事件：安装应用");

        String appCode = event.getAppCode();
        Integer resourceDatabaseId = event.getResourceDatabaseId();


        return Response.buildSuccess();
    }
}
