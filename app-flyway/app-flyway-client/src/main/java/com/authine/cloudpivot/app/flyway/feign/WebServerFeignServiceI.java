package com.authine.cloudpivot.app.flyway.feign;

import com.alibaba.cola.dto.MultiResponse;
import com.authine.cloudpivot.app.flyway.dto.WebServerQry;
import com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author carter
 * create_date  2020/5/29 11:30
 * description     对系统内其它微服务的feign接口
 */
@FeignClient("app-flyway")
public interface WebServerFeignServiceI {

    @ApiOperation("查询WEB服务器列表信息")
    @PostMapping("/web_server/search")
    @ResponseBody
    MultiResponse<WebServerDto> searchWebServerInfo(WebServerQry qryParam);
    
}
