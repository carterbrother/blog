package com.springbootpractice.demo.data.dict.web;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.springbootpractice.demo.data.dict.param.core.RestResponseBean;
import com.springbootpractice.demo.data.dict.param.rest.*;
import com.springbootpractice.demo.data.dict.service.DataCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carter
 * 创建日期:  2020/5/8 18:01
 * 描述:     mysql-oracle数据对比控制器
 */
@RestController
@Api("mysql-oracle数据对比控制器")
@RequestMapping("/datac")
public class DataCController {

    private final DataCService dataCService;

    public DataCController(DataCService dataCService) {
        this.dataCService = dataCService;
    }



    @PostMapping("/compare/init_datasource")
    @ApiOperation("初始化数据源mysql或者oracle")
    public RestResponseBean initDataSource(@NonNull InitDataResourceRestReq param) {

        String msg = dataCService.initDataSource(param.getDatabaseType(), param.getConnectionUrl(), param.getUsername(), param.getPassword());

        RestResponseBean responseBean = RestResponseBean.SUCCESS_BEAN;
        responseBean.setMsg(msg);
        return responseBean;

    }

    @PostMapping("/compare/init")
    @ApiOperation("初始化数据源mysql或者oracle")
    public InitCompareRestRes init(@NonNull InitCompareRestReq param) {
        return dataCService.getInitCompareData(param.getMysqlDatabaseName(), param.getOracleDatabaseName());
    }

    @PostMapping("/compare/total")
    @ApiOperation("整体提交表的数量，表的数据数量，表的字段数和索引数量")
    public InitCompareRestRes compareTotal(@NonNull InitCompareRestReq param) {
        return dataCService.compareTotal(param.getMysqlDatabaseName(), param.getOracleDatabaseName());
    }



    @PostMapping("/compare/table_data_count")
    @ApiOperation("对比mysql和oracle的表的数据数量")
    public CompareTableDataCountRestRes compareTableDataCount(@NonNull CompareRestReq param) {

        String mysqlDatabase = param.getMysqlDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlDatabase), "mysql的数据库名为空");

        String oracleDatabase = param.getOracleDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleDatabase), "oracle的数据库名为空");

        String mysqlTableName = param.getMysqlTableName();

        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlTableName), "mysql的数据表名为空");

        String oracleTableName = param.getOracleTableName();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleTableName), "oracle的数据表名为空");

        return dataCService.compareTableDataCount(mysqlDatabase,mysqlTableName,oracleDatabase,oracleTableName);

    }


    @PostMapping("/compare/table_definition")
    @ApiOperation("对比mysql和oracle的表的字段类型")
    public CompareTableFieldTypeRestRes compareTableDefinition(@NonNull CompareRestReq param) {

        String mysqlDatabase = param.getMysqlDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlDatabase), "mysql的数据库名为空");

        String oracleDatabase = param.getOracleDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleDatabase), "oracle的数据库名为空");

        String mysqlTableName = param.getMysqlTableName();

        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlTableName), "mysql的数据表名为空");

        String oracleTableName = param.getOracleTableName();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleTableName), "oracle的数据表名为空");

        return dataCService.compareTalbeDefinition(mysqlDatabase,mysqlTableName,oracleDatabase,oracleTableName);
    }


    @PostMapping("/compare/table_first_last_row")
    @ApiOperation("对比mysql和oracle的首行和尾行数据")
    public CompareTableRowDataRestRes compareTableFirstAndLastRow(@NonNull CompareRestReq param) {

        String mysqlDatabase = param.getMysqlDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlDatabase), "mysql的数据库名为空");

        String oracleDatabase = param.getOracleDatabase();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleDatabase), "oracle的数据库名为空");

        String mysqlTableName = param.getMysqlTableName();

        Preconditions.checkArgument(!Strings.isNullOrEmpty(mysqlTableName), "mysql的数据表名为空");

        String oracleTableName = param.getOracleTableName();
        Preconditions.checkArgument(!Strings.isNullOrEmpty(oracleTableName), "oracle的数据表名为空");

        return dataCService.compareTableRowData(mysqlDatabase,mysqlTableName,oracleDatabase,oracleTableName);
    }


}
