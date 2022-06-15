package com.springbootpractice.egproduct;

import com.springbootpractice.api.user.UserApi;
import com.springbootpractice.api.user.dto.UserPro;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EgProductApplicationTests {

    @MockBean
    private UserApi userApi;

    @Test
    public void mockUserApiTest() {

        UserPro mockUserPro = new UserPro();
        mockUserPro.setId(1L);
        mockUserPro.setUserName("xxxx");

        BDDMockito.given(userApi.getUserPro(1L)).willReturn(mockUserPro);


        UserPro userProReturn = userApi.getUserPro(1L);

        Assert.assertEquals(userProReturn,mockUserPro);
    }

}
