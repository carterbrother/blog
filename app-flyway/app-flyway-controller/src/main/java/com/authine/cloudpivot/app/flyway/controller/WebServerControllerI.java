package com.authine.cloudpivot.app.flyway.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.ProgressDto;
import com.alibaba.cola.dto.SingleResponse;
import  com.authine.cloudpivot.app.flyway.api.WebServerServiceI;
import  com.authine.cloudpivot.app.flyway.dto.DeleteWebServerCmd;
import  com.authine.cloudpivot.app.flyway.dto.SaveWebServerCmd;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import  com.authine.cloudpivot.app.flyway.dto.WebServerQry;
import  com.authine.cloudpivot.app.flyway.feign.WebServerFeignServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author carter
 * create_date  2020/6/2 14:36
 * description     应用服务器管理
 */
@RestController
@Api(tags = "应用服务器资源管理")
public class WebServerControllerI implements WebServerFeignServiceI {

    private final WebServerServiceI webServerServiceI;

    public WebServerControllerI(WebServerServiceI webServerServiceI) {
        this.webServerServiceI = webServerServiceI;
    }

    @ApiOperation(value = "保存WEB服务器", notes = "录入或者更新WEB服务器连接信息")
    @PostMapping("/web_server/save")
    public SingleResponse<ProgressDto> saveWebServer(@RequestBody @Valid @NonNull SaveWebServerCmd cmd) {
        return webServerServiceI.saveWebServer(cmd);
    }

    @ApiOperation(value = "删除WEB服务器", notes = "删除WEB服务器资源")
    @DeleteMapping("/web_server/delete")
    public SingleResponse<ProgressDto> deleteWebServer(@RequestBody @Valid @NonNull DeleteWebServerCmd cmd) {
        return webServerServiceI.deleteWebServer(cmd);
    }

    @Override
    @ApiOperation("查询WEB服务器列表信息")
    @PostMapping("/web_server/search")
    @ResponseBody
    public MultiResponse<WebServerDto> searchWebServerInfo(@RequestBody WebServerQry qryParam) {
        return webServerServiceI.searchWebServer(qryParam);
    }

}
