package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserStatistic)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserStatistic implements Serializable {
    private static final long serialVersionUID = -43590959298378795L;
    
    private Date statisticDate;
    
    private Integer total;
    
    private Integer app;
    
    private Integer weixin;
    
    private Integer appWeixin;


    public Date getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(Date statisticDate) {
        this.statisticDate = statisticDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public Integer getWeixin() {
        return weixin;
    }

    public void setWeixin(Integer weixin) {
        this.weixin = weixin;
    }

    public Integer getAppWeixin() {
        return appWeixin;
    }

    public void setAppWeixin(Integer appWeixin) {
        this.appWeixin = appWeixin;
    }

}