package module.service;

import com.baomidou.mybatisplus.extension.service.IService;

import module.entity.User;


public interface IUserService extends IService<User>{

	User findByUsername(String username);

}
