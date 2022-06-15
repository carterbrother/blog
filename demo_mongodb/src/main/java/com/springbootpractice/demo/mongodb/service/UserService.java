package com.springbootpractice.demo.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springbootpractice.demo.mongodb.dao.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：mongodb操作代码
 * @author carter
 * 创建时间： 2020年01月15日 3:02 下午
 **/
@Service
public class UserService {

    private final MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    public DeleteResult deleteUser(Long id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, User.class);
    }

    public List<User> findUser(String userName, String note, int skip, int limit) {
        final Criteria criteria = Criteria.where("userName")
                .regex(userName)
                .and("note")
                .regex(note);
        Query query = Query.query(criteria)
                .limit(limit)
                .skip(skip);
        return mongoTemplate.find(query, User.class);
    }

    public UpdateResult updateUser(Long id, String userName, String note) {
        Update update = new Update();
        update.set("userName", userName);
        update.set("note", note);
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    public User getUser(Long id) {
        return mongoTemplate.findById(id, User.class);
    }
}
