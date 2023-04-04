package com.spring.story.demo.cos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/12/20  12:12
 **/
@Controller
public class RController {


    @RequestMapping("/echo")
    @ResponseBody
    public Object echo(HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> params = new HashMap<>();

        params.put("uri",request.getRequestURI());
        params.put("RemoteAddr",request.getRemoteAddr());
        params.put("domian",request.getServerName());
        params.put("params",request.getParameterMap());

        return params;

    }

    @RequestMapping("/queryS")
    @ResponseBody
    public Object echo(HttpServletRequest request) {

        Map<String,Object> params = new HashMap<>();

        params.put("uri",request.getRequestURI());
        params.put("RemoteAddr",request.getRemoteAddr());
        params.put("domian",request.getServerName());
        params.put("params",request.getParameterMap());
        params.put("queryString",request.getQueryString());

        return params;

    }


}
