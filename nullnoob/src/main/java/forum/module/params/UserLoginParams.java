package forum.module.params;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserLoginParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户名")
	private String username;
	@ApiModelProperty(value = "密码")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
