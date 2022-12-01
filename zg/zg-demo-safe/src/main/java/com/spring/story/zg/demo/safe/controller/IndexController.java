package com.spring.story.zg.demo.safe.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/10/27  19:02
 **/
@RestController
public class IndexController {


    @PostMapping(path = "/v3/user/login/you")
    public JSONObject you(HttpServletRequest request){

        //接受参数

        String postBody = "";
        String time = "";

        String  aseKey = "";

        String body = "";

        //todo 处理业务逻辑

        JSONObject res = null;

        //响应信息加密
        String resM = "";
        return new JSONObject().fluentPut("data",resM);

    }

}
