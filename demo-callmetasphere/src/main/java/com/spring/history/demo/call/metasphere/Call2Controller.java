package com.spring.history.demo.call.metasphere;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Carter.li
 * @createtime 2022/7/14 9:16
 */
@RestController
@Slf4j
public class Call2Controller {

    private String baseUrl = "http://10.10.1.47:8081/";
    public static final String accessKey = "uXLvWli8nxXA4C7G";
    public static final String secretKey = "Cfv7JtXKmc74w9bJ";
    public static final String projectName = "cycube_dev";
    public static final String testPlanName = "sprint1";
    public static final String envName = "dev";

    public static final String event = "xxx服务更新-内网meterSphere测试";
    //企业微信的URL

   public static final String webhookUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=b9e516b2-9418-4261-b018-60e4ee4342c2";



    @GetMapping(path = "/callMeterSphere2")
    public String callMeterSphere() {

        String projectId = getId("project/listAll", Method.GET, Collections.emptyMap(), "name", projectName, "id");

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("projectId", projectId);

        String testPlanId = getId("test/plan/list/all", Method.POST, dataMap, "name", testPlanName, "id");

        String userId = getDataValueStr("currentUser", Method.GET, Collections.emptyMap(), "id");

        log.info("userId: {}", userId);

        //run test plan
        Map<String, Object> bodyMapForRun = new HashMap<>(8);

        bodyMapForRun.put("testPlanId", testPlanId);
        bodyMapForRun.put("projectId", projectId);
        bodyMapForRun.put("userId", userId);
        bodyMapForRun.put("triggerMode", "API");
        bodyMapForRun.put("mode", "serial");
        Map<String, Object> envMap = new HashMap<>(2);
        envMap.put(projectId, envName);
        bodyMapForRun.put("envMap", envMap);

        String reportId = getDataStr("test/plan/run", Method.POST, bodyMapForRun);

        Map<String, Object> shareBodyMap = new HashMap<>(4);
        shareBodyMap.put("customData", reportId);
        shareBodyMap.put("shareType", "PLAN_DB_REPORT");
        shareBodyMap.put("lang", null);

        String shareId = getDataValueStr("share/info/generateShareInfoWithExpired", Method.POST, shareBodyMap,  "shareUrl");

        String reportUrl = baseUrl + "sharePlanReport" + shareId;

        //发送通知到企业微信
        String content = "# <font color=\"info\">自动化API测试结果通知</font> \n".concat("## <font color=\"comment\">"+event + "</font>\n").concat("<font color=\"warning\">[点我打开测试报告-有效期24H]("+reportUrl+")</font>");

        Map<String,Object> bodyMapCall = new HashMap<>(2);
        bodyMapCall.put("msgtype","markdown");
        bodyMapCall.put("markdown",Collections.singletonMap("content", content));
//        HttpResponse callRes = HttpUtil.createPost(webhookUrl).body(JSONUtil.toJsonStr(bodyMapCall)).contentType("application/json").charset(StandardCharsets.UTF_8).execute();

//        log.info("res: {} ",JSONUtil.toJsonPrettyStr(callRes.body()));

        return reportUrl;
    }


    @SneakyThrows
    private String getSign(String secretKey, String accessKey) {

        String src = accessKey.concat("|").concat(String.valueOf(System.currentTimeMillis()));

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv1 = new IvParameterSpec(accessKey.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv1);

        return Base64.encode(cipher.doFinal(src.getBytes(StandardCharsets.UTF_8)));
    }


    private String getId(String apiPath, Method method, Map<String, Object> bodyMap, String filterKey, String filterValue, String returnFieldId) {


        String runUrl = baseUrl + apiPath;
        String sign = getSign(secretKey, accessKey);

        HttpRequest httpRequest = HttpUtil.createRequest(method, runUrl).header("accessKey", accessKey).header("signature", sign);
        if (CollectionUtil.isNotEmpty(bodyMap)) {
            httpRequest = httpRequest.body(JSONUtil.toJsonStr(bodyMap));
        }
        HttpResponse testPlanRes = httpRequest.execute();

        log.info("{} res: {}", runUrl, JSONUtil.toJsonPrettyStr(testPlanRes.body()));


        JSONArray dataJsonArray = JSONUtil.parseObj(testPlanRes.body()).getJSONArray("data");

        return dataJsonArray.toList(JSONObject.class).stream().filter(item -> {
            if (StringUtils.hasText(filterKey) && StringUtils.hasText(filterValue)) {
                return Objects.equals(item.getStr(filterKey), filterValue);
            }
            return true;

        }).findFirst().map(jsonObj -> jsonObj.getStr(returnFieldId)).orElse("");


    }

    private String getDataStr(String apiPath, Method method, Map<String, Object> bodyMap) {


        String runUrl = baseUrl + apiPath;
        String sign = getSign(secretKey, accessKey);

        HttpRequest httpRequest = HttpUtil.createRequest(method, runUrl).header("accessKey", accessKey).header("signature", sign);
        if (CollectionUtil.isNotEmpty(bodyMap)) {
            httpRequest = httpRequest.body(JSONUtil.toJsonStr(bodyMap));
        }
        HttpResponse testPlanRes = httpRequest.execute();

        log.info("{} res: {}", runUrl, JSONUtil.toJsonPrettyStr(testPlanRes.body()));


        return JSONUtil.parseObj(testPlanRes.body()).getStr("data");

    }

    private String getDataValueStr(String apiPath, Method method, Map<String, Object> bodyMap,String key) {


        String runUrl = baseUrl + apiPath;
        String sign = getSign(secretKey, accessKey);

        HttpRequest httpRequest = HttpUtil.createRequest(method, runUrl).header("accessKey", accessKey).header("signature", sign);
        if (CollectionUtil.isNotEmpty(bodyMap)) {
            httpRequest = httpRequest.body(JSONUtil.toJsonStr(bodyMap));
        }
        HttpResponse testPlanRes = httpRequest.execute();

        log.info("{} res: {}", runUrl, JSONUtil.toJsonPrettyStr(testPlanRes.body()));


        return JSONUtil.parseObj(testPlanRes.body()).getJSONObject("data").getStr(key);

    }


}
