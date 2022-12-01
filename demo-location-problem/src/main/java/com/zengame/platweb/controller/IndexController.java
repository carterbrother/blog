package com.zengame.platweb.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/29  19:02
 **/
@RestController
public class IndexController {

    @GetMapping("/hello")
    public Object hello(String name) {
        return "hello " + Optional.ofNullable(name).filter(StringUtils::hasLength).orElse("Carter");
    }

    @GetMapping("/ex")
    public Object ex() {
        int l = (int) System.currentTimeMillis() % 3;
        switch (l) {
            case 0: {
                throw new RuntimeException("xxx");
            }
            case 1: {
                throw new Error("yyy");
            }
            default: {
                int i = 1 / 0;
                System.out.println(i);
            }

        }
        return "ex";
    }

}
