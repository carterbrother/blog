package com.springbootpractice.demo.data.dict.web;

import com.google.common.io.Files;
import com.springbootpractice.demo.data.dict.param.datax.DataXConfigBean;
import com.springbootpractice.demo.data.dict.param.datax.Reader;
import com.springbootpractice.demo.data.dict.param.datax.Writer;
import com.springbootpractice.demo.data.dict.param.rest.DataXRestReqVo;
import com.springbootpractice.demo.data.dict.param.rest.OracleSqlRestRes;
import com.springbootpractice.demo.data.dict.service.DataDictService;
import com.springbootpractice.demo.data.dict.util.JsonUtil;
import com.springbootpractice.demo.data.dict.util.ShellExecutorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 说明：dataX配置生成接口
 *
 * @author carter
 * 创建时间： 2020年02月03日 3:57 下午
 **/
@RestController
@Api("DataX配置生成")
@RequestMapping("/datax")
public class DataXController {

    private final DataDictService dataDictService;

    @Value("${datax.base.path:/tmp/}")
    private String dataxConfigJsonBasePath;

    public DataXController(DataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }


    @PostMapping(path = "/generateOracleSql")
    @ApiOperation(value = "生成Oracle的建表脚本")
    public OracleSqlRestRes generateOracle(@NonNull DataXRestReqVo param) {
        DataXConfigBean dataXConfigBean = generateDataXJson(param);


        StringBuilder stringBuilder = new StringBuilder();

        List<DataXConfigBean.Content> contentList = dataXConfigBean.getJob().getContent();

        String oracleDatabaseName = param.getOracleDatabaseName();

        dataDictService.executeDDL(contentList, oracleDatabaseName);

        contentList
                .stream()
                .flatMap(content -> Stream.of(content.getWriter().getParameter().getPreInitTableSql()))
                .forEachOrdered((List<String> sqls) -> sqls.forEach(sql -> stringBuilder.append(sql).append(";\n")));


        String content = stringBuilder.toString();

        return OracleSqlRestRes.builder()
                .oracleSql(content)
                .build();

    }


    @PostMapping(path = "/generateDataXJson")
    @ApiOperation(value = "生成DataX的json数据,reader:Mysql,writer:Oracle")
    public DataXConfigBean generateDataXJson(@NonNull DataXRestReqVo param) {

        String mysqlDatabaseName = param.getMysqlDatabaseName();
        Assert.isTrue(StringUtils.isNotBlank(mysqlDatabaseName), "请选择数据库");

        return Optional.ofNullable(dataDictService.generateDataXConfigBean(mysqlDatabaseName))
                .map(item -> {
                    Optional.of(item).map(DataXConfigBean::getJob).map(DataXConfigBean.Job::getContent).orElse(Collections.emptyList())
                            .stream()
                            .map(DataXConfigBean.Content::getReader)
                            .peek(reader -> {
                                reader.setName("mysqlreader");
                                Reader.ReaderParameter readerParameter = reader.getParameter();
                                readerParameter.setUsername(param.getMysqlUsername());
                                readerParameter.setPassword(param.getMysqlPassword());
                                readerParameter.getConnection().forEach(connection -> connection.getJdbcUrl().add(param.getMysqlConnectionUrl()));
                            }).collect(Collectors.toList());

                    return item;
                })
                .map(item -> {

                    Optional.of(item).map(DataXConfigBean::getJob).map(DataXConfigBean.Job::getContent).orElse(Collections.emptyList())
                            .stream()
                            .map(DataXConfigBean.Content::getWriter)
                            .peek(writer -> {

                                writer.setName("oraclewriter");
                                Writer.WriterParameter writerParameter = writer.getParameter();

                                writerParameter.setUsername(param.getOracleUsername());
                                writerParameter.setPassword(param.getOraclePassword());
                                writerParameter.setTruncate("true");
                                writerParameter.setBatchSize("128");

                                writerParameter.getConnection().forEach(connection -> connection.setJdbcUrl(param.getOracleConnectionUrl()));

                            }).collect(Collectors.toList());


                    return item;
                })
                .orElse(null);

    }


    @PostMapping(path = "/generateDataXJsonFile")
    @ApiOperation(value = "生成DataX的json数据的多个文件,reader:Mysql,writer:Oracle")
    public OracleSqlRestRes generateDataXJsonFile(@NonNull DataXRestReqVo param) {

        DataXConfigBean dataXConfigBean = generateDataXJson(param);

        String pathName = dataxConfigJsonBasePath + "dataXScript";
        File scriptFilePath = new File(pathName);
        if (!scriptFilePath.exists()) {
            scriptFilePath.mkdirs();
        }
        Arrays.stream(scriptFilePath.listFiles()).forEach(f -> f.delete());


        AtomicInteger integer = new AtomicInteger(1);
        dataXConfigBean.getJob().getContent().forEach(content -> {

            DataXConfigBean fileContent = DataXConfigBean.builder()
                    .job(DataXConfigBean.Job.builder()
                            .setting(dataXConfigBean.getJob().getSetting())
                            .content(Collections.singletonList(content))
                            .build())
                    .build();



            String tableName = content.getWriter().getParameter().getConnection().get(0).getTable().get(0);

            if (tableName.toLowerCase().substring(0,1).equalsIgnoreCase("i")){
                //i表分配两个线程并行执行
                fileContent.getJob().getSetting().getSpeed().setChannel(2);
            }

            File fileToWrite = new File(pathName + "/" + integer.getAndIncrement() + "_" + tableName.replaceAll("", "") + ".json");

            try {
                if (!fileToWrite.exists()) {
                    fileToWrite.createNewFile();
                }
                Files.write(JsonUtil.toJson(fileContent).getBytes(), fileToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Arrays.stream(Objects.requireNonNull(scriptFilePath.listFiles((dir, name) -> name.endsWith(".json"))))
                .map(File::getAbsolutePath)
                .sorted(((o1, o2) -> {
                    String o1Number = o1.substring(o1.lastIndexOf("/")+1,o1.indexOf("_"));
                    String o2Number = o2.substring(o2.lastIndexOf("/")+1,o2.indexOf("_"));
                    return Integer.parseInt(o1Number) - Integer.parseInt(o2Number);
                }))
                .forEachOrdered(scriptName -> {
                    String shellCmd = String.format("python /tmp/datax/bin/datax.py %s", scriptName);
                    ShellExecutorUtil.executeShell(shellCmd, false);
                });


        return OracleSqlRestRes.builder().oracleSql(pathName).build();

    }


}
