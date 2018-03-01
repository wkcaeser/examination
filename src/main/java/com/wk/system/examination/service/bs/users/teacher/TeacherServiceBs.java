package com.wk.system.examination.service.bs.users.teacher;


import java.util.Map;

public interface TeacherServiceBs {

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return User
	 */
	Map<String, Object> getTeacherInfo(String username);
}
