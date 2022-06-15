package com.authine.cloudpivot.app.flyway.executor;

import com.alibaba.cola.dto.ProgressDto;
import com.alibaba.cola.dto.RequestContext;
import com.alibaba.cola.dto.SingleResponse;
import  com.authine.cloudpivot.app.flyway.domain.webserver.domainservice.WebServerDomainService;
import  com.authine.cloudpivot.app.flyway.dto.DeleteWebServerCmd;
import org.springframework.stereotype.Component;

/**
 * @author carter
 * create_date  2020/6/24 14:38
 * description     TODO
 */
@Component
public class DeleteWebServerCmdExe {

    private final WebServerDomainService webServerDomainService;

    public DeleteWebServerCmdExe(WebServerDomainService webServerDomainService) {
        this.webServerDomainService = webServerDomainService;
    }

    public SingleResponse<ProgressDto> execute(DeleteWebServerCmd cmd) {

        Integer id = cmd.getId();

        webServerDomainService.deleteWebServerById(id);

        return SingleResponse.of(RequestContext.get().getProgressDto());
    }
}
