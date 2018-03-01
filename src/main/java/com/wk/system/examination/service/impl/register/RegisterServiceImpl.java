package com.wk.system.examination.service.impl.register;

import com.wk.system.examination.entity.dao.UserMapper;
import com.wk.system.examination.entity.po.User;
import com.wk.system.examination.service.bs.register.RegisterServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterServiceBs {
	private final UserMapper userMapper;

	@Autowired
	public RegisterServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public User addUser(User user) throws Exception{
		userMapper.insert(user);
		System.out.println(user);
		return user;
	}
}
