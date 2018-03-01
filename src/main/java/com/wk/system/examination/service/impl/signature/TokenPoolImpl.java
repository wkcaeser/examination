 package com.wk.system.examination.service.impl.signature;

import com.wk.system.examination.entity.dao.UserMapper;
import com.wk.system.examination.entity.po.User;
import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenPoolImpl implements TokenPoolBs {
	private ConcurrentHashMap<String, TokenEntity> tokenPool;
	private final UserMapper userMapper;

	@Autowired
	public TokenPoolImpl(UserMapper userMapper) {
		tokenPool = new ConcurrentHashMap<>(128);
		this.userMapper = userMapper;
	}

	/**
	 * 检查token是否合法  有效期72小时
	 * @param username 用户名
	 * @param token token
	 * @return token是否合法
	 */
	@Override
	public boolean checkToken(String username, String token) {
		if(username==null || token==null){
			return false;
		}
		TokenEntity tokenEntity = tokenPool.get(username);
		if(tokenEntity == null){
			return false;
		}
		if(!token.equals(tokenEntity.token)){
			return false;
		}
		long createdTime = tokenEntity.createdTime;
		return System.currentTimeMillis() - createdTime <= 1000 * 3600 * 72;
	}

	/**
	 * 将token创建时间  用户信息 以及token存入tokenPool
	 * @param username 用户名
	 * @param token token
	 */
	@Override
	public void addToken(String username, String token) {
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.token = token;
		tokenEntity.createdTime = System.currentTimeMillis();
		tokenEntity.userInfo = userMapper.queryByUsername(username);
		tokenPool.put(username, tokenEntity);
	}

	/**
	 * 获取登陆用户信息
	 * @param username 用户名
	 * @return User
	 */
	@Override
	public Map<String, Object> getUserInfo(String username) {
		if(username == null){
			return null;
		}
		TokenEntity tokenEntity = tokenPool.get(username);
		if(tokenEntity == null){
			return null;
		}
		return tokenEntity.userInfo;
	}

	private class TokenEntity{
		private String token;
		private Map<String,Object> userInfo;
		private long createdTime;
	}
}
