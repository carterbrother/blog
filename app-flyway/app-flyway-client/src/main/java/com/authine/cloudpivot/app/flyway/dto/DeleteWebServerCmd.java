package  com.authine.cloudpivot.app.flyway.dto;
        
import com.alibaba.cola.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author carter
 * create_date  2020/6/24 13:37
 * description     WEB服务器的记录ID
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("删除服务器指令")
public class DeleteWebServerCmd extends Command {

    @NotNull(message = "删除的记录ID不能为空")
    @Min(value = 1,message = "删除的记录ID必须为正整数")
    @ApiModelProperty("WEB服务器的记录ID")
    private Integer id;
}
