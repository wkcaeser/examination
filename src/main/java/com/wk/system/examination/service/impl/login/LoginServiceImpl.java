package com.wk.system.examination.service.impl.login;

import com.wk.system.examination.entity.dao.UserMapper;
import com.wk.system.examination.service.bs.login.LoginServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginServiceBs {
	private final UserMapper userMapper;

	@Autowired
	public LoginServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 *  检查用户名和密码是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return Boolean
	 */
	@Override
	public boolean checkLoginData(String username, String password) {
		int count = userMapper.checkUsernameAndPassword(username, password);
		return count == 1;
	}
}
