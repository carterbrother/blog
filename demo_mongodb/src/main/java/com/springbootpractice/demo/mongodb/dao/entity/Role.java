package com.springbootpractice.demo.mongodb.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 3:00 下午
 **/
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role  implements Serializable {
    private static final long serialVersionUID = -4843967519549630720L;

    private Long id;

    @Field("role_name")
    private String roleName;

    private String note;
}
