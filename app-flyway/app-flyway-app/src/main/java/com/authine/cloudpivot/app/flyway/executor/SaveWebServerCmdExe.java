package com.authine.cloudpivot.app.flyway.executor;

import com.alibaba.cola.dto.ProgressDto;
import com.alibaba.cola.dto.RequestContext;
import com.alibaba.cola.dto.SingleResponse;
import  com.authine.cloudpivot.app.flyway.domain.webserver.domainservice.WebServerDomainService;
import  com.authine.cloudpivot.app.flyway.dto.SaveWebServerCmd;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author carter
 * create_date  2020/6/24 14:22
 * description     保存WEB服务器指令
 */
@Component
public class SaveWebServerCmdExe {

    private final WebServerDomainService webServerDomainService;

    public SaveWebServerCmdExe(WebServerDomainService webServerDomainService) {
        this.webServerDomainService = webServerDomainService;
    }

    public SingleResponse<ProgressDto> execute(SaveWebServerCmd cmd) {


        WebServerDto webServerDto = new WebServerDto();
        BeanUtils.copyProperties(cmd, webServerDto);

        webServerDomainService.saveWebServer(webServerDto);

        return SingleResponse.of(RequestContext.get().getProgressDto());
    }
}
