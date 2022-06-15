package com.authine.cloudpivot.app.flyway.gateway;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.ProgressDto;
import  com.authine.cloudpivot.app.flyway.domain.gateway.WebServerGateway;
import  com.authine.cloudpivot.app.flyway.dto.clientobject.WebServerDto;
import  com.authine.cloudpivot.app.flyway.repository.ResourceWebServerDORepository;
import  com.authine.cloudpivot.app.flyway.repository.databaseobject.ResourceWebServerDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author carter
 * create_date  2020/6/24 13:57
 * description     数据持久层实现
 */
@Service
public class WebServerGatewayImpl implements WebServerGateway {

    private final ResourceWebServerDORepository resourceWebServerDORepository;

    public WebServerGatewayImpl(ResourceWebServerDORepository resourceWebServerDORepository) {
        this.resourceWebServerDORepository = resourceWebServerDORepository;
    }


    /**
     * 按照记录ID的条件查询得到记录
     *
     * @param webServerRecordId 记录ID
     * @param pageNum
     * @param pageSize
     * @return web服务器列表
     */
    @Override
    public MultiResponse<WebServerDto> findByIdCondition(Integer webServerRecordId, int pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<ResourceWebServerDO> list;

        if (Objects.isNull(webServerRecordId)) {
            list = Optional.ofNullable(resourceWebServerDORepository.findAll()).orElse(Collections.emptyList());
        } else {
            Condition condition = new Condition(ResourceWebServerDO.class);
            condition.createCriteria().andEqualTo("id", webServerRecordId);
            list = Optional.ofNullable(resourceWebServerDORepository.findByCondition(condition)).orElse(Collections.emptyList());

        }

        List<WebServerDto> webServerDtoList = list.stream().map(item -> {
            WebServerDto webServerDto = new WebServerDto();
            BeanUtils.copyProperties(item, webServerDto);
            return webServerDto;
        }).collect(Collectors.toList());

        PageInfo<WebServerDto> pageInfo = new PageInfo<>(webServerDtoList);

        return MultiResponse.of(pageInfo.getList(), (int) pageInfo.getTotal());

    }

    /**
     * 创建Web服务器
     *
     * @param webServerDto
     */
    @Override
    public void createWebServer(WebServerDto webServerDto) {

        ResourceWebServerDO resourceWebServerDO = new ResourceWebServerDO();
        BeanUtils.copyProperties(webServerDto, resourceWebServerDO);

        ProgressDto.addProgressMsg("保存Web服务器", "创建", () -> 1);
        resourceWebServerDORepository.save(resourceWebServerDO);

    }

    /**
     * 更新Web服务器
     *
     * @param webServerDto
     */
    @Override
    public void updateWebServer(WebServerDto webServerDto) {
        ResourceWebServerDO resourceWebServerDO = new ResourceWebServerDO();
        BeanUtils.copyProperties(webServerDto, resourceWebServerDO);

        ProgressDto.addProgressMsg("保存Web服务器", "更新", () -> 1);
        resourceWebServerDORepository.update(resourceWebServerDO);
    }

    /**
     * 删除Web服务器
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {

        ProgressDto.addProgressMsg("删除Web服务器", "删除", () -> 1);
        resourceWebServerDORepository.deleteById(id);
    }
}
