package com.lifesense.sz.research_formimageread.service;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 图片识别服务类
 * @date 2019年02月18日 12:37 PM
 * @Copyright (c) carterbrother
 */
@Service
public class AIPService {

    private static final Logger logger = LoggerFactory.getLogger(AIPService.class);

    @Autowired
    private AipOcr aipOcr;

    @Autowired
    private RetryTemplate retryTemplate;



    public JSONObject getTableContentSync(String imageRealPath) {

        String filePath = "/Users/lifesense-szyf01/Desktop/"+imageRealPath;
        checkFileExist(filePath);
        HashMap<String, String> options = new HashMap<>();
//        options.put("probability", "true");
//        options.put("result_type", "json");

        final JSONObject jsonObject = aipOcr.tableRecognitionAsync(filePath, options);

        if (Objects.isNull(jsonObject)) {
            throw  new IllegalArgumentException("获取百度图片识别的结果的requestId出错");
        }

        return jsonObject;
//        final String error_code = jsonObject.optString("error_code",null);
//        if (Objects.nonNull(error_code) && Long.parseLong(error_code) > 0) {
//            throw new IllegalArgumentException(String.format("log_id:%s , error_msg : %s", jsonObject.getString("log_id"), jsonObject.getString("error_msg")));
//        }
//
//        String requestId = jsonObject.getJSONArray("result").getJSONObject(0).getString("request_id");
//        options.put("result_type", "json");
//
//        try {
//            final JSONObject result = retryTemplate.execute((RetryCallback<JSONObject, Exception>) retryContext -> {
//                        final JSONObject jsonObject1 = aipOcr.tableResultGet(requestId, options);
//                        if (!Objects.equals(jsonObject1.getJSONObject("result").getInt("ret_code"),3)){
//                            throw  new IllegalStateException("结果没完成，稍后重试");
//                        }
//                        return jsonObject1;
//                    },
//                    retryContext -> null);
//            return Objects.requireNonNull(result);
//        } catch (Exception e) {
//            logger.error("重试获取百度图片识别结果异常",e);
//            e.printStackTrace();
//            return null;
//        }


    }


    public JSONObject getTableContent(String imageRealPath) {

        String filePath = "/Users/lifesense-szyf01/Desktop/"+imageRealPath;
        checkFileExist(filePath);
        HashMap<String, String> options = new HashMap<>();
        final JSONObject jsonObject = aipOcr.tableRecognitionAsync(filePath, options);

        if (Objects.isNull(jsonObject)) {
            throw  new IllegalArgumentException("获取百度图片识别的结果的requestId出错");
        }

        final String error_code = jsonObject.optString("error_code",null);
        if (Objects.nonNull(error_code) && Long.parseLong(error_code) > 0) {
            throw new IllegalArgumentException(String.format("log_id:%s , error_msg : %s", jsonObject.getString("log_id"), jsonObject.getString("error_msg")));
        }

        String requestId = jsonObject.getJSONArray("result").getJSONObject(0).getString("request_id");
        options.put("result_type", "json");

        try {
            final JSONObject result = retryTemplate.execute((RetryCallback<JSONObject, Exception>) retryContext -> {
                        final JSONObject jsonObject1 = aipOcr.tableResultGet(requestId, options);
                        if (!Objects.equals(jsonObject1.getJSONObject("result").getInt("ret_code"),3)){
                            throw  new IllegalStateException("结果没完成，稍后重试");
                        }
                        return jsonObject1;
                    },
                    retryContext -> null);
            return Objects.requireNonNull(result);
        } catch (Exception e) {
            logger.error("重试获取百度图片识别结果异常",e);
            e.printStackTrace();
            return null;
        }


    }

    private void checkFileExist(String filePath) {
        assert new File(filePath).exists();
    }


    public JSONObject getText(String imageRealPath) {

        String filePath = "/Users/lifesense-szyf01/Desktop/"+imageRealPath;
        checkFileExist(filePath);
        HashMap<String, String> options = new HashMap<>();
//        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
//        options.put("detect_language", "true");
//        options.put("probability", "true");
        final JSONObject jsonObject1 = aipOcr.basicAccurateGeneral(filePath, options);
        return jsonObject1;
    }
}
