package com.springbootpractice.demo.mongodb;

import com.springbootpractice.demo.mongodb.dao.entity.Role;
import com.springbootpractice.demo.mongodb.dao.entity.User;
import com.springbootpractice.demo.mongodb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoMongodbApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void mongodbTest() {

        IntStream.rangeClosed(1, 10).forEach((item) -> {
            final long id = System.currentTimeMillis();
            userService.saveUser(User.builder().id(id)
                    .userName("user")
                    .note("note")
                    .roles(Arrays.asList(Role.builder().id(id)
                                    .roleName("role" + id)
                                    .note("note" + id)
                                    .build(),
                            Role.builder().id(id + 1000000)
                                    .roleName("_role" + id)
                                    .note("_note" + id)
                                    .build()
                    ))
                    .build());

        });

        userService.findUser("user", "note", 0, 30)
                .forEach(System.out::println);

    }

}
