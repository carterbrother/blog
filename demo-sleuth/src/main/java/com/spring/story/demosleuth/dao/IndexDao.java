package com.spring.story.demosleuth.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Carter.li
 * @createtime 2022/7/22 17:49
 */
@Component
@Slf4j
public class IndexDao {

    @Async
    public void save(String name) {

        log.error("save call : {}",name);

        throw new RuntimeException("test throw exception xxxx");

    }
}
