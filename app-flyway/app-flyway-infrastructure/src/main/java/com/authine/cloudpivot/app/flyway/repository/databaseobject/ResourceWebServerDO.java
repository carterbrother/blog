package com.authine.cloudpivot.app.flyway.repository.databaseobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("WEB服务器资源")
@Table(name = "resource_web_server")
public class ResourceWebServerDO {

    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("web服务器IP")
    @Column(name = "server_ip")
    private String serverIp;

    @ApiModelProperty("ssh端口")
    @Column(name = "ssh_port")
    private Integer sshPort;

    @ApiModelProperty("ssh账号")
    @Column(name = "ssh_username")
    private String sshUsername;

    @ApiModelProperty("ssh密码")
    @Column(name = "ssh_password")
    private String sshPassword;

    @ApiModelProperty("别名或者备注")
    private String memo;

    @ApiModelProperty("创建时间")
    private Date created;

    @ApiModelProperty("更新时间")
    private Date updated;


}