package com.wk.system.examination.service.impl.users.teacher;

import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import com.wk.system.examination.service.bs.users.teacher.TeacherServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherServiceBs {

	private final TokenPoolBs tokenPoolBs;

	@Autowired
	public TeacherServiceImpl(TokenPoolBs tokenPoolBs) {
		this.tokenPoolBs = tokenPoolBs;
	}

	/**
	 * 根据用户名从tokenPool获取相应用户信息
	 * @param username 用户名
	 * @return User
	 */
	@Override
	public Map<String, Object> getTeacherInfo(String username) {
		return tokenPoolBs.getUserInfo(username);
	}
}
