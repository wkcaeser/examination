package com.wk.system.examination.service.bs.signature;


import com.wk.system.examination.entity.po.User;

import java.util.Map;

public interface TokenPoolBs {
	/**
	 * 验证token
	 * @param username 用户名
	 * @param token token
	 * @return 是否合法
	 */
	boolean checkToken(String username, String token);

	/**
	 * 新增token
	 * @param username 用户名
	 * @param token token
	 */
	void addToken(String username, String token);

	/**
	 * 获取登陆用户信息
	 * @param username 用户名
	 * @return User
	 */
	Map<String, Object> getUserInfo(String username);
}
