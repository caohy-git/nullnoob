package forum.module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import forum.module.entity.User;
import forum.module.mapper.IUserMapper;
import forum.module.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService{

	@Autowired
	private IUserMapper userMapper;
	
	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public User findByNameAndPwd(String username, String password) {
		return userMapper.findByNameAndPwd(username,password);
	}

}
