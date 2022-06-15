package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 灰度策略(GrayStrategy)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class GrayStrategy implements Serializable {
    private static final long serialVersionUID = -85532116441445107L;
    
    private Object id;
    /**
    * 策略类型:0-按login_id取模灰度
    */
    private Object strategyType;
    /**
    * 余数
    */
    private Object remainder;
    /**
    * 分母
    */
    private Object denominator;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 更新时间
    */
    private Date updated;
    /**
    * 删除状态
    */
    private Object deleted;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Object strategyType) {
        this.strategyType = strategyType;
    }

    public Object getRemainder() {
        return remainder;
    }

    public void setRemainder(Object remainder) {
        this.remainder = remainder;
    }

    public Object getDenominator() {
        return denominator;
    }

    public void setDenominator(Object denominator) {
        this.denominator = denominator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

}