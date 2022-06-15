package com.authine.cloudpivot.app.flyway.dto.clientobject;
        
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author carter
 * create_date  2020/6/22 21:55
 * description     web服务器信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("web服务器信息")
public class WebServerDto {


    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("web服务器IP")
    private String serverIp;

    @ApiModelProperty("ssh端口")
    private Integer sshPort;

    @ApiModelProperty("ssh账号")
    private String sshUsername;

    @ApiModelProperty("ssh密码")
    private String sshPassword;

    @ApiModelProperty("别名或者备注")
    private String memo;

}
