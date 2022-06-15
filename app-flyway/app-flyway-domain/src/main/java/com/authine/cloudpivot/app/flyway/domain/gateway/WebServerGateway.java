package com.authine.cloudpivot.app.flyway.domain.gateway;

import com.alibaba.cola.dto.MultiResponse;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;

/**
 * @author carter
 * create_date  2020/6/24 13:56
 * description     webServer领域对应的网关接口
 */

public interface WebServerGateway {
    /**
     * 按照记录ID的条件查询得到记录
     *
     * @param serverRecordId 记录ID
     * @param pageNum
     * @param pageSize
     * @return web服务器列表
     */
    MultiResponse<WebServerDto> findByIdCondition(Integer serverRecordId, int pageNum, Integer pageSize);

    /**
     * 创建Web服务器
     * @param webServerDto
     */
    void createWebServer(WebServerDto webServerDto);

    /**
     * 更新Web服务器
     * @param webServerDto
     */
    void updateWebServer(WebServerDto webServerDto);

    /**
     * 删除Web服务器
     * @param id
     */
    void deleteById(Integer id);
}
