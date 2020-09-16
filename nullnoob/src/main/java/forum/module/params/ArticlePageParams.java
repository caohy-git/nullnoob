package forum.module.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticlePageParams {
	@ApiModelProperty(value = "标题", example = "")
	private String title;
}
