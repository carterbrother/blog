package com.example.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 说明：人实体
 * @author carter
 * 创建时间： 2019年12月04日 18:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("人实体")
public class Person {
    @ApiModelProperty("姓名")
    @NotEmpty(message = "name can not empty")
    private String name;

    @ApiModelProperty("年纪")
    @Min(value = 0,message = "age should great than 0")
    @Max(value = 150,message = "age cant not beyond 150")
    private Integer age;

//    public Person() {
//    }

//    public Person(@NonNull String str) {
//
//        final String[] splitArray = str.split(":", 2);
//
//        assert splitArray.length == 2 : "字符串没有按照格式a:b给出";
//
//        setName(splitArray[0]);
//        setAge(Integer.parseInt(splitArray[1]));
//
//    }

}
