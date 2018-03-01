package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
	int insert(User user);
	Map<String, Object> queryByUsername(@Param("username")String username);
	int checkUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
