package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 患者信息(UserPatientInfo)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserPatientInfo implements Serializable {
    private static final long serialVersionUID = -13843403332224975L;
    /**
    * 用户id
    */
    private Long id;
    /**
    * 患者编号,参加课题才有
    */
    private String patientNo;
    /**
    * 抽烟年限
    */
    private Object smokeTime;
    /**
    * 抽烟频率根/天
    */
    private Object smokeRate;
    /**
    * 喝酒年限
    */
    private Object drinkTime;
    /**
    * 喝酒频率
    */
    private Object drinkRate;
    /**
    * 课题ID,非课题则留空
    */
    private Long topicId;
    /**
    * 既往病史
    */
    private String historyDisease;
    /**
    * 家族病史
    */
    private String familyDisease;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 修改时间
    */
    private Date updated;
    /**
    * 1删除0未删除
    */
    private Object deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public Object getSmokeTime() {
        return smokeTime;
    }

    public void setSmokeTime(Object smokeTime) {
        this.smokeTime = smokeTime;
    }

    public Object getSmokeRate() {
        return smokeRate;
    }

    public void setSmokeRate(Object smokeRate) {
        this.smokeRate = smokeRate;
    }

    public Object getDrinkTime() {
        return drinkTime;
    }

    public void setDrinkTime(Object drinkTime) {
        this.drinkTime = drinkTime;
    }

    public Object getDrinkRate() {
        return drinkRate;
    }

    public void setDrinkRate(Object drinkRate) {
        this.drinkRate = drinkRate;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getHistoryDisease() {
        return historyDisease;
    }

    public void setHistoryDisease(String historyDisease) {
        this.historyDisease = historyDisease;
    }

    public String getFamilyDisease() {
        return familyDisease;
    }

    public void setFamilyDisease(String familyDisease) {
        this.familyDisease = familyDisease;
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