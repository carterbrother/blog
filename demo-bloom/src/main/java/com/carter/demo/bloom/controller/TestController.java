package com.carter.demo.bloom.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.carter.demo.bloom.service.SensitiveServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carter.li
 * @createtime 2023/4/3 19:36
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final SensitiveServiceImpl sensitiveServiceImpl;




    @RequestMapping("/testName")
    public JSONObject testName(@RequestParam("name") String name){

        Assert.isTrue(StringUtils.isNotBlank(name),"name should not blank");

        return sensitiveServiceImpl.hasSensitiveWordV2(name);

    }
}
