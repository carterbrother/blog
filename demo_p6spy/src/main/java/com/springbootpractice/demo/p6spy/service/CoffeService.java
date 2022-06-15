package com.springbootpractice.demo.p6spy.service;

import com.google.common.collect.Lists;
import com.springbootpractice.demo.p6spy.dao.entity.CoffeEntity;
import com.springbootpractice.demo.p6spy.dao.repository.CoffeRepositry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：业务代码
 * @author carter
 * 创建时间： 2020年02月16日 9:33 下午
 **/
@Service
public class CoffeService {

    private final CoffeRepositry coffeRepositry;

    public CoffeService(CoffeRepositry coffeRepositry) {
        this.coffeRepositry = coffeRepositry;
    }

    public List<CoffeEntity> findAll() {
        return Lists.newArrayList(coffeRepositry.findAll());
    }

    public CoffeEntity getByName(String name) {
        return coffeRepositry.findByName(name);
    }

    public CoffeEntity save(CoffeEntity param) {
        return coffeRepositry.save(param);
    }
}
