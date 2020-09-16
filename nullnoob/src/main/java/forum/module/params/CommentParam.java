package forum.module.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "评论新增对象")
@Data
public class CommentParam {
	@ApiModelProperty(value = "文章id", example = "")
	private String articleId;
	@ApiModelProperty(value = "内容", example = "")
	private String content;
	
}
