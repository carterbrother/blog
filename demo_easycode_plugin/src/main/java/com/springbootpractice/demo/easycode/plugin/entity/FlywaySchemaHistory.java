package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (FlywaySchemaHistory)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class FlywaySchemaHistory implements Serializable {
    private static final long serialVersionUID = 553405083275866412L;
    
    private Integer installedRank;
    
    private String version;
    
    private String description;
    
    private String type;
    
    private String script;
    
    private Integer checksum;
    
    private String installedBy;
    
    private Date installedOn;
    
    private Integer executionTime;
    
    private Object success;


    public Integer getInstalledRank() {
        return installedRank;
    }

    public void setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    public Date getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(Date installedOn) {
        this.installedOn = installedOn;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }

}