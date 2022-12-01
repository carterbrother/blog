package com.spring.story.demo.cache.pre.dao.repository;

import com.spring.story.demo.cache.pre.dao.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity,String> {

}
