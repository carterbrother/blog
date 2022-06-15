package com.springbootpractice.eguser;

import com.springbootpractice.api.user.dto.UserPro;
import com.springbootpractice.eguser.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EgUserApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testUserService() {

        UserPro userPro = new UserPro();
        userPro.setId(1L);
        userPro.setUserName("xxxaaa");

        final Map<String, Object> map = userService.insertUser(userPro);

        Assert.assertEquals("插入失败",true,map.get("success"));


        final UserPro userProReturn = userService.getUserPro(1L);

        Assert.assertEquals(userPro,userProReturn);


    }


    @Test
    public void testUserRest() {
        UserPro userPro = new UserPro();
        userPro.setId(2L);
        userPro.setUserName("BBBB");

         Map map = testRestTemplate.postForObject("/insert", userPro, Map.class);
        Assert.assertEquals("插入失败",true,map.get("success"));


        UserPro userProReturn = testRestTemplate.getForObject("/user/{id}", UserPro.class, 2L);
        Assert.assertEquals(userPro,userProReturn);
    }

}
