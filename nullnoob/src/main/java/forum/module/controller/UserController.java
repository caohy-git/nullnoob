package forum.module.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import forum.module.common.constant.Rp;
import forum.module.common.constant.RpEnum;
import forum.module.common.exception.BizException;
import forum.module.common.util.StringUtil;
import forum.module.entity.User;
import forum.module.params.UserLoginParams;
import forum.module.params.UserParams;
import forum.module.service.IUserService;
import forum.module.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("/user")
@CrossOrigin
@Api(value = "用户管理",tags = "用户管理")
public class UserController {
	@Autowired
	private IUserService userService;
	
	
	//跳转注册页面
	@RequestMapping("/getReg")
	public ModelAndView getReg() {
		return new ModelAndView("user/registered.html");
	}
	//跳转登陆首页
	@RequestMapping("/loginView")
	public ModelAndView loginView() {
		return new ModelAndView("login.html");
	}
	
	//跳转注册成功页面
	@RequestMapping("/regSuccess")
	public ModelAndView regSuccess() {
		return new ModelAndView("user/regSuccess.html");
	}
	
	@GetMapping("/getUser")
	@ApiOperation("获取用户")
	@ResponseBody
	public Rp<User> getUser(String id) {
		if(StringUtil.isEamil(id)) {
			throw new BizException("缺少必要参数");
		}
		User user = userService.getById(id);
		return Rp.buildSuccess(user);
	}
	
	@PostMapping("/add")
	@ApiOperation("注册")
	@ResponseBody
	public Rp<String> add(HttpServletRequest request,UserParams userParams) {
		
		if(userParams == null) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		if(StringUtils.isEmpty(userParams.getUsername())) {
			throw new BizException("用户名不能为空");
		}
		if(userParams.getUsername().length() < 6) {
			throw new BizException("输入用户名不能小于6个字符");
		}
		
		if(StringUtils.isEmpty(userParams.getPassword())) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		if(userParams.getPassword().length() < 6) {
			throw new BizException("输入密码不能小于6个字符");
		}
		
		if(StringUtils.isEmpty(userParams.getName()) || 
				StringUtils.isEmpty(userParams.getPhone()) ||
				StringUtils.isEmpty(userParams.getEmailAddress()) ||
				StringUtils.isEmpty(userParams.getOccupation())){
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		
		
		if(userParams.getPhone().length()>13) {
			throw new BizException("电话格式异常");
		}
		if(!StringUtil.isEamil(userParams.getEmailAddress())) {
			throw new BizException("邮箱地址格式异常");
		}
		
		//判断数据库中是否存在已有的数据
		User oldUser = userService.findByUsername(userParams.getUsername());
		if(oldUser != null) {
			throw new BizException("当前用户名已被注册");
		}
		User user = new User();
		BeanUtils.copyProperties(userParams, user);
		user.setId(StringUtil.generateUUID());
		//处理用户密码
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		user.setRoleStatus(1);//1表示注册用户
		user.setCreateUser("123");
		user.setCreateTime(new Date());
		
		userService.save(user);
		return Rp.buildSuccess();
	}
	
	@PostMapping("/login")
	@ApiOperation("登陆")
	@ResponseBody
	public Rp<String> login(HttpServletRequest request,UserLoginParams user){
		if(user == null) {
			throw new BizException("缺少必要参数");
		}
		if(StringUtil.isEmpty(user.getUsername())) {
			throw new BizException("输入账号为空");
		}
		if(StringUtil.isEmpty(user.getPassword())) {
			throw new BizException("输入密码为空");
		}
		
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		User oldUser = userService.findByNameAndPwd(user.getUsername(),password);
		if(oldUser == null) {
			throw new BizException(RpEnum.USERNAME_OR_PASSWORD_ERROR);
		}
		request.getSession().setAttribute("USER", oldUser);
		return Rp.buildSuccess();
	}
	
	@PostMapping("/edit")
	@ApiOperation("修改用户")
	public Rp<String> edit(HttpServletRequest request,UserParams userParam){
		if(userParam == null) {
			throw new BizException("缺少必要参数");
		}
		if(StringUtil.isEmpty(userParam.getId())) {
			throw new BizException("缺少必要参数");
		}
		if(StringUtils.isEmpty(userParam.getUsername())) {
			throw new BizException("用户名不能为空");
		}
		if(StringUtils.isEmpty(userParam.getPassword())) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		if(userParam.getPassword().length() < 6) {
			throw new BizException("输入密码必须大于6位");
		}
		
		//TODO 判断不能为空的字段
		if(StringUtils.isEmpty(userParam.getName()) || 
				StringUtils.isEmpty(userParam.getPhone()) ||
				StringUtils.isEmpty(userParam.getEmailAddress()) ||
				StringUtils.isEmpty(userParam.getOccupation())){
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		
		
		if(userParam.getPhone().length()>13) {
			throw new BizException("电话格式异常");
		}
		if(!StringUtil.isEamil(userParam.getEmailAddress())) {
			throw new BizException("邮箱地址格式异常");
		}
		
		User user = new User();
		BeanUtils.copyProperties(userParam, user);
		user.setPassword(DigestUtils.md5DigestAsHex(userParam.getPassword().getBytes()));
		
		userService.updateById(user);
		return Rp.buildSuccess();
		
	}
	
	//跳转登陆首页
		@RequestMapping("/index")
		public ModelAndView index(HttpServletRequest request) {
			ModelMap map = new ModelMap();
			User user = (User)request.getSession().getAttribute("USER");
			UserVo userVo = new UserVo();
			if(user != null) {
				BeanUtils.copyProperties(user, userVo);
				map.put("user", userVo);
			}
			return new ModelAndView("index.html", map);
		}
	
}
