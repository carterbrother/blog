package com.springbootpractice.demo.data.dict.service;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.springbootpractice.demo.data.dict.param.bo.ColumnBo;
import com.springbootpractice.demo.data.dict.param.bo.IndexBo;
import com.springbootpractice.demo.data.dict.param.rest.CompareTableDataCountRestRes;
import com.springbootpractice.demo.data.dict.param.rest.CompareTableFieldTypeRestRes;
import com.springbootpractice.demo.data.dict.param.rest.CompareTableRowDataRestRes;
import com.springbootpractice.demo.data.dict.param.rest.InitCompareRestRes;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * carter
 * 创建日期:  2020/5/8 18:13
 * 描述:     对比oracle和mysql的数据业务服务代码
 *
 * @author lifuchun
 */

@Service
public class DataCService {

    private final static Map<String, DataCompareService> DATA_COMPARE_SERVICE_MAP = new HashMap<>(2);

    public DataCService(List<DataCompareService> dataCompareServiceList) {
        if (!CollectionUtils.isEmpty(dataCompareServiceList)) {
            for (DataCompareService dataCompareService : dataCompareServiceList) {
                DATA_COMPARE_SERVICE_MAP.put(dataCompareService.getDatabaseTypeName(), dataCompareService);
            }
        }
    }


    /**
     * 初始化数据源
     *
     * @param databaseType  数据库类型
     * @param connectionUrl 连接url
     * @param username      用户名
     * @param password      密码
     * @return 初始化结果
     */
    public String initDataSource(String databaseType, String connectionUrl, String username, String password) {

        return DATA_COMPARE_SERVICE_MAP.get(databaseType)
                .initDataSource(connectionUrl, username, password);

    }

    public InitCompareRestRes getInitCompareData(String mysqlDatabaseName, String oracleDatabaseName) {

        DataCompareService mysqlService = DATA_COMPARE_SERVICE_MAP.get("mysql");
        DataCompareService oracleService = DATA_COMPARE_SERVICE_MAP.get("oracle");

        Set<String> mysqlTableSet = mysqlService.getTableCount(mysqlDatabaseName);
        Set<String> oracleTableSet = oracleService.getTableCount(oracleDatabaseName);

        Map<String,String> mysqlMap = new HashMap<>(mysqlTableSet.size());
        Map<String,String> oracleMap = new HashMap<>(oracleTableSet.size());

        mysqlTableSet.forEach(k->mysqlMap.put(k.toLowerCase(),k));
        oracleTableSet.forEach(k->oracleMap.put(k.toLowerCase(),k));



        int mysqlTableCount = mysqlTableSet.size();
        int oracleTableCount = oracleTableSet.size();

        String countMsg = String.format("mysql的数据库%s表数量%d个，oracle的数据库%s表数量%d个", mysqlDatabaseName, mysqlTableCount, oracleDatabaseName, oracleTableCount);
        List<InitCompareRestRes.TableCompareBo> tableCompareBos = new LinkedList<>();

        Sets.SetView<String> intersection = Sets.intersection(mysqlMap.keySet(), oracleMap.keySet());

        Sets.SetView<String> differenceMysql = Sets.difference(mysqlMap.keySet(), oracleMap.keySet());
        Sets.SetView<String> differenceOracle = Sets.difference(mysqlMap.keySet(), oracleMap.keySet());


        for (String tableName : intersection) {

            tableCompareBos.add(InitCompareRestRes.TableCompareBo.builder().mysqlTableName(mysqlMap.get(tableName))
                    .oracleTableName(oracleMap.get(tableName)).build());
        }

        for (String tableName : differenceMysql) {

            tableCompareBos.add(InitCompareRestRes.TableCompareBo.builder().mysqlTableName(mysqlMap.get(tableName)).build());
        }

        for (String tableName : differenceOracle) {
            tableCompareBos.add(InitCompareRestRes.TableCompareBo.builder().oracleTableName(oracleMap.get(tableName)).build());
        }


        String totalMsg = "";

        return InitCompareRestRes.builder()
                .countMsg(countMsg)
                .totalMsg(totalMsg)
                .tableCompareBos(tableCompareBos)
                .build();

    }

    public CompareTableDataCountRestRes compareTableDataCount(String mysqlDatabase, String mysqlTableName, String oracleDatabase, String oracleTableName) {

        DataCompareService mysqlService = DATA_COMPARE_SERVICE_MAP.get("mysql");
        DataCompareService oracleService = DATA_COMPARE_SERVICE_MAP.get("oracle");


        Long mysqlTableDataCount = mysqlService.getTableDataCount(mysqlDatabase, mysqlTableName);
        Long oracleTableDataCount = oracleService.getTableDataCount(oracleDatabase, oracleTableName);

        return CompareTableDataCountRestRes.builder()
                .mysqlTableName(mysqlDatabase + "." + mysqlTableName)
                .oracleTableName(oracleDatabase + "." + oracleTableName)
                .mysqlTableDataCount(mysqlTableDataCount)
                .oracleTableDataCount(oracleTableDataCount)
                .msg(mysqlTableDataCount.equals(oracleTableDataCount)?"数据总条数相同":"数据总条数不同")
                .build();
    }

    public CompareTableRowDataRestRes compareTableRowData(String mysqlDatabase, String mysqlTableName, String oracleDatabase, String oracleTableName) {

        DataCompareService mysqlService = DATA_COMPARE_SERVICE_MAP.get("mysql");
        DataCompareService oracleService = DATA_COMPARE_SERVICE_MAP.get("oracle");

        Map<String, Object> mysqlFirstRow = mysqlService.getFirstRow(mysqlDatabase, mysqlTableName);
        Map<String, Object> mysqlLastRow = mysqlService.getLastRow(mysqlDatabase, mysqlTableName);

        Map<String, Object> oracleFirstRow = oracleService.getFirstRow(oracleDatabase, oracleTableName);
        Map<String, Object> oracleLastRow = oracleService.getLastRow(oracleDatabase, oracleTableName);

        Map<String,String> oracleKeyMapF= new HashMap<>(oracleFirstRow.size());
        Map<String,String> oracleKeyMapL= new HashMap<>(oracleLastRow.size());

        oracleFirstRow.keySet().forEach(k->{
            oracleKeyMapF.put(k.toLowerCase().replaceAll("\"",""),k);
        });

        oracleLastRow.keySet().forEach(k->{
            oracleKeyMapL.put(k.toLowerCase().replaceAll("\"",""),k);
        });



        List<CompareTableRowDataRestRes.CellBo> firstRows = new LinkedList<>();

        for (String columnName : mysqlFirstRow.keySet()) {
            CompareTableRowDataRestRes.CellBo cellBo = CompareTableRowDataRestRes.CellBo.builder().mKey(columnName).mValue(mysqlFirstRow.get(columnName)).build();

            boolean containsKey = oracleKeyMapF.containsKey(columnName.toLowerCase());
            if (containsKey) {
                cellBo.setOKey(oracleKeyMapF.get(columnName.toLowerCase()));
                cellBo.setOValue(oracleFirstRow.get(cellBo.getOKey()));
            }

            firstRows.add(cellBo);
        }

        List<CompareTableRowDataRestRes.CellBo> lastRows = new LinkedList<>();
        for (String columnName : mysqlLastRow.keySet()) {
            CompareTableRowDataRestRes.CellBo cellBo = CompareTableRowDataRestRes.CellBo.builder().mKey(columnName).mValue(mysqlLastRow.get(columnName)).build();

            boolean containsKey = oracleKeyMapL.containsKey(columnName.toLowerCase());
            if (containsKey) {
                cellBo.setOKey(oracleKeyMapL.get(columnName.toLowerCase()));
                cellBo.setOValue(oracleLastRow.get(cellBo.getOKey()));
            }
            lastRows.add(cellBo);
        }

        boolean isDataEqual = firstRows.stream().allMatch(item->Objects.equals(item.getOValue(),item.getMValue()))
                &&lastRows.stream().allMatch(item->Objects.equals(item.getOValue(),item.getMValue()));

        return CompareTableRowDataRestRes.builder()
                .mysqlTableName(mysqlDatabase + "." + mysqlTableName)
                .oracleTableName(oracleDatabase + "." + oracleTableName)
                .firstRow(firstRows)
                .lastRow(lastRows)
                .msg(isDataEqual?"数据字段值一样":"数据字段值有差异")
                .build();
    }

    public CompareTableFieldTypeRestRes compareTalbeDefinition(String mysqlDatabase, String mysqlTableName, String oracleDatabase, String oracleTableName) {

        DataCompareService mysqlService = DATA_COMPARE_SERVICE_MAP.get("mysql");
        DataCompareService oracleService = DATA_COMPARE_SERVICE_MAP.get("oracle");

        List<String> mIndexList = mysqlService.getTableIndexList(mysqlDatabase, mysqlTableName)
                .entrySet()
                .stream()
                .map(entry -> {
                    String indexName = entry.getKey();
                    List<IndexBo> indexBos = entry.getValue();

                    String columns = indexBos.stream().sorted(Comparator.comparingInt(IndexBo::getSeqInIndex)).map(IndexBo::getColumnName).collect(Collectors.joining(",", "(", ")"));
                    return indexName + columns;

                })
                .collect(Collectors.toList());

        List<String> oIndexList = oracleService.getTableIndexList(oracleDatabase, oracleTableName)
                .entrySet()
                .stream()
                .map(entry -> {
                    String indexName = entry.getKey();
                    List<IndexBo> indexBos = entry.getValue();

                    String columns = indexBos.stream().sorted(Comparator.comparingInt(IndexBo::getSeqInIndex)).map(IndexBo::getColumnName).collect(Collectors.joining(",", "(", ")"));
                    return indexName + columns;

                })
                .collect(Collectors.toList());

        List<CompareTableFieldTypeRestRes.TableDefinitionBo> tableDefinitions = new LinkedList<>();

        Map<String, ColumnBo> mysqlTableColumnList = mysqlService.getTableColumnList(mysqlDatabase, mysqlTableName).stream().collect(Collectors.toMap(ColumnBo::getCOLUMN_NAME, Function.identity()));
        Map<String, ColumnBo> oracleTableColumnList = oracleService.getTableColumnList(oracleDatabase, oracleTableName).stream()
                .map(item->{
                    item.setCOLUMN_NAME(item.getCOLUMN_NAME().toLowerCase());
                    return item;
                })
                .collect(Collectors.toMap(ColumnBo::getCOLUMN_NAME, Function.identity()));
        ;

        mysqlTableColumnList.forEach((k, v) -> {

            String fieldType = k+" "+ v.getCOLUMN_TYPE() + " " + v.getCOLUMN_DEFAULT() + " " + v.getIS_NULLABLE();
            CompareTableFieldTypeRestRes.TableDefinitionBo tableDefinitionBo = CompareTableFieldTypeRestRes.TableDefinitionBo.builder()
                    .mysqlFieldType(fieldType)
                    .build();

            boolean containsKey = oracleTableColumnList.containsKey(k.toLowerCase());
            if (containsKey) {
                ColumnBo oColumnBo = oracleTableColumnList.get(k.toLowerCase());
                String oFieldType = k + " "+ oColumnBo.getCOLUMN_TYPE() + " " + oColumnBo.getCOLUMN_DEFAULT() + " " + oColumnBo.getIS_NULLABLE();

                tableDefinitionBo.setOracleFieldType(oFieldType);
            }

            tableDefinitions.add(tableDefinitionBo);

        });

        boolean isFieldCountAndIndexCountEq = Objects.equals(mIndexList.size(),oIndexList.size())
                && tableDefinitions.stream().allMatch(item->!Strings.isNullOrEmpty(item.getMysqlFieldType()) && !Strings.isNullOrEmpty(item.getOracleFieldType()));


        return CompareTableFieldTypeRestRes.builder()
                .tableDefinitionBoList(tableDefinitions)
                .mIndexList(mIndexList)
                .oIndexList(oIndexList)
                .msg(isFieldCountAndIndexCountEq?"表的字段和索引数量相同":"表的字段或者索引的数量不同")
                .build();
    }

    public InitCompareRestRes compareTotal(String mysqlDatabaseName, String oracleDatabaseName) {

        StringBuffer stringBuffer = new StringBuffer("失败原因：");
        //比较表的总数量

        DataCompareService mysqlService = DATA_COMPARE_SERVICE_MAP.get("mysql");
        DataCompareService oracleService = DATA_COMPARE_SERVICE_MAP.get("oracle");

        Set<String> mysqlTableSet = mysqlService.getTableCount(mysqlDatabaseName).stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> oracleTableSet = oracleService.getTableCount(oracleDatabaseName);

        int mysqlTableCount = mysqlTableSet.size();
        int oracleTableCount = oracleTableSet.size();

        boolean tableCountSuc = Objects.equals(mysqlTableCount,oracleTableCount);

        if (!tableCountSuc){
            stringBuffer.append("表的总数不一致，mysql:"+mysqlTableCount + " oracle: "+oracleTableCount);
        }

        //比较所有的表的 数据总数，字段总数，索引总数

        List<Boolean> tableResList = getInitCompareData(mysqlDatabaseName, oracleDatabaseName).getTableCompareBos().stream().map(tableCompareBo -> {


            String mysqlTableName = tableCompareBo.getMysqlTableName();
            String oracleTableName = tableCompareBo.getOracleTableName();

            Long tableDataCountMySql = mysqlService.getTableDataCount(mysqlDatabaseName, mysqlTableName);
            Long tableDataCountOracle = oracleService.getTableDataCount(oracleDatabaseName, oracleTableName);

            boolean tableCountEq = Objects.equals(tableDataCountMySql, tableDataCountOracle);
            if (!tableCountEq) {
                stringBuffer.append("<br>表[" + mysqlTableName + "]的数据总数不一致，mysql:" + tableDataCountMySql + " oracle: " + tableDataCountOracle);
            }

            int fieldSizeMysql = mysqlService.getTableColumnList(mysqlDatabaseName, mysqlTableName).size();
            int fieldSizeOracle = oracleService.getTableColumnList(oracleDatabaseName, oracleTableName).size();

            boolean fieldSizeEq = Objects.equals(fieldSizeMysql, fieldSizeOracle);
            if (!fieldSizeEq) {
                stringBuffer.append("<br>表[" + mysqlTableName + "]的字段总数不一致，mysql:" + fieldSizeMysql + " oracle: " + fieldSizeOracle);
            }

            int indexSizeMysql = mysqlService.getTableIndexList(mysqlDatabaseName, mysqlTableName).size();
            int indexSizeOracle = oracleService.getTableIndexList(oracleDatabaseName, oracleTableName).size();

            boolean indexCountEq = Objects.equals(indexSizeMysql, indexSizeOracle);
            if (!indexCountEq) {
                stringBuffer.append("<br>表[" + mysqlTableName + "]的索引总数不一致，mysql:" + indexSizeMysql + " oracle: " + indexSizeOracle);
            }

            return indexCountEq && fieldSizeEq && tableCountEq;


        }).collect(Collectors.toList());

        boolean tableInfoSuc = tableResList.stream().allMatch(item -> Objects.equals(item, true));
        boolean  suc = tableCountSuc && tableInfoSuc;

        return InitCompareRestRes.builder().totalMsg(suc?"整体比对成功":"整体比对失败:"+stringBuffer.toString()).build();

    }
}
