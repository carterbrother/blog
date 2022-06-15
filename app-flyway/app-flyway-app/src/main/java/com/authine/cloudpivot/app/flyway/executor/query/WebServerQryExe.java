package com.authine.cloudpivot.app.flyway.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import  com.authine.cloudpivot.app.flyway.domain.webserver.domainservice.WebServerDomainService;
import  com.authine.cloudpivot.app.flyway.dto.WebServerQry;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import org.springframework.stereotype.Component;

/**
 * @author carter
 * create_date  2020/6/24 13:53
 * description     TODO
 */
@Component
public class WebServerQryExe {

    private final WebServerDomainService webServerDomainService;

    public WebServerQryExe(WebServerDomainService webServerDomainService) {
        this.webServerDomainService = webServerDomainService;
    }


    public MultiResponse<WebServerDto> execute(WebServerQry cmd) {

        int pageNum = cmd.getPageNum();
        int pageSize = cmd.getPageSize();
        Integer webServerRecordId = cmd.getId();

        MultiResponse<WebServerDto> multiResponse = webServerDomainService.findByIdCondition(webServerRecordId, pageNum, pageSize);

        return multiResponse;


    }
}
