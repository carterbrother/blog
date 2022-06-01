package com.spring.story.demo.openfeign.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Carter.li
 */
@FeignClient(name="providerA",url = "http://localhost:8080")
public interface ProviderAClient {

    /**
     * providerA提供的接口协议
     * @param name  名字
     * @return 响应信息
     */
    @GetMapping("/hello")
    String hello(@RequestParam(name = "name") String name);
}
