package com.wk.system.examination.service.impl.users.student;

import com.wk.system.examination.entity.dao.UserMapper;
import com.wk.system.examination.service.bs.users.student.StudentServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentServiceBs {
	private final UserMapper userMapper;

	@Autowired
	public StudentServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public Map<String, Object> getStudentInfo(String username) {
		return userMapper.queryByUsername(username);
	}
}
