package com.spring.storey.demo.completeable_future.client.dto;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class R <T> {

    private Boolean ret;

    private Integer code;

    private String msg;

    private  T  data;


    public static  <T> R fail(){
        return R.builder().ret(false).msg("fail").code(500).build();
    }

    public static  R error(Throwable throwable){
        return R.builder().ret(false).msg("error").code(500).data(Throwables.getStackTraceAsString(Throwables.getRootCause(throwable))).build();
    }


    public static  <T> R success(){
        return success(null);
    }

    public static  <T> R success(T data){
        return R.builder().ret(true).msg("success").code(200).data(data).build();
    }

    public Optional<T> optionalData(){
        return  Optional.ofNullable(data);
    }



}
