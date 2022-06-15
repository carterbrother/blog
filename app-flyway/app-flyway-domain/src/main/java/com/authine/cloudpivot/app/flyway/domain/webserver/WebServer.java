package com.authine.cloudpivot.app.flyway.domain.webserver;

import com.alibaba.cola.dto.MultiResponse;
import  com.authine.cloudpivot.app.flyway.domain.gateway.WebServerGateway;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author carter
 * create_date  2020/6/24 13:55
 * description     Web服务器领域对象
 */
@Component
public class WebServer extends WebServerDto {

    private  final WebServerGateway webServerGateway;

    public WebServer(WebServerGateway webServerGateway) {
        this.webServerGateway = webServerGateway;
    }


    public MultiResponse<WebServerDto> findByIdCondition(Integer serverRecordId, int pageNum, Integer pageSize) {

        return webServerGateway.findByIdCondition(serverRecordId, pageNum, pageSize);
    }

    public void saveWebServer(WebServerDto webServerDto) {

        Integer id = webServerDto.getId();

        if (Objects.isNull(id) || id < 1) {
            webServerDto.setId(null);
            webServerGateway.createWebServer(webServerDto);

            return;
        }

        webServerGateway.updateWebServer(webServerDto);

    }

    public void deleteById(Integer id) {
        webServerGateway.deleteById(id);
    }


}
