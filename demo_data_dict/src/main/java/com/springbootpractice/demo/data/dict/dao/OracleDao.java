package com.springbootpractice.demo.data.dict.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.DDLExecuteBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.param.bo.TableBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author carter
 * create_date  2020/5/12 17:56
 * description     oracle的数据操作
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Repository
@Slf4j
public class OracleDao {


    private static final String SQL_TABLE = "select TABLE_NAME,LAST_ANALYZED from tabs";
    private static final String SQL_DATA_DICT = "select TABLE_NAME,COLUMN_NAME,DATA_TYPE,DATA_LENGTH,DATA_DEFAULT,COLUMN_ID,NULLABLE from user_tab_columns where Table_Name='%s' order by COLUMN_ID asc ";
    private static final String SQL_TABLE_COUNT = "SELECT COUNT(1)  FROM  %s ";
    private static final String SQL_TABLE_FIRST_ROW = "select * from (select * from %s order by %s %s) where rownum=1";
    private static final String SQL_INDEX = "SELECT INDEX_NAME,TABLE_NAME,COLUMN_NAME,COLUMN_POSITION,COLUMN_LENGTH,CHAR_LENGTH,DESCEND FROM USER_IND_COLUMNS WHERE TABLE_NAME='%s' ";

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

        properties.setProperty("maxActive", "2");
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
            preparedStatement = connection.prepareStatement(SQL_TABLE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Date lastAnalyzed = resultSet.getDate("LAST_ANALYZED");
                TableBo tableBo = TableBo.builder()
                        .TABLE_NAME(resultSet.getString("TABLE_NAME"))
                        .build();
                Optional.ofNullable(lastAnalyzed).ifPresent((date) -> {
                    tableBo.setLAST_ANALYZED(DateFormatUtils.format(date, "yyyyMMdd:HHmmss"));
                });
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


    public Map<String, List<ColumnBo>> getTableNameDataDictBoListMap(String databaseName, String tableName) {

        List<ColumnBo> columnBoList = Lists.newLinkedList();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称

            preparedStatement = connection.prepareStatement(String.format(SQL_DATA_DICT, tableName));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ColumnBo columnBo = ColumnBo.builder()
                        .COLUMN_NAME(resultSet.getString("COLUMN_NAME"))
                        .COLUMN_TYPE(resultSet.getString("DATA_TYPE") + " " + resultSet.getString("DATA_LENGTH"))
                        .COLUMN_DEFAULT(resultSet.getString("DATA_DEFAULT"))
                        .IS_NULLABLE(resultSet.getString("NULLABLE"))
                        .TABLE_NAME(resultSet.getString("TABLE_NAME"))
                        .Column_Index(resultSet.getLong("COLUMN_ID"))
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
        PreparedStatement prepareStatement = null;
        try {
            //查询得到所有的数据的名称

            prepareStatement = connection.prepareStatement(String.format(SQL_INDEX, tableName));
            resultSet = prepareStatement.executeQuery();

            //INDEX_NAME, TABLE_NAME, COLUMN_NAME, COLUMN_POSITION, COLUMN_LENGTH, CHAR_LENGTH, DESCEND
            while (resultSet.next()) {
                IndexBo dataDictBo = IndexBo.builder()
                        .columnName(resultSet.getString("COLUMN_NAME"))
                        .IndexName(resultSet.getString("INDEX_NAME"))
                        .seqInIndex(resultSet.getInt("COLUMN_POSITION"))
                        .tableName(resultSet.getString("TABLE_NAME"))
                        .build();
                indexBoList.add(dataDictBo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePrepareStatement(prepareStatement);
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
            preparedStatement = connection.prepareStatement(String.format(SQL_TABLE_COUNT, tableName));
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

        List<ColumnBo> columnBoList = Optional.ofNullable(getTableNameDataDictBoListMap(databaseName, tableName))
                .map(m -> m.get(tableName)).orElse(Collections.emptyList());

        if (columnBoList.isEmpty()) {
            return Collections.emptyMap();
        }

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = getConnection();
        try {
            //查询得到所有的数据的名称
            String firstColumnName = columnBoList.get(0).getCOLUMN_NAME();
            String sql = String.format(SQL_TABLE_FIRST_ROW, tableName, firstColumnName, (isFirst ? "ASC" : "DESC"));
            preparedStatement = connection.prepareStatement(sql);
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

    private void closeStatement(Statement preparedStatement) {
        if (Objects.nonNull(preparedStatement)) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    /**
     * 执行表定义语句
     * @param sql sql语句
     * @return 0表示成功 小于0表示失败
     */
    public DDLExecuteBo executeDDLSql(String sql) {


        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            int executeUpdateResult = statement.executeUpdate(sql);

            log.info("执行ddl语句: {} 结果：{}", sql, executeUpdateResult);

            return DDLExecuteBo.builder().res(executeUpdateResult).build();

        } catch (Exception ex) {
            log.error("执行ddl语句：{} 错误.", sql, ex);
            return DDLExecuteBo.builder().res(-1).sql(sql).err(ex.getMessage()).build();
        } finally {

            closeStatement(statement);

            closeConnection(connection);
        }


    }
}
