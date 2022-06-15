package com.springbootpractice.demo.data.dict.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author carter
 * create_date  2020/5/17 20:33
 * description     页面配置
 */
@RestController
public class PageController {

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;



    @GetMapping(path = "/datac/index")
    @ApiIgnore
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("datac");
        modelAndView.addObject("contextPath",contextPath);
        return modelAndView;
    }


    @GetMapping(path = "/log")
    @ApiIgnore
    public ModelAndView log() {
        ModelAndView modelAndView = new ModelAndView("log");
        modelAndView.addObject("port",port);
        modelAndView.addObject("contextPath",contextPath);
        return modelAndView;
    }

    @GetMapping(path = "/")
    @ApiIgnore
    public ModelAndView def() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("contextPath",contextPath);
        return modelAndView;
    }

    @GetMapping(path = "/dict/index")
    @ApiIgnore
    public ModelAndView dict() {

        ModelAndView modelAndView = new ModelAndView("dict");
        modelAndView.addObject("contextPath",contextPath);
        return modelAndView;

    }

    @GetMapping(path = "/datax/index")
    @ApiIgnore
    public ModelAndView indexDatax() {

        ModelAndView modelAndView = new ModelAndView("datax");
        modelAndView.addObject("contextPath",contextPath);
        return modelAndView;
    }



}
