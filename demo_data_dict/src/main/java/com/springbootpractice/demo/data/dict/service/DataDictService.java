package com.springbootpractice.demo.data.dict.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.springbootpractice.demo.data.dict.dao.MysqlDao;
import com.springbootpractice.demo.data.dict.dao.OracleDao;
import com.springbootpractice.demo.data.dict.param.bo.*;
import com.springbootpractice.demo.data.dict.param.datax.DataXConfigBean;
import com.springbootpractice.demo.data.dict.param.datax.Reader;
import com.springbootpractice.demo.data.dict.param.datax.Writer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 说明：业务代码实现类
 *
 * @author carter
 * 创建时间： 2020年02月03日 4:46 下午
 **/
@Service
@Slf4j
public class DataDictService {

    private final MysqlDao mysqlDao;

    private final OracleDao oracleDao;


    public DataDictService(MysqlDao mysqlDao, OracleDao oracleDao) {
        this.mysqlDao = mysqlDao;
        this.oracleDao = oracleDao;
    }


    public List<String> getConnectionDatabaseList(String connectionUrl, String username, String password) {

        mysqlDao.rebuildDataSource(connectionUrl, username, password);
        return mysqlDao.getConnectionDatabaseList();
    }


    public String generateDataDict(String databaseName) {

        Map<String, TableBo> tableBoMap = mysqlDao.getTableBoMap(databaseName);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(databaseName).append("\n");
        stringBuilder.append("> ").append(databaseName).append("\n");

        Map<String, List<ColumnBo>> map = mysqlDao.getTableNameDataDictBoListMap(databaseName);
        stringBuilder.append("> 表数量：").append(map.keySet().size()).append("\n");
        map.forEach((k, v) -> {
            final TableBo tableBo = tableBoMap.get(k);
            stringBuilder.append("## ").append(k).append("\n\n");
            stringBuilder.append("> 业务说明：").append(tableBo.getTABLE_COMMENT());
            stringBuilder.append("\n存储引擎：").append(tableBo.getENGINE()).append("\n\n");

            stringBuilder.append("|字段名|字段类型|字段备注|默认值|是否允许为空|其它约束|").append("\n");
            stringBuilder.append("|-|-|-|-|-|-|").append("\n");

            v.forEach(column -> stringBuilder.append("|")
                    .append(column.getCOLUMN_NAME()).append("|")
                    .append(column.getCOLUMN_TYPE()).append("|")
                    .append(column.getCOLUMN_COMMENT()).append("|")
                    .append(column.getCOLUMN_DEFAULT()).append("|")
                    .append(column.getIS_NULLABLE()).append("|")
                    .append(column.getEXTRA()).append("|")
                    .append("\n"));
            stringBuilder.append("\n");


        });
        return stringBuilder.toString();
    }

    public String generateDataDictOnlyTable(String databaseName) {

        Map<String, TableBo> tableBoMap = mysqlDao.getTableBoMap(databaseName);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(databaseName).append("\n");
//        stringBuilder.append(" ").append(databaseName).append("\n");

        Map<String, List<ColumnBo>> map = mysqlDao.getTableNameDataDictBoListMap(databaseName);
        stringBuilder.append("|表名|备注|").append("\n");
        stringBuilder.append("|-|-|").append("\n");
        map.forEach((k, v) -> {


            final TableBo tableBo = tableBoMap.get(k);

            if (!tableBo.getTABLE_NAME().equalsIgnoreCase("flyway_schema_history")){
                stringBuilder.append("|")
                        .append(tableBo.getTABLE_NAME()).append("|")
                        .append(tableBo.getTABLE_COMMENT()).append("|");
                stringBuilder.append("\n");
            }

        });
        return stringBuilder.toString();
    }

    /**
     * 生成datax所需的迁移mysql到oracle的脚本,基于mysql的元素数据信息得到
     *
     * @param databaseName 数据库名
     * @return dataX的迁移实体
     */
    public DataXConfigBean generateDataXConfigBean(String databaseName) {
        DataXConfigBean dataXConfigBean = DataXConfigBean.builder()
                .job(DataXConfigBean.Job.builder()
                        .setting(DataXConfigBean.Setting.builder()
                                .speed(DataXConfigBean.Speed.builder().channel(1).build())
                                .build())
                        .content(Lists.newLinkedList())
                        .build())
                .build();

        List<DataXConfigBean.Content> contents = dataXConfigBean.getJob().getContent();

        Map<String, TableBo> tableBoMap = mysqlDao.getTableBoMap(databaseName);

        Map<String, List<ColumnBo>> tableNameDictListMap = mysqlDao.getTableNameDataDictBoListMap(databaseName);

        Map<String, AtomicInteger> indexNameMap = new HashMap<>(tableNameDictListMap.size() * 3);

        List<String> tableNameList = new LinkedList<>();

        List<String> indexNameList = new LinkedList<>();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        tableBoMap.forEach((tableName, tableBo) -> {

            atomicInteger.incrementAndGet();


            List<ColumnBo> columnBos = tableNameDictListMap.get(tableName);
            if (!tableNameList.contains(tableName.toLowerCase())) {

                String querySql = getQuerySql(tableName, columnBos);
                //设置querySql
                Reader reader = Reader.builder()
                        .parameter(Reader.ReaderParameter.builder()
                                .connection(Collections.singletonList(Reader.ReaderConnection.builder()
                                        .querySql(Collections.singletonList(querySql))
                                        .jdbcUrl(Lists.newLinkedList())
                                        .build()))
                                .build())
                        .build();

                List<String> columns = getColumns(columnBos);

                List<String> preSqls = new LinkedList<>();
                List<String> preInitSqls = new LinkedList<>();
                String dropTableSql = "delete from " + tableName;
                preSqls.add(dropTableSql);

                //生成建表，设置备注的sql
                preInitSqls.addAll(getPreSqls(databaseName, tableName, columnBos));


                //生成表相关的索引
                List<String> tableIndexList = getTableIndexList(databaseName, tableName, indexNameMap, indexNameList);
                preInitSqls.addAll(tableIndexList);

                Writer writer = Writer.builder()
                        .parameter(Writer.WriterParameter.builder()
                                .column(columns)
                                .preInitTableSql(preInitSqls)
                                .preSql(preSqls)
                                .connection(Collections.singletonList(Writer.WriteConnection.builder()
                                        .table(Collections.singletonList("" + tableName + ""))
                                        .build()))
                                .build())
                        .build();

                contents.add(DataXConfigBean.Content.builder().reader(reader).writer(writer).build());

            }
            tableNameList.add(tableName.toLowerCase());


        });

        //清空索引统计信息
        indexNameMap.clear();
        return dataXConfigBean;
    }

    /**
     * @param databaseName
     * @param tableName
     * @param indexNameMap
     * @param indexNameList
     * @return
     */
    public List<String> getTableIndexList(String databaseName, String tableName, Map<String, AtomicInteger> indexNameMap, List<String> indexNameList) {
        List<String> createTableIndexSqls = new LinkedList<>();

        Map<String, List<IndexBo>> indexNameIndexBoListMap = mysqlDao.getIndexNameIndexBoListMap(databaseName, tableName);

        //找出跟主键索引冲突的一般索引
        Set<String> primaryColumnSet = indexNameIndexBoListMap.entrySet().stream()
                .filter(item -> "primary".equalsIgnoreCase(item.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList())
                .stream().map(IndexBo::getColumnName)
                .collect(Collectors.toSet());


        indexNameIndexBoListMap.forEach((indexName, indexBoList) -> {


            Set<String> columnSet = indexBoList.stream().map(IndexBo::getColumnName).collect(Collectors.toSet());

            if (!"PRIMARY".equalsIgnoreCase(indexName) && !Objects.deepEquals(columnSet, primaryColumnSet)) {
                //create index IDX_BWT_INSTANCEID on BIZ_WORKFLOW_TOKEN (INSTANCEID)


                String indexNameFul = indexName;


                if (indexNameList.contains(indexNameFul.toLowerCase())) {
                    indexNameFul = indexNameFul + "_" + getTableAliasName(tableName);
                }

                if (indexNameFul.length() > 30) {
                    //超长的索引，进行简化
                    indexNameFul = getTableAliasName(indexNameFul) + "_" + getTableAliasName(tableName);
                }


                indexNameList.add(indexNameFul.toLowerCase());

                if (indexNameMap.containsKey(indexNameFul)) {
                    indexNameMap.put(indexNameFul, new AtomicInteger(indexNameMap.get(indexNameFul).incrementAndGet()));
                } else {
                    indexNameMap.put(indexNameFul, new AtomicInteger(1));
                }

                //如果存在同名的了，增加数量编排
                int indexNameCount = indexNameMap.get(indexNameFul).get();
                indexNameFul = indexNameCount > 1 ? (indexNameFul + indexNameCount) : indexNameFul;

                String createTableIndexStringBuilder = "create index " +
                        indexNameFul +
                        " on " + tableName + "" +
                        indexBoList.stream()
                                .sorted(Comparator.comparingInt(IndexBo::getSeqInIndex))
                                .map(IndexBo::getColumnName)
                                .map(item -> {
                                    if (tableName.toLowerCase().startsWith("i")){
                                        return "\"" + item + "\"";
                                    }
                                    return item;
                                })
                                .collect(Collectors.joining(",", "(", ")"));
                createTableIndexSqls.add(createTableIndexStringBuilder);


            }


        });

        return createTableIndexSqls;
    }


    private static String getTableAliasName(String tableName) {
        return Arrays.asList(StringUtils.split(tableName, "_")).stream().map(item -> item.substring(0, 1))
                .collect(Collectors.joining(""));

    }

    public static void main(String[] args) {
        System.out.println(getTableAliasName("h_user_guide"));
    }


    //分别放入删除表，新建表 , 表字段备注语句 oracle
    private List<String> getPreSqls(String databaseName, String tableName, List<ColumnBo> columnBos) {
        List<String> columnCommentSqls = new LinkedList<>();

        StringBuilder createTableStringBuilder = new StringBuilder("create table ").append(tableName).append(" (");

        List<String> primaryFieldList = Lists.newLinkedList();
        columnBos.forEach(columnBo -> {

            String columnType = columnBo.getCOLUMN_TYPE();
            ColumnTypeBo columnTypeBo = ColumnTypeBo.getColumnTypeBo(columnType);
            String oracleTypeString = ColumnTypeBo.getOracleType(columnTypeBo);

            if (tableName.toLowerCase().substring(0,1).equalsIgnoreCase("i") && "clob".equalsIgnoreCase(oracleTypeString)){
                oracleTypeString = "varchar2(4000)";
            }


            String columnName = columnBo.getCOLUMN_NAME();

            String columnComment = columnBo.getCOLUMN_COMMENT();
            if (!Strings.isNullOrEmpty(columnComment)) {
                columnCommentSqls.add(String.format("comment on column %s.%s  is '%s'", "" + tableName + "", "" + columnName + "", columnComment));
            }


            createTableStringBuilder.append("" + columnName + "")
                    .append(" ").append(oracleTypeString);

            String isNullableStr = columnBo.getIS_NULLABLE();

            String columnDefault = columnBo.getCOLUMN_DEFAULT();

            if (!Strings.isNullOrEmpty(columnDefault)) {

                if (columnDefault.startsWith("b") && columnDefault.contains("'")) {
                    columnDefault = columnDefault.substring(columnDefault.indexOf("'") + 1, columnDefault.lastIndexOf("'"));
                }

                if (oracleTypeString.contains("varchar2") || (oracleTypeString.contains("date") && Character.isDigit(columnDefault.charAt(0)))) {
                    createTableStringBuilder.append(" default '").append(columnDefault).append("' ");
                } else {
                    createTableStringBuilder.append(" default ").append(columnDefault);
                }

            }

            if ("no".equalsIgnoreCase(isNullableStr)) {
                createTableStringBuilder.append(" not null");
            }

            String columnKey = columnBo.getCOLUMN_KEY();
            if ("pri".equalsIgnoreCase(columnKey)) {
                primaryFieldList.add(columnName);
            }

            createTableStringBuilder.append(", ");

        });

        if (!primaryFieldList.isEmpty()) {
            createTableStringBuilder.append(primaryFieldList.stream().collect(Collectors.joining(",", " primary key(", ")")));
        } else {
            createTableStringBuilder
                    .delete(createTableStringBuilder.length() - 2, createTableStringBuilder.length() - 1);
        }
        String createTableSql = createTableStringBuilder
                .append(")")
                .toString();

        List<String> preSqlList = new LinkedList<>();

        preSqlList.add(createTableSql);
        preSqlList.addAll(columnCommentSqls);


        return preSqlList;
    }

    //获取表的字段列表
    private List<String> getColumns(List<ColumnBo> columnBos) {
        return columnBos.stream().map(ColumnBo::getCOLUMN_NAME).collect(Collectors.toList());
    }

    //获取查询的SQL
    private String getQuerySql(String tableName, List<ColumnBo> columnBos) {

        StringBuilder stringBuilder = new StringBuilder("select");

        columnBos.forEach(columnBo -> {
            String columnName = columnBo.getCOLUMN_NAME().replaceAll("\"","");
            String columnType = columnBo.getCOLUMN_TYPE();

            ColumnTypeBo columnTypeBo = ColumnTypeBo.getColumnTypeBo(columnType);

            //更多不对应的类型，也在这块处理；
            if ("bit".equalsIgnoreCase(columnTypeBo.getColumnTypeName())) {
                columnName = "if(" + columnName + "=true,1,0) " + columnName;
            }

            if ("json".equalsIgnoreCase(columnTypeBo.getColumnTypeName())){
                columnName = "if("+columnName+ " is null,"+columnName+",JSON_PRETTY("+ columnName + ")) as " + columnName+" ";
            }

            stringBuilder.append(" ").append(columnName).append(",");
        });

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(" from ").append(tableName).append(";").toString();
    }


    public void executeDDL(List<DataXConfigBean.Content> contentList, String oracleDatabaseName) {
        //先删除原来的表，然后，进行表初始化；
        Map<String, TableBo> tableBoMap = oracleDao.getTableBoMap(oracleDatabaseName);

        if (!tableBoMap.isEmpty()) {
            List<DDLExecuteBo> dropTableDDLResultList = tableBoMap.keySet().stream().map(tableName -> {
                if (Character.isLowerCase(tableName.charAt(0))) {
                    tableName = "\"" + tableName + "\"";
                }
                String dropTableSql = String.format("drop table %s", tableName);
                return oracleDao.executeDDLSql(dropTableSql);
            }).collect(Collectors.toList());

            boolean allDropSuc = dropTableDDLResultList.stream().allMatch(item -> item.getRes() == 0);
            if (!allDropSuc) {
                String msg = dropTableDDLResultList.stream().filter(item -> item.getRes() != 0)
                        .map(item -> item.getErr() + " sql: " + item.getSql())
                        .collect(Collectors.joining("<br>"));
                throw new IllegalArgumentException("删除oracle的语句执行出现错误：" + msg);
            }

        }

        //执行oracle的建表语句,备注更新语句，建立索引
        List<DDLExecuteBo> ddlInitList = contentList.stream()
                .flatMap(content -> {

                    List<String> preInitTableSql = content.getWriter().getParameter().getPreInitTableSql();
                    String[] sqlArray = new String[preInitTableSql.size()];
                    preInitTableSql.toArray(sqlArray);
                    return Arrays.stream(sqlArray);
                }).map(sql -> {
                    String sql1 = sql.replaceAll(";", "");
                    return oracleDao.executeDDLSql(sql1);
                }).collect(Collectors.toList());

        boolean allInitSuc = ddlInitList.stream().allMatch(item -> item.getRes() == 0);

        if (!allInitSuc) {
            String msg = ddlInitList.stream().filter(item -> item.getRes() != 0)
                    .map(item -> item.getErr() + " sql: " + item.getSql())
                    .collect(Collectors.joining("<br>"));
            throw new IllegalArgumentException("oracle初始语句出现错误：" + msg);
        }


    }

    /**
     * 初始化oracle的数据源
     *
     * @param connectionUrl jdbcUrl
     * @param username      用户名
     * @param password      密码
     */
    public void initConnectionOracle(String connectionUrl, String username, String password) {
        oracleDao.rebuildDataSource(connectionUrl, username, password);
    }
}
