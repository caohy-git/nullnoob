package forum.module.service;

import com.baomidou.mybatisplus.extension.service.IService;

import forum.module.entity.User;



public interface IUserService extends IService<User>{

	User findByUsername(String username);

	User findByNameAndPwd(String username, String password);

}
