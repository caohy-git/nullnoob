package module.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import module.common.constant.Rp;
import module.common.constant.RpEnum;
import module.common.exception.BizException;
import module.entity.User;
import module.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "用户管理",tags = "用户管理")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("getUser")
	@ApiOperation("获取用户")
	public User getUser(String id) {
		User user = userService.getById(id);
		return user;
	}
	
	@PostMapping("新增")
	public Rp<String> add(HttpServletRequest request,User user) {
		if(user == null) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new BizException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		
		//TODO 判断不能为空的字段
		if(StringUtils.isEmpty(user.getName()) || 
				StringUtils.isEmpty(user.getPhone()) ||
				StringUtils.isEmpty(user.getEmailAddress()) ||
				StringUtils.isEmpty(user.getOccupation())){
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		
		
		if(user.getPhone().length()>13) {
			throw new BizException("电话格式异常");
		}
		
		//判断数据库中是否存在已有的数据
		User oldUser = userService.findByUsername(user.getUsername());
		if(oldUser != null) {
			throw new BizException("当前用户名已被注册");
		}
		//处理用户密码
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		user.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		user.setRoleStatus(1);//1表示注册用户
		user.setCreateUser("123");
		user.setCreateTime(new Date());
		
		//C - Ser - M 
		//1.数据非空判断 2.数据格式逻辑校验 3.添加系统自带的逻辑数据 4.保存
		userService.save(user);
		return Rp.buildSuccess();
	}
	
	
}
