package com.lifesense.sz.research_formimageread.web;

import com.lifesense.sz.research_formimageread.service.AIPService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 测试图片识别
 * @date 2019年02月18日 2:50 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class TestTableImageReadController {

    @Autowired
    private AIPService aipService;

    @GetMapping("test")
    @ResponseBody
    public String test(String imageName) {
        final JSONObject tableContent = aipService.getTableContent(imageName);
        if (Objects.isNull(tableContent)){
            return "返回内容为空";
        }

        return JSONObject.valueToString(tableContent);
    }

    @GetMapping("testText")
    @ResponseBody
    public String testText(String imageName) {
        return JSONObject.valueToString(aipService.getText(imageName));
    }
}
