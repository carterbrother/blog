package com.springbootpractice.demo.tx.transactional.biz;

import com.springbootpractice.demo.tx.transactional.dao.entity.UserLoginEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月08日 7:29 下午
 **/
@Service
public class BatchUserLoginBiz {

    private final UserLoginBiz userLoginBiz;

    public BatchUserLoginBiz(UserLoginBiz userLoginBiz) {
        this.userLoginBiz = userLoginBiz;
    }


    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUserLoginListRequired(List<UserLoginEntity> userLoginEntityList){

        AtomicInteger count = new AtomicInteger();
        userLoginEntityList.forEach(userLoginEntity -> {

            count.addAndGet(userLoginBiz.insertUserRequiredNew(userLoginEntity));

        });

        return count.get();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUserLoginListRequiredNew(List<UserLoginEntity> userLoginEntityList){

        AtomicInteger count = new AtomicInteger();
        userLoginEntityList.forEach(userLoginEntity -> {

            count.addAndGet(userLoginBiz.insertUserRequiredNew(userLoginEntity));

        });

        return count.get();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUserLoginListNest(List<UserLoginEntity> userLoginEntityList){

        AtomicInteger count = new AtomicInteger();
        userLoginEntityList.forEach(userLoginEntity -> {
            count.addAndGet(userLoginBiz.insertUserNest(userLoginEntity));

        });

        return count.get();
    }
}
