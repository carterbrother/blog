package com.springbootpractice.demo.data.dict.param.bo;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author carter
 * create_date  2020/5/9 16:55
 * description     TODO
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("字段的类型和长度")
@Slf4j
public class ColumnTypeBo implements Serializable {

    private static final long serialVersionUID = 5761868821705193679L;

    @ApiModelProperty("字段类型名称")
    private String columnTypeName;

    @ApiModelProperty("字段类型的长度，null表示无长度")
    private Integer columnTypeLength;

    public static ColumnTypeBo getColumnTypeBo(@NonNull String columnNameString) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(columnNameString), "字段字符串为空");

        String trimString = columnNameString.trim();


        int indexOfStart = trimString.indexOf("(");

        if (indexOfStart < 0) {
            return ColumnTypeBo.builder().columnTypeName(trimString).build();
        }

        String columnTypeString = trimString.substring(0, indexOfStart);
        String columnLengthString = trimString.substring(indexOfStart + 1, trimString.indexOf(")"));

        if (!NumberUtils.isDigits(columnLengthString)) {
            return ColumnTypeBo.builder().columnTypeName(columnTypeString).build();
        }

        return ColumnTypeBo.builder().columnTypeName(columnTypeString)
                .columnTypeLength(Integer.parseInt(columnLengthString))
                .build();

    }

    public static void main(String[] args) {


        ColumnTypeBo columnTypeBo1 = ColumnTypeBo.getColumnTypeBo("decimal(13,4)");
        System.out.println(columnTypeBo1);

        ColumnTypeBo columnTypeBo = ColumnTypeBo.getColumnTypeBo("bit(1)");
        System.out.println(columnTypeBo);




        ColumnTypeBo columnTypeBo2 = ColumnTypeBo.getColumnTypeBo("datetime");
        System.out.println(columnTypeBo2);


        String columnDefault="b'0'";;
        if (columnDefault.startsWith("b") && columnDefault.contains("'")){
            columnDefault = columnDefault.substring(columnDefault.indexOf("'")+1, columnDefault.lastIndexOf("'"));
        }
        System.out.println(columnDefault);

        Set<String> set1 = new HashSet<>();
        set1.add("id");

        Set<String> set2 = new HashSet<>();
        set2.add("id");

        System.out.println("set compare: "+Objects.deepEquals(set1, set2));


    }

    private static Map<String, String> mysqlTypeMapOracleType = new HashMap<>();

    static {
        mysqlTypeMapOracleType.put("varchar", "varchar2");

        /**
         * TINYINT(-128-127)
         *
         * SMALLINT(-32768-32767)
         *
         * MEDIUMINT(-8388608-8388607)
         *
         * INT(-2147483648-2147483647)
         * BIGINT(-9223372036854775808-9223372036854775807)
         */
        mysqlTypeMapOracleType.put("tinyint", "int");
        mysqlTypeMapOracleType.put("SMALLINT".toLowerCase(), "number");
        mysqlTypeMapOracleType.put("MEDIUMINT".toLowerCase(), "number");
        mysqlTypeMapOracleType.put("int", "int");
        mysqlTypeMapOracleType.put("BIGINT".toLowerCase(), "number(20)");
        mysqlTypeMapOracleType.put("DECIMAL".toLowerCase(), "decimal(19, 6)");
        mysqlTypeMapOracleType.put("float", "number");
        mysqlTypeMapOracleType.put("double", "number");
        mysqlTypeMapOracleType.put("bit", "number(1,0)");

        mysqlTypeMapOracleType.put("date", "date");
        mysqlTypeMapOracleType.put("datetime", "date");
        mysqlTypeMapOracleType.put("timestamp", "date");
        mysqlTypeMapOracleType.put("time", "date");

        mysqlTypeMapOracleType.put("char", "char");

        mysqlTypeMapOracleType.put("tinytext", "clob");
        mysqlTypeMapOracleType.put("text", "clob");
        mysqlTypeMapOracleType.put("mediumtext", "clob");
        mysqlTypeMapOracleType.put("longtext", "clob");

        mysqlTypeMapOracleType.put("tinyblob", "blob");
        mysqlTypeMapOracleType.put("blob", "blob");
        mysqlTypeMapOracleType.put("mediumblob", "blob");
        mysqlTypeMapOracleType.put("longblob", "blob");

        mysqlTypeMapOracleType.put("json", "varchar2(4000)");


    }

    public static String getOracleType(@NonNull ColumnTypeBo columnTypeBo) {

        String columnTypeName = columnTypeBo.getColumnTypeName().toLowerCase();
        boolean containsKey = mysqlTypeMapOracleType.containsKey(columnTypeName);
        Preconditions.checkArgument(containsKey, "没有对mysql的类型【%s】配置对应oracleType", columnTypeName);

        String oracleTypeName = mysqlTypeMapOracleType.get(columnTypeName);
        Integer columnTypeLength = columnTypeBo.getColumnTypeLength();

        if (Objects.isNull(columnTypeLength)||Arrays.asList("int","bigint","decimal","bit","tinyint").contains(columnTypeName)) {
            return oracleTypeName;
        }
        //长度超过4000，使用clob存储
        if ("varchar2".equalsIgnoreCase(oracleTypeName) ){
            if (columnTypeLength>1333)
            {
                return mysqlTypeMapOracleType.get("tinytext");
            }else{
                columnTypeLength*=3;
            }
        }
        //长度超过38，使用number字段类型
        if ("number".equalsIgnoreCase(oracleTypeName) && columnTypeLength>38){
            return "number";
        }

        return new StringBuilder(oracleTypeName).append("(").append(columnTypeLength).append(")").toString();
    }
}
