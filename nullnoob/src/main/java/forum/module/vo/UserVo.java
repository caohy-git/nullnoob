package forum.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户输出对象")
@Data
public class UserVo {
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "姓名")
	private String name;
}
