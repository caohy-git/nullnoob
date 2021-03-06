package forum.module.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import forum.module.entity.User;

@Mapper
public interface IUserMapper extends BaseMapper<User>{

	User findByUsername(@Param("username") String username);

	User findByNameAndPwd(@Param("username")String username, @Param("password")String password);

}
