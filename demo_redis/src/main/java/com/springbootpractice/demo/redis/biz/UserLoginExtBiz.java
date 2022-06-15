package com.springbootpractice.demo.redis.biz;

import com.springbootpractice.demo.redis.dao.entity.UserLoginExtEntity;
import com.springbootpractice.demo.redis.dao.mapper.UserLoginExtEntityMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 说明：操作user的数据增强层
 * @author carter
 * 创建时间： 2020年01月21日 6:40 下午
 **/
@Service
public class UserLoginExtBiz {

    private final UserLoginExtEntityMapper userLoginExtEntityMapper;

    public UserLoginExtBiz(UserLoginExtEntityMapper userLoginExtEntityMapper) {
        this.userLoginExtEntityMapper = userLoginExtEntityMapper;
    }

    @Cacheable(value = "redisCache",key = "'getById:'+#id")
    public UserLoginExtEntity getById(Integer id){
        return userLoginExtEntityMapper.selectByPrimaryKey(id);
    }

    @CachePut(value = "redisCache",key = "'getById:'+#param.id")
    public UserLoginExtEntity updateUserLoginExt(UserLoginExtEntity param){
        userLoginExtEntityMapper.updateByPrimaryKeySelective(param);
        return param;
    }

    @CacheEvict(value = "redisCache",key = "'getById:'+#id")
    public int deleteUserLoginExt(Integer id){
       return userLoginExtEntityMapper.logicalDeleteByPrimaryKey(id);
    }

}
