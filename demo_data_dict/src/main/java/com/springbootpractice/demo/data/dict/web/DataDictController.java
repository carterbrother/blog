package com.springbootpractice.demo.data.dict.web;

import com.springbootpractice.demo.data.dict.param.rest.ConnectionReqParam;
import com.springbootpractice.demo.data.dict.param.rest.DatabaseListResParam;
import com.springbootpractice.demo.data.dict.param.rest.GenerateDataDictResParam;
import com.springbootpractice.demo.data.dict.service.DataDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 说明：数据字典控制器
 *
 * @author carter
 * 创建时间： 2020年02月03日 3:57 下午
 **/
@RestController
@Api("数据字典生成")
public class DataDictController {

    private final DataDictService dataDictService;

    public DataDictController(DataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }



    @PostMapping(path = "/testConnection")
    @ApiOperation(value = "测试连接信息并获取数据库列表")
    public DatabaseListResParam testConnection(@NonNull ConnectionReqParam param) {


        String oracleLinkMsg = "";
        List<String> connectionDatabaseList = Collections.emptyList();


        String connectionUrl = param.getConnectionUrl();
        String username = param.getUsername();
        String password = param.getPassword();

        String dbType = param.getDbType();
        if ("oracle".equalsIgnoreCase(dbType)){
            dataDictService.initConnectionOracle(connectionUrl, username, password);
            oracleLinkMsg = "oracle数据源初始化成功";
        }else{
            connectionDatabaseList = dataDictService.getConnectionDatabaseList(connectionUrl, username, password);
        }


        return DatabaseListResParam.builder().databaseList(connectionDatabaseList).oracleLinkMsg(oracleLinkMsg).build();
    }

    @PostMapping(path = "/generateDataDict")
    @ApiOperation(value = "生成数据字典")
    public GenerateDataDictResParam generateDataDict(@NonNull String databaseName) {
        Assert.isTrue(StringUtils.isNotBlank(databaseName), "请选择数据库");
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(StringUtils.split(databaseName, ","))
                .filter(StringUtils::isNotBlank)
                .forEach(dbName -> {
                    final String markdownContent = dataDictService.generateDataDict(dbName);
                    stringBuilder.append(markdownContent).append("\n");
                });

        return GenerateDataDictResParam.builder().markdownContent(stringBuilder.toString()).build();
    }

    @PostMapping(path = "/generateDataDictOnlyTable")
    @ApiOperation(value = "生成数据字典")
    public GenerateDataDictResParam generateDataDictOnlyTable(@NonNull String databaseName) {
        Assert.isTrue(StringUtils.isNotBlank(databaseName), "请选择数据库");
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(StringUtils.split(databaseName, ","))
                .filter(StringUtils::isNotBlank)
                .forEach(dbName -> {
                    final String markdownContent = dataDictService.generateDataDictOnlyTable(dbName);
                    stringBuilder.append(markdownContent).append("\n");
                });

        return GenerateDataDictResParam.builder().markdownContent(stringBuilder.toString()).build();
    }

}
