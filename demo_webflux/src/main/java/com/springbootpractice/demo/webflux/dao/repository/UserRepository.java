package com.springbootpractice.demo.webflux.dao.repository;

import com.springbootpractice.demo.webflux.dao.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 6:18 下午
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,Long> {

   Flux<User> findByUserNameLikeAndNoteLike(String userName,String note);

}
