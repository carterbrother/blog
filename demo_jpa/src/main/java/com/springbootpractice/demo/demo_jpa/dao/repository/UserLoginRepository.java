package com.springbootpractice.demo.demo_jpa.dao.repository;

import com.springbootpractice.demo.demo_jpa.dao.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 4:06 下午
 **/
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {

    /**
     * 通过名称模糊查询得到用户列表
     * @param username
     * @return
     */
    @Query("from userLogin where userName like concat('%',?1,'%')")
    List<UserLoginEntity> findUserLogin(String username);

    /**
     * 按照名字模糊查询
     * @param username
     * @return
     */
    List<UserLoginEntity> findByUserNameIsLike(String username);


}
