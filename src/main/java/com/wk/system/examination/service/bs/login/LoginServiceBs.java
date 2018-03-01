package com.wk.system.examination.service.bs.login;

public interface LoginServiceBs {
	/**
	 * 检查用户名和密码是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return true/false
	 */
	boolean checkLoginData(String username, String password);
}
