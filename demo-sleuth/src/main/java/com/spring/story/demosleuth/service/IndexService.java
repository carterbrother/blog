package com.spring.story.demosleuth.service;

import com.spring.story.demosleuth.dao.IndexDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Carter.li
 * @createtime 2022/7/22 17:46
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class IndexService {

    private final IndexDao indexDao;

    public void execute(String name) {

        log.warn("execute call ! {}",name);

        indexDao.save(name);

    }
}
