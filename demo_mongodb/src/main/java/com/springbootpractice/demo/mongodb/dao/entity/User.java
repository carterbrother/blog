package com.springbootpractice.demo.mongodb.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 2:43 下午
 **/
@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private Long id;

    @Field("user_name")
    private String userName;

    private String note;

    private List<Role> roles ;

}
