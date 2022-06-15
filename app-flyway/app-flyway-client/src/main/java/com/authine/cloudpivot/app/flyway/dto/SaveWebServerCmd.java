package  com.authine.cloudpivot.app.flyway.dto;
        
import com.alibaba.cola.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author carter
 * create_date  2020/6/24 11:54
 * description     保存webServer的指令
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("保存webServer的指令")
public class SaveWebServerCmd extends Command {

    @ApiModelProperty("id")
    private Integer id;

    @NotBlank(message = "web服务器IP不能为空")
    @Pattern(regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$",message = "请输入正确格式的IP地址")
    @ApiModelProperty("web服务器IP")
    private String serverIp;

    @NotNull(message = "ssh端口不能为空")
    @Range(min = 1,max = 65535,message = "ssh端口范围不超过65535")
    @ApiModelProperty("ssh端口")
    private Integer sshPort;

    @NotBlank(message = "ssh账号不能为空")
    @Length(min = 4,max = 30,message = "ssh账号长度在4到30")
    @ApiModelProperty("ssh账号")
    private String sshUsername;

    @NotBlank(message = "ssh密码不能为空")
    @Length(min = 6,max = 30,message = "ssh密码长度在6到30")
    @ApiModelProperty("ssh密码")
    private String sshPassword;

    @NotBlank(message = "别名或者备注不能为空")
    @Length(min = 1,max = 100,message = "别名或者备注长度在1到100")
    @ApiModelProperty("别名或者备注")
    private String memo;
}
