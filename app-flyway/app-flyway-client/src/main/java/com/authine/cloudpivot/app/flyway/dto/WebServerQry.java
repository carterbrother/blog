package  com.authine.cloudpivot.app.flyway.dto;

import com.alibaba.cola.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author carter
 * create_date  2020/6/22 21:54
 * description     web服务器查询条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("web服务器查询条件")
public class WebServerQry extends PageQuery {

    @ApiModelProperty("WEB服务器记录ID")
    private Integer id;

}
