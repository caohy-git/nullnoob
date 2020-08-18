package module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import module.entity.User;
import module.mapper.IUserMapper;
import module.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService{

	@Autowired
	private IUserMapper userMapper;
	
	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
