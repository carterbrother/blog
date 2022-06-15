package com.springbootpractice.demo.spring.security1.param.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 说明：删除，新增，更新结果
 * @author carter
 * 创建时间： 2020年02月08日 6:34 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestRes implements Serializable {
    private static final long serialVersionUID = 5913028066781569831L;

    private Long id;
}
