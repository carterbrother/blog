package com.springbootpractice.demo.data.dict.web;

import com.springbootpractice.demo.data.dict.param.core.RestResponseBean;
import com.springbootpractice.demo.data.dict.param.rest.DeployReq;
import com.springbootpractice.demo.data.dict.param.rest.DeployRes;
import com.springbootpractice.demo.data.dict.service.LocalDebugService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author carter
 * create_date  2021/3/19 20:33
 * description     输入服务名，镜像 环境，然后按照本地调测的方式部署
 */
@RestController
@RequestMapping("/local_debug")
public class LocalDebugController {


    private final LocalDebugService localDebugService;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    public LocalDebugController(LocalDebugService localDebugService) {
        this.localDebugService = localDebugService;
    }

    @GetMapping(path = "/index")
    @ApiIgnore
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("ci");
        modelAndView.addObject("contextPath", contextPath);
        return modelAndView;
    }


    @GetMapping(path = "/deploy")
    @ApiIgnore
    public RestResponseBean<DeployRes> deploy(DeployReq deployReq) {

        String appName = deployReq.getAppName();
        String imageName = deployReq.getImageName();
        String envName = deployReq.getEnvName();


        localDebugService.deployService(appName,imageName,envName);


        RestResponseBean response = new RestResponseBean();

        response.setCode(200);
        response.setMsg("部署成功");
        response.setData(DeployRes.builder().logUrl("/xxx").build());

        return response;
    }


}
