package com.springbootpractice.demo_intercepter.core;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 系统类型
 * @date 2019年05月17日 10:50 AM
 * @Copyright (c) carterbrother
 */
public enum AppType {
    /*OA系统*/
    OA(1,"OA系统"),
    /*患者端APP*/
    PatientApp(2,"患者端APP"),
    /*医生端APP*/
    DoctorApp(3,"医生端APP"),
    /*患者端小程序*/
    PatientSmallProgram(4,"患者端小程序"),
    /*AppType5*/
    AppType5(5,"5"),
    /*AppType6*/
    AppType6(6,"6"),
    /*AppType7*/
    AppType7(7,"7"),
    /*AppType8*/
    AppType8(8,"8"),
    /*AppType9*/
    AppType9(9,"9"),
    ;
    private Integer appId;
    private String appName;


    AppType(Integer appId, String appName){
        this.appId = appId;
        this.appName = appName;
    }

    public String appName() {
        return appName;
    }

    public Integer appId() {
        return appId;
    }

    /**
     * 通过appId找到对应的AppType枚举
     * @param appId appId
     * @return AppType对应的枚举
     * @throws NullPointerException appId为空
     * @throws IllegalArgumentException appId无法找到对应的AppType
     */
    public static AppType fromAppId(Integer appId){
        if (Objects.isNull(appId)){
            throw new  NullPointerException("appId不能为空");
        }

        return Arrays.stream(AppType.values())
                .filter((item)->Objects.equals(item.appId(),appId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("appId[%d]没有对应的AppType枚举",appId)));
    }


}
