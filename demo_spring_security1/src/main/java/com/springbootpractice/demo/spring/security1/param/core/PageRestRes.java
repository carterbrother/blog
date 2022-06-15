package com.springbootpractice.demo.spring.security1.param.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：分页结果
 * @author carter
 * 创建时间： 2020年02月08日 6:30 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRestRes implements Serializable {

    private Long total;
    private Long pageIndex;
    private Long pageSize;
    private List<Object> dataList;


}
