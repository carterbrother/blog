package com.springbootpractice.demo.redis.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 说明：订单
 * @author carter
 * 创建时间： 2020年01月11日 12:04 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo implements Serializable {

    private static final long serialVersionUID = -6429900368586334135L;

    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDateTime created;

}
