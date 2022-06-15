package com.authine.cloudpivot.app.flyway.domain.webserver.domainservice;

import com.alibaba.cola.dto.MultiResponse;
import  com.authine.cloudpivot.app.flyway.domain.webserver.WebServer;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import org.springframework.stereotype.Component;

/**
 * @author carter
 * create_date  2020/6/11 19:40
 * description     应用的领域服务
 */

@Component
public class WebServerDomainService {

    private final WebServer webServer;


    public WebServerDomainService(WebServer webServer) {
        this.webServer = webServer;
    }


    public MultiResponse<WebServerDto> findByIdCondition(Integer serverRecordId, int pageNum, Integer pageSize) {

        return webServer.findByIdCondition(serverRecordId, pageNum, pageSize);
    }

    public void saveWebServer(WebServerDto webServerDto) {

        webServer.saveWebServer(webServerDto);
    }


    public void deleteWebServerById(Integer id) {

        webServer.deleteById(id);

    }
}
