package com.springbootpractice.demo_springmvc2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户实体
 * @date 2019年05月16日 3:45 PM
 * @Copyright (c) carterbrother
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer sex;

}
