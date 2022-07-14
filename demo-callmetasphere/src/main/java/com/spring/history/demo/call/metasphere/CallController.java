package com.spring.history.demo.call.metasphere;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carter.li
 * @createtime 2022/7/14 9:16
 */
@RestController
@Slf4j
public class CallController {

    private String baseRrl = "http://10.10.1.47:8081/";
    public static final String userName = "carter";
    public static final String password = "123456Aa";

    //回归测试场景id

    public static final String scId ="xxx";


    @GetMapping(path = "/callMeterSphere")
    public String callMeterSphere() {

        //1 login get token
        Map<String, Object> loginParamMap = new HashMap<>(4);

        loginParamMap.put("authenticate", "LOCAL");
        //密码是加密传输的，需要下载 meterSphere 代码看看加密算法进行处理 TODO
        loginParamMap.put("username", "LOCAL");
        loginParamMap.put("password", "LOCAL");

        String res = HttpUtil.post(baseRrl.concat("signin"), loginParamMap, 15000);
        log.info("login res: {}",res);
        JSONObject resJson = JSONUtil.parseObj(res);

        String csrfToken = resJson.getJSONObject("data").getStr("csrfToken");

        //2. 请求自动化测试的场景。 TODO  可先查询场景id的配置，再组装运行参数

        String body = "";

        String runUrl = baseRrl + "/api/run";
        HttpResponse httpResponse = HttpUtil.createPost(runUrl).body(body).header("CSRF-TOKEN", csrfToken).execute();

        String reportId = httpResponse.body();
        //3. 提取报告id,组装成测试报告url

        String reportUrl = "http://10.10.1.47:8081/#/api/automation/report/view/"+reportId;


        return reportUrl;
    }


}
