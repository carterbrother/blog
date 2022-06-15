package com.springbootpractice.demo.data.dict.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author carter
 * create_date  2021/3/19 20:33
 * description     持续集成接口（打包，打镜像）
 */
@RestController
@RequestMapping("/ci")
public class CiController {


    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @GetMapping(path = "/index")
    @ApiIgnore
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("ci");
        modelAndView.addObject("contextPath", contextPath);
        return modelAndView;
    }


}
