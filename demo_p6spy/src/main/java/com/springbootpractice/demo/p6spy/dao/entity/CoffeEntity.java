package com.springbootpractice.demo.p6spy.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年02月16日 9:23 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_coffe")
@Entity
public class CoffeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDateTime created;

    private LocalDateTime updated;

}
