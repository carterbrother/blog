package com.spring.story.demo.openfeign.web;

import com.spring.story.demo.openfeign.out.ProviderAClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Carter.li
 */
@RestController
@Slf4j
public class TestController {

    private final ProviderAClient providerAClient;

    public TestController(ProviderAClient providerAClient) {
        this.providerAClient = providerAClient;
    }

    @GetMapping("/test")
    public String test(@RequestParam("a") String a){
        Assert.isTrue(Objects.nonNull(a),"name不能为空");
        return providerAClient.hello(a);
    }

}
