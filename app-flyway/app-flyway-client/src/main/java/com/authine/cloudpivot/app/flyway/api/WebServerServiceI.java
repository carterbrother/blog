package com.authine.cloudpivot.app.flyway.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.ProgressDto;
import com.alibaba.cola.dto.SingleResponse;
import com.authine.cloudpivot.app.flyway.dto.*;
import com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;

/**
 * @author carter
 * create_date  2020/6/22 21:51
 * description     Web服务器的应用接口
 */

public interface WebServerServiceI {
    /**
     * 查询webServer信息
     * @param qryParam 查询参数
     * @return 记录列表
     */
    MultiResponse<WebServerDto> searchWebServer(WebServerQry qryParam);

    /**
     * 保存Web服务器
     * @param cmd 保存指令
     * @return 保存进度
     */
    SingleResponse<ProgressDto> saveWebServer(SaveWebServerCmd cmd);

    /**
     * 删除Web服务器
     * @param cmd 删除指令
     * @return 删除进度
     */
    SingleResponse<ProgressDto> deleteWebServer(DeleteWebServerCmd cmd);
}
