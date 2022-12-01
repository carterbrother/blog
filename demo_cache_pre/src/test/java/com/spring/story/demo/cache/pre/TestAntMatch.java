package com.spring.story.demo.cache.pre;

import org.springframework.util.AntPathMatcher;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/18  17:42
 **/
public class TestAntMatch {

    public static void main(String[] args) {

        String pat="/**/actuator/health";

        String path="/api/common/actuator/health";

        boolean match = new AntPathMatcher().match(pat, path);

        System.out.println(path +"  match: " + pat +" res: "+ match);

    }

}
