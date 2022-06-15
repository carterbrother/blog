package com.springbootpractice.demo.easycode.plugin.entity;

import java.io.Serializable;

/**
 * (Memberstatus)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class Memberstatus implements Serializable {
    private static final long serialVersionUID = -59677191619248170L;
    
    private Integer id;
    
    private String memberid;
    
    private String lastestdata;
    
    private Integer userid;
    
    private String usercreated;
    
    private Object flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getLastestdata() {
        return lastestdata;
    }

    public void setLastestdata(String lastestdata) {
        this.lastestdata = lastestdata;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsercreated() {
        return usercreated;
    }

    public void setUsercreated(String usercreated) {
        this.usercreated = usercreated;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

}