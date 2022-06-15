package com.springbootpractice.demo.p6spy.web;

import com.springbootpractice.demo.p6spy.dao.entity.CoffeEntity;
import com.springbootpractice.demo.p6spy.service.CoffeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 说明：控制器
 * @author carter
 * 创建时间： 2020年02月16日 9:10 下午
 **/
@RestController
public class CoffeController {

    private final CoffeService coffeService;

    public CoffeController(CoffeService coffeService) {
        this.coffeService = coffeService;
    }

    @GetMapping("findAll")
    public List<CoffeEntity> findAll(){
        return coffeService.findAll();
    }

    @PostMapping("/save")
    public CoffeEntity save(@RequestBody CoffeEntity param){
        return coffeService.save(param);
    }




}
