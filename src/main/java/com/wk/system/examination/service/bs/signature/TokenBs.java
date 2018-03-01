package com.wk.system.examination.service.bs.signature;

/**
 * 生成token及相关操作
 */
public interface TokenBs {
	/**
	 * 根据用户名生成token
	 * @param username 用户名
	 * @return token
	 */
	String getToken(String username);
}
