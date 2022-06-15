package com.springbootpractice.demo.data.dict.service;

import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author carter
 * create_date  2020/5/11 11:42
 * description     数据对比操作
 */
public interface DataCompareService {

    /**
     * 数据库的种类名称，比如mysql或者oracle
     * @return 数据库的种类名称，比如mysql或者oracle
     */
    String getDatabaseTypeName();

    /**
     * 初始化数据源
     * @param jdbcUrl url
     * @param userName 用户名
     * @param password 密码
     * @return 初始化结果
     */
    String initDataSource(String jdbcUrl,String userName,String password);

    /**
     * 获取指定数据库的表名集合
     * @param databaseName 数据库名
     * @return 表名集合
     */
    Set<String> getTableCount(String databaseName);


    /**
     * 获取数据库某个表的字段定义
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return 字段定义列表
     */
    List<ColumnBo> getTableColumnList(String databaseName, String tableName);

    /**
     * 获取数据库的某个表的索引定义
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return 索引定义列表
     */
    Map<String, List<IndexBo>> getTableIndexList(String databaseName, String tableName);

    /**
     * 获取数据库表的第一行数据
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return 数据对应的map
     */
    Map<String,Object> getFirstRow(String databaseName, String tableName);

    /**
     * 获取数据库表的第一行数据
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return 数据对应的map
     */
    Map<String,Object> getLastRow(String databaseName, String tableName);


    /**
     * 获取数据库某表对应的总数据条数
     * @param databaseName 数据库名
     * @param tableName 表名
     * @return 表的总数据条数
     */
    Long getTableDataCount(String databaseName, String tableName);
}
