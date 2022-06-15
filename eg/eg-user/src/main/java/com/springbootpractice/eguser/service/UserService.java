package com.springbootpractice.eguser.service;

import com.springbootpractice.api.user.dto.UserPro;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户的业务类
 * @date 2019年06月20日 16:43
 * @Copyright (c) carterbrother
 */
@Service
public class UserService {

    private static Map<Long,UserPro> userProMap = new ConcurrentHashMap<>();

    public UserPro getUserPro(Long id) {
        Assert.isTrue(Objects.nonNull(id) && id > 0 , "用户id必须大于0");
        Assert.isTrue(userProMap.containsKey(id),"用户id不存在");
        return userProMap.get(id);
    }

    public Map<String, Object> updateUserName(String userName, Long id) {

        Assert.isTrue(Objects.nonNull(id) && id > 0 , "用户id必须大于0");

        Assert.isTrue(userProMap.containsKey(id),"用户id不存在");
        Assert.isTrue(!StringUtils.isEmpty(userName),"用户名不能为空");
        final UserPro userPro = userProMap.get(id);
        Assert.isTrue(!Objects.equals(userName, userPro.getUserName()),"请不要重复修改同样的用户名");

        userPro.setUserName(userName);

        Map<String,Object> dataMap = new HashMap<>(2);
        dataMap.put("success",true);
        dataMap.put("message", MessageFormat.format("更新用户信息id:{1}的名称为:{2}成功",id,userName));

        return dataMap;
    }

    public Map<String, Object> insertUser(UserPro userPro) {

        Assert.notNull(userPro,"用户信息不能为空");
        Long id = userPro.getId();
        Assert.isTrue(Objects.nonNull(id) && id > 0 , "用户id必须大于0");

        Assert.isTrue(!userProMap.containsKey(id),"用户id已经存在");

        String userName = userPro.getUserName();
        Assert.isTrue(!StringUtils.isEmpty(userName),"用户名不能为空");

        userProMap.put(id,userPro);


        Map<String,Object> dataMap = new HashMap<>(2);
        dataMap.put("success",true);
        dataMap.put("message", MessageFormat.format("插入用户信息{1}成功",userName));


        return dataMap;
    }
}
