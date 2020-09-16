package forum.module.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "文章列表输出对象")
@Data
public class ArticlePageVo {
	@ApiModelProperty(value = "主键id", example ="")
	private String id;
	@ApiModelProperty(value = "标题", example ="")
	private String title;
	@ApiModelProperty(value = "主题", example = "")
	private String theme;
	@ApiModelProperty(value = "点赞数量", example = "")
	private Integer thumbUpNum;
	@ApiModelProperty(value = "发布时间", example = "")
	private Date publishTime;
	@ApiModelProperty(value = "创建人", example = "")
	private String createUser;
	@ApiModelProperty(value = "评论数量", example ="")
	private Integer commentNum;
}
