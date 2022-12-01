package com.spring.story.demo.cache.pre.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ApiModel("用户")
@Table(name = "user")
@Entity
public class UserEntity {

    @Id
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("微信用户id")
    private String wxUserId;

    @ApiModelProperty("真实名称")
    private String name;

    @ApiModelProperty("职位")
    private String position;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("UserStatusEnum")
    private Integer status;

    @ApiModelProperty("创建者ID")
    private String cUser;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("删除ID")
    private String dUser;

    @ApiModelProperty("删除时间")
    private Date dTime;

    @ApiModelProperty("删除标记 1为删除")
    private Integer del = 0;

}
