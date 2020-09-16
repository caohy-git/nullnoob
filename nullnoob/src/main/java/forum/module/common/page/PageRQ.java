package forum.module.common.page;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain=true)
@ApiModel("分页请求对象")
public class PageRQ implements Serializable {
private static final long serialVersionUID = 8837921660980549815L;
	
	@Range(min = 1, max = 50, message = "每页显示条数不合法")
    @ApiModelProperty(value = "每页显示条数，默认是10", example = "2")
    private long size = 10;
    
	@NotNull(message = "当前页不能为空")
	@ApiModelProperty(value = "当前页", required = true, example = "1")
    private long current;
	
	@ApiModelProperty(value = "asc/desc，例：asc.DB字段名1,2,...", required = false)
	private String sortRule;
}
