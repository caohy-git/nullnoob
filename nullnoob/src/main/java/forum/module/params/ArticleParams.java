package forum.module.params;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class ArticleParams implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "主题")
	private String theme;
	@ApiModelProperty(value = "类型")
	private String type;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "文章状态 0:草稿 1:发布")
	private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
