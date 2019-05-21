package com.springx.bootdubbo.common.bean;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Rpm预警kafka消息实体，状态变更的时候推送消息；
 * @date 2019年03月23日 4:10 PM
 * @Copyright (c) carterbrother
 */
public class RpmWarningKafkaBean implements Serializable {
    /**
     * 医生ID，如果没有没有，传空；
     */
    private Long doctorId;
    /**
     * 患者ID,必须有，标注是哪个患者用户的
     */
    private Long patientId;
    /**
     * 顶级预警类型，1，随访 2，设备数据 3，im消息
     */
    private Long topType;
    /**
     * 子级别的预警类型,比如设备数据，1 ,2 ,3 , n 标识不同的 子类型， 最大64
     */
    private Long subType;
    /**
     * 预警发生为true， 预警解除为 false;
     */
    private Boolean happen;


    public Long getTopType() {
        return topType;
    }

    public void setTopType(Long topType) {
        this.topType = topType;
    }

    public Long getSubType() {
        return subType;
    }

    public void setSubType(Long subType) {
        this.subType = subType;
    }

    public Boolean getHappen() {
        return happen;
    }

    public void setHappen(Boolean happen) {
        this.happen = happen;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RpmWarningKafkaDto{");
        sb.append("topType=").append(topType);
        sb.append(", subType='").append(subType).append('\'');
        sb.append(", happen=").append(happen);
        sb.append('}');
        return sb.toString();
    }
}
