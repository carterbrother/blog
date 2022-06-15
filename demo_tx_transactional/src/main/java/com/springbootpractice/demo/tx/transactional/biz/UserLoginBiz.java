package com.springbootpractice.demo.tx.transactional.biz;

import com.springbootpractice.demo.tx.transactional.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.tx.transactional.dao.mapper.UserLoginEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明：数据操作封装
 * @author carter
 * 创建时间： 2020年01月08日 6:42 下午
 **/
@Service
public class UserLoginBiz {

    @Autowired
    private UserLoginEntityMapper userLoginEntityMapper;


    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public UserLoginEntity getUserById(Integer id) {
        return userLoginEntityMapper.selectByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public Integer insertUser(UserLoginEntity userLoginEntity) {
        return userLoginEntityMapper.insertSelective(userLoginEntity);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.REQUIRED)
    public Integer insertUserRequired(UserLoginEntity userLoginEntity) {
        return userLoginEntityMapper.insertSelective(userLoginEntity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.REQUIRES_NEW)
    public Integer insertUserRequiredNew(UserLoginEntity userLoginEntity) {
        return userLoginEntityMapper.insertSelective(userLoginEntity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
    public Integer insertUserNest(UserLoginEntity userLoginEntity) {
        return userLoginEntityMapper.insertSelective(userLoginEntity);
    }


}
