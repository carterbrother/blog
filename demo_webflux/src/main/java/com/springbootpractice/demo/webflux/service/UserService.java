package com.springbootpractice.demo.webflux.service;

import com.springbootpractice.demo.webflux.dao.entity.User;
import com.springbootpractice.demo.webflux.dao.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 6:38 下午
 **/
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> insertUser(User user) {
        return userRepository.insert(user);
    }

    public Mono<User> updateUser(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    public Flux<User> findByUserNameAndNote(String userName, String note) {
        return userRepository.findByUserNameLikeAndNoteLike(userName, note);
    }

}
