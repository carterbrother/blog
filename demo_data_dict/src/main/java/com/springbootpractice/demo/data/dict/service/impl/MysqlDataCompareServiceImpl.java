package com.springbootpractice.demo.data.dict.service.impl;

import com.springbootpractice.demo.data.dict.dao.MysqlDao;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.service.DataCompareService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author carter
 * create_date  2020/5/12 13:49
 * description     mysql数据库实现数据比较业务类
 */
@Service("mysql")
public class MysqlDataCompareServiceImpl implements DataCompareService {

    private final MysqlDao mysqlDao;

    public MysqlDataCompareServiceImpl(MysqlDao mysqlDao) {
        this.mysqlDao = mysqlDao;
    }

    @Override
    public String getDatabaseTypeName() {
        return "mysql";
    }

    @Override
    public String initDataSource(String jdbcUrl, String userName, String password) {
        mysqlDao.rebuildDataSource(jdbcUrl,userName,password);
        return "初始化mysql数据源ok";
    }

    @Override
    public Set<String> getTableCount(String databaseName) {

        return Optional.ofNullable(mysqlDao.getTableBoMap(databaseName))
                .map(Map::keySet)
                .orElse(Collections.emptySet());
    }

    @Override
    public List<ColumnBo> getTableColumnList(String databaseName, String tableName) {
        return Optional.ofNullable(mysqlDao.getTableNameDataDictBoListMap(databaseName))
                .map(m -> m.get(tableName))
                .orElse(Collections.emptyList());
    }

    @Override
    public Map<String, List<IndexBo>> getTableIndexList(String databaseName, String tableName) {
        return mysqlDao.getIndexNameIndexBoListMap(databaseName, tableName);
    }

    @Override
    public Map<String, Object> getFirstRow(String databaseName, String tableName) {
        return mysqlDao.getRowData(databaseName, tableName,true);
    }

    @Override
    public Map<String, Object> getLastRow(String databaseName, String tableName) {
        return mysqlDao.getRowData(databaseName, tableName,false);
    }

    @Override
    public Long getTableDataCount(String databaseName, String tableName) {
        return mysqlDao.getTableDataCount(databaseName, tableName);
    }
}
