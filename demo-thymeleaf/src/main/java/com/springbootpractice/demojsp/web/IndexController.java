package com.springbootpractice.demojsp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**¡™
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月03日 6:11 下午
 **/
@Controller
public class IndexController
{

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
