package com.authine.cloudpivot.app.flyway.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.ProgressDto;
import com.alibaba.cola.dto.SingleResponse;
import  com.authine.cloudpivot.app.flyway.api.WebServerServiceI;
import  com.authine.cloudpivot.app.flyway.executor.DeleteWebServerCmdExe;
import  com.authine.cloudpivot.app.flyway.executor.SaveWebServerCmdExe;
import  com.authine.cloudpivot.app.flyway.executor.query.WebServerQryExe;
import  com.authine.cloudpivot.app.flyway.dto.DeleteWebServerCmd;
import  com.authine.cloudpivot.app.flyway.dto.SaveWebServerCmd;
import  com.authine.cloudpivot.app.flyway.dto.WebServerQry;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import org.springframework.stereotype.Service;

/**
 * @author carter
 * create_date  2020/6/24 13:41
 * description     web服务器的应用接口实现类
 */
@Service
public class WebServerServiceImpl implements WebServerServiceI {

    private final WebServerQryExe webServerQryExe;

    private final SaveWebServerCmdExe saveWebServerCmdExe;

    private final DeleteWebServerCmdExe deleteWebServerCmdExe;

    public WebServerServiceImpl(WebServerQryExe webServerQryExe, SaveWebServerCmdExe saveWebServerCmdExe, DeleteWebServerCmdExe deleteWebServerCmdExe) {
        this.webServerQryExe = webServerQryExe;
        this.saveWebServerCmdExe = saveWebServerCmdExe;
        this.deleteWebServerCmdExe = deleteWebServerCmdExe;
    }

    /**
     * 查询webServer信息
     *
     * @param qryParam 查询参数
     * @return 记录列表
     */
    @Override
    public MultiResponse<WebServerDto> searchWebServer(WebServerQry qryParam) {
        return webServerQryExe.execute(qryParam);
    }

    /**
     * 保存Web服务器
     *
     * @param cmd 保存指令
     * @return 保存进度
     */
    @Override
    public SingleResponse<ProgressDto> saveWebServer(SaveWebServerCmd cmd) {
        return saveWebServerCmdExe.execute(cmd);
    }

    /**
     * 删除Web服务器
     *
     * @param cmd 删除指令
     * @return 删除进度
     */
    @Override
    public SingleResponse<ProgressDto> deleteWebServer(DeleteWebServerCmd cmd) {
        return deleteWebServerCmdExe.execute(cmd);
    }
}
