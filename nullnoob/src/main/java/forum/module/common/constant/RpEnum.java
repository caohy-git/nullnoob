package forum.module.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description 状态信息
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum RpEnum {

	SUCCESS(0, "成功"), 
	ERROR_SYSTEM(-1, "系统繁忙,请稍后再试"), 
	ERROR_NOFOUND(-1, "无法找到相应的数据"), 
	ERROR_PARAMETER(-1, "参数错误"),
	NO_AUTHEN(-2, "请先登录"),
	USERNAME_OR_PASSWORD_ERROR(-2, "用户名或密码错误"),
	NO_PERMIT(-2, "登录已过期，请重新登录"),
	NO_PARAMETER(-2,"缺少必要参数"),
	NO_AUTHOR(-4, "没有权限");

	public Integer code;

	public String msg;
}
