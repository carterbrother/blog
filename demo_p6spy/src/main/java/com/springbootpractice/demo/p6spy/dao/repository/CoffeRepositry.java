package com.springbootpractice.demo.p6spy.dao.repository;

import com.springbootpractice.demo.p6spy.dao.entity.CoffeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：接口
 * @author carter
 * 创建时间： 2020年02月16日 9:36 下午
 **/
@Repository
public interface CoffeRepositry extends JpaRepository<CoffeEntity,Long> {
    CoffeEntity findByName(String name);
}
