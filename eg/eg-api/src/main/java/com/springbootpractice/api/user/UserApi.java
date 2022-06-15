package com.springbootpractice.api.user;

import com.springbootpractice.api.user.dto.UserPro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 对外的feign API
 * @date 2019年06月20日 16:32
 * @Copyright (c) carterbrother
 */
@FeignClient(name = "user")
public interface UserApi {

    /**获取用户信息
     * @param id  用户ID
     * @return 返回用信息
     */
    @GetMapping("/user/{id}")
    UserPro getUserPro(@PathVariable("id") Long id);

    /**
     * 更新用户的用户名
     * @param userName
     * @return 更新结果
     */
    @PostMapping(path = "/update/{userName}")
    Map<String,Object> updateUserName(@PathVariable("userName") String userName, @RequestHeader("id") Long id);


    /**插入用户信息
     * @param userPro
     * @return
     */
    @PostMapping(path = "/insert")
    Map<String,Object> insertUser(@RequestBody UserPro userPro);


}
