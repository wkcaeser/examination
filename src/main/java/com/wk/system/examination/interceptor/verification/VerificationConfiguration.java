package com.wk.system.examination.interceptor.verification;

import com.wk.system.examination.entity.codes.LevelCode;
import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 权限验证
 */
@Configuration
public class VerificationConfiguration extends WebMvcConfigurerAdapter {

	private final TokenPoolBs tokenPoolBs;

	@Autowired
	public VerificationConfiguration(TokenPoolBs tokenPoolBs) {
		this.tokenPoolBs = tokenPoolBs;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new VerificationInterceptor(tokenPoolBs, LevelCode.LEVEL_TEACHER))
				.addPathPatterns("/service/teacher/**");
		registry.addInterceptor(new VerificationInterceptor(tokenPoolBs, LevelCode.LEVEL_STUDENT))
				.addPathPatterns("/service/student/**");
	}
}
