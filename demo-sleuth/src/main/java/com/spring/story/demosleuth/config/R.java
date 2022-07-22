package com.spring.story.demosleuth.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Carter.li
 * @createtime 2022/7/22 18:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    private Integer ret;
    private String msg;
    private T data;
    private String traceId;

    public static <T> R<T> ofData(T data){
        return ofData(data,null);
    }

    public static <T> R<T> ofData(T data,String traceId){
        return new R<T>(1,"success",data,traceId);
    }

}
