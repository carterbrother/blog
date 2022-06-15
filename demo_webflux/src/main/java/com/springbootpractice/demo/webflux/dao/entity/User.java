package com.springbootpractice.demo.webflux.dao.entity;

import com.springbootpractice.demo.webflux.dao.entity.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 6:14 下午
 **/
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 905626961924507351L;

    @Id
    private Long id;

    @Field("user_name")
    private String userName;

    private String note;


    private SexEnum sex;

}
