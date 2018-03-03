package com.wk.system.examination.interceptor.verification;

import com.wk.system.examination.entity.codes.LevelCode;
import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 教师操作权限检查
 */
public class VerificationInterceptor implements HandlerInterceptor {
	private TokenPoolBs tokenPoolBs;
	private LevelCode levelCode;

	public VerificationInterceptor(TokenPoolBs tokenPoolBs, LevelCode levelCode) {
		this.tokenPoolBs = tokenPoolBs;
		this.levelCode = levelCode;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Cookie[] cookies = request.getCookies();
		ResponseData responseData = new ResponseData.Builder()
				.statusCode(ResponseCode.CODE_LOGIN_TIMEOUT)
				.build();
		if(cookies == null){
			response.getWriter().write(responseData.toString());
			return false;
		}
		String username = null;
		String token = null;
		//获取cookie中username、 token
		for(Cookie cookie : cookies){
			if("username".equals(cookie.getName())){
				username = cookie.getValue();
			}
			if("token".equals(cookie.getName())){
				token = cookie.getValue();
			}
		}
		if(username==null || token==null){
			response.getWriter().write(responseData.toString());
			return false;
		}
		//验证username和token的正确性及权限
		if (tokenPoolBs.checkToken(username, token)){
			Map<String, Object> user = tokenPoolBs.getUserInfo(username);
			if(Integer.parseInt(user.get("level").toString()) == levelCode.getValue()){
				return true;
			}
		}
		response.getWriter().write(responseData.toString());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
