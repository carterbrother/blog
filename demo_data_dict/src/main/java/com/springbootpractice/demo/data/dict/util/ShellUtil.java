package com.springbootpractice.demo.data.dict.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author carter
 * create_date  2020/5/14 15:13
 * description     shell执行工具类
 */
@Slf4j
public class ShellUtil {


    public static boolean executeLocalShell(String cmd, Consumer<List<String>> consumer) throws Exception {

        List<String> lines = new LinkedList<>();
        StringBuilder errors = new StringBuilder();

        BufferedReader bufferedInfo = null;
        BufferedReader bufferedErr = null;

        try {
            Process process = Runtime.getRuntime().exec(cmd);

            bufferedInfo = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bufferedErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));


            String line;



            //读取正常返回信息
            while (Objects.nonNull(line = bufferedInfo.readLine())) {
                lines.add(line);
            }
            //消费返回信息
            consumer.accept(lines);

            //打印出错误信息，如果有；
            while (Objects.nonNull(line = bufferedErr.readLine())) {
                log.error("{}", line);
                errors.append(line);
            }
            //返回处理失败；
            if (Strings.isNotBlank(errors.toString())){
                return false;
            }


            //阻塞执行线程直至脚本执行完成后返回
            process.waitFor();

            log.info("指令{}- 处理结果:{}!", cmd, errors.toString());

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            if (Objects.nonNull(bufferedErr)) {
                try {
                    bufferedErr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    bufferedInfo.close();
                }
            }
        }

        return false;


    }


    public static void main(String[] args) throws Exception {
        ShellUtil.executeLocalShell("ps -ef ",a->System.out.println(a));
    }

}
