package com.springbootpractice.demo.data.dict.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.param.bo.TableBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author carter
 * create_date  2020/5/12 17:56
 * description     mysql的数据库操作
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Repository
@Slf4j
public class MysqlDao {


    private static final String SQL_SCHEMA = "select SCHEMA_NAME from information_schema.SCHEMATA";
    private static final String SQL_TABLE = "select TABLE_NAME,TABLE_COMMENT,ENGINE from information_schema.TABLES where TABLE_SCHEMA=? ";
    private static final String SQL_DATA_DICT = "select COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_COMMENT,EXTRA,IS_NULLABLE,COLUMN_DEFAULT,TABLE_NAME " +
            "from information_schema.COLUMNS WHERE TABLE_SCHEMA=? " +
            "ORDER BY TABLE_NAME DESC , ORDINAL_POSITION ASC ";
    private static final String SQL_TABLE_COUNT = "SELECT COUNT(*)  FROM  %s ";
    private static final String SQL_TABLE_FIRST_ROW = " SELECT * FROM  %s order by %s %s  LIMIT 1";

    private DruidDataSource druidDataSource;


    /**
     * 30分钟自动关闭数据源
     */
    @Scheduled(fixedRate = 1000 * 60 * 60*12)
    public void autoCloseDataSource() {
        try {
            if (Objects.nonNull(druidDataSource)) {
                druidDataSource.close();
            }
        } finally {
            druidDataSource = null;
        }
    }

    private void closeConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    /**
     * 重建数据源
     *
     * @param connectionUrl 连接url
     * @param username      用户名
     * @param password      密码
     */
    public synchronized void rebuildDataSource(String connectionUrl, String username, String password) {
        if (Objects.nonNull(druidDataSource)) {
            druidDataSource.close();
        }

        druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(connectionUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        Properties properties = new Properties();

        properties.setProperty("maxActive", "5");
        properties.setProperty("initialSize", "1");
        properties.setProperty("maxWait", "60000");
        properties.setProperty("maxIdle", "1");

        properties.setProperty("timeBetweenEvictionRunsMillis", "60000");
        properties.setProperty("minEvictableIdleTimeMillis", "300000");
        properties.setProperty("testWhileIdle", "true");
        properties.setProperty("testOnBorrow", "false");
        properties.setProperty("testOnReturn", "false");
        properties.setProperty("poolPreparedStatements", "true");
        properties.setProperty("maxOpenPreparedStatements", "10");
        properties.setProperty("asyncInit", "true");


        druidDataSource.setConnectProperties(properties);
        try {
            druidDataSource.init();
            log.info("初始化mysql数据源成功{},{}", connectionUrl, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public synchronized Connection getConnection() {
        Assert.notNull(druidDataSource, "请先初始化数据源");
        try {
            return druidDataSource.getConnection(100000);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalArgumentException("数据库参数配置错误");

    }


    public Map<String, TableBo> getTableBoMap(String databaseName) {
        Map<String, TableBo> tableBoMap = Maps.newHashMap();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称
            preparedStatement = connection.prepareStatement(SQL_TABLE, new int[1]);
            preparedStatement.setString(1, databaseName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TableBo tableBo = TableBo.builder()
                        .TABLE_NAME(resultSet.getString("TABLE_NAME"))
                        .TABLE_COMMENT(resultSet.getString("TABLE_COMMENT"))
                        .ENGINE(resultSet.getString("ENGINE"))
                        .build();
                tableBoMap.put(tableBo.getTABLE_NAME(), tableBo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return tableBoMap;
    }

    public List<String> getConnectionDatabaseList() {

        List<String> databaseNameList = Lists.newLinkedList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称

            preparedStatement = connection.prepareStatement(SQL_SCHEMA);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                databaseNameList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return databaseNameList;
    }

    public Map<String, List<ColumnBo>> getTableNameDataDictBoListMap(String databaseName) {

        List<ColumnBo> columnBoList = Lists.newLinkedList();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称

            preparedStatement = connection.prepareStatement(SQL_DATA_DICT, new int[1]);
            preparedStatement.setString(1, databaseName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ColumnBo columnBo = ColumnBo.builder()
                        .COLUMN_NAME(resultSet.getString("COLUMN_NAME"))
                        .COLUMN_TYPE(resultSet.getString("COLUMN_TYPE"))
                        .COLUMN_COMMENT(resultSet.getString("COLUMN_COMMENT"))
                        .COLUMN_DEFAULT(resultSet.getString("COLUMN_DEFAULT"))
                        .IS_NULLABLE(resultSet.getString("IS_NULLABLE"))
                        .EXTRA(resultSet.getString("EXTRA"))
                        .TABLE_NAME(resultSet.getString("TABLE_NAME"))
                        .COLUMN_KEY(resultSet.getString("COLUMN_KEY"))
                        .build();

                //如果出现关键字，加引号
                if (Arrays.asList("date","level").contains(columnBo.getCOLUMN_NAME().toLowerCase())){
                    String trimString="\""+columnBo.getCOLUMN_NAME()+"\"";
                    columnBo.setCOLUMN_NAME(trimString);
                }

                if (columnBo.getTABLE_NAME().toLowerCase().startsWith("i")){
                    columnBo.setCOLUMN_NAME("\""+columnBo.getCOLUMN_NAME()+"\"");
                }

                columnBoList.add(columnBo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
        return new TreeMap<>(columnBoList.stream().collect(Collectors.groupingBy(ColumnBo::getTABLE_NAME)));
    }

    public Map<String, List<IndexBo>> getIndexNameIndexBoListMap(String databaseName, String tableName) {

        List<IndexBo> indexBoList = Lists.newLinkedList();

        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称

            resultSet = connection.getMetaData().getIndexInfo(databaseName, databaseName, tableName, false, true);

            while (resultSet.next()) {
                IndexBo dataDictBo = IndexBo.builder()
                        .columnName(resultSet.getString(9))
                        .IndexName(resultSet.getString(6))
                        .seqInIndex(resultSet.getInt(8))
                        .tableName(resultSet.getString(3))
                        .build();
                indexBoList.add(dataDictBo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return new TreeMap<>(indexBoList.stream().collect(Collectors.groupingBy(IndexBo::getIndexName)));

    }

    public Long getTableDataCount(String databaseName, String tableName) {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称
            preparedStatement = connection.prepareStatement(String.format(SQL_TABLE_COUNT, databaseName + "." + tableName));
            resultSet = preparedStatement.executeQuery();

            AtomicLong count = new AtomicLong(0L);
            while (resultSet.next()) {
                count.set(resultSet.getLong(1));
            }
            return count.get();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);

        }

        return 0L;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Object> getRowData(String databaseName, String tableName, boolean isFirst) {

        List<ColumnBo> columnBoList = Optional.ofNullable(getTableNameDataDictBoListMap(databaseName))
                .map(m -> m.get(tableName)).orElse(Collections.emptyList())
                .stream().map(item->{
                    item.setCOLUMN_NAME(item.getCOLUMN_NAME().replaceAll("\"",""));
                    return item;
                }).collect(Collectors.toList());

        if (columnBoList.isEmpty()) {
            return Collections.emptyMap();
        }

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称
            String firstColumnName = columnBoList.get(0).getCOLUMN_NAME();
            preparedStatement = connection.prepareStatement(String.format(SQL_TABLE_FIRST_ROW, databaseName + "." + tableName,firstColumnName, isFirst?"ASC":"DESC"));
            resultSet = preparedStatement.executeQuery();

            Map<String, Object> dataMap = new HashMap<>(columnBoList.size());
            List<String> columnNameList = columnBoList.stream().map(ColumnBo::getCOLUMN_NAME).collect(Collectors.toList());
            while (resultSet.next()) {
                for (String columnName : columnNameList) {
                    dataMap.put(columnName, resultSet.getObject(columnName));
                }
            }
            return dataMap;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);

        }
        return Collections.emptyMap();
    }

    private void closePrepareStatement(PreparedStatement preparedStatement) {
        if (Objects.nonNull(preparedStatement)) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
