package com.springbootpractice.demo.data.dict.service.impl;

import com.springbootpractice.demo.data.dict.dao.OracleDao;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.service.DataCompareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author carter
 * create_date  2020/5/12 13:49
 * description     mysql数据库实现数据比较业务类
 */
@Service("oracle")
public class OracleDataCompareServiceImpl implements DataCompareService {

    private final OracleDao oracleDao;

    public OracleDataCompareServiceImpl(OracleDao oracleDao) {
        this.oracleDao = oracleDao;
    }

    @Override
    public String getDatabaseTypeName() {
        return "oracle";
    }

    @Override
    public String initDataSource(String jdbcUrl, String userName, String password) {
        oracleDao.rebuildDataSource(jdbcUrl, userName, password);
        return "初始化oracle数据源OK";
    }

    @Override
    public Set<String> getTableCount(String databaseName) {

        return oracleDao.getTableBoMap(databaseName).keySet();
    }

    @Override
    public List<ColumnBo> getTableColumnList(String databaseName, String tableName) {
        return oracleDao.getTableNameDataDictBoListMap(databaseName, tableName).get(tableName);
    }

    @Override
    public Map<String, List<IndexBo>> getTableIndexList(String databaseName, String tableName) {
        return oracleDao.getIndexNameIndexBoListMap(databaseName, tableName);
    }

    @Override
    public Map<String, Object> getFirstRow(String databaseName, String tableName) {
        return oracleDao.getRowData(databaseName, tableName, true);
    }

    @Override
    public Map<String, Object> getLastRow(String databaseName, String tableName) {
        return oracleDao.getRowData(databaseName, tableName, false);
    }


    @Override
    public Long getTableDataCount(String databaseName, String tableName) {
        return oracleDao.getTableDataCount(databaseName, tableName);
    }
}
