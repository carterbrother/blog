package com.authine.cloudpivot.app.flyway.dto.domainevent;
        
import com.alibaba.cola.event.DomainEventI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author carter
 * create_date  2020/6/9 17:33
 * description     安装应用领域事件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("安装应用领域事件")
public class InstallAppEvent implements DomainEventI {

    @ApiModelProperty("应用code")
    private String appCode;

    @ApiModelProperty("数据库资源ID")
    private Integer resourceDatabaseId;

    @ApiModelProperty("进程ID")
    private Integer processId;

}
