package forum.module.params;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserParams implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "用户名")
	private String username;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "年龄")
	private Integer age;
	@ApiModelProperty(value = "电话")
	private String phone;
	@ApiModelProperty(value = "邮箱地址")
	private String emailAddress;
	@ApiModelProperty(value = "职业")
	private String occupation;
	@ApiModelProperty(value = "国籍")
	private String nationality;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
}
