package com.wk.system.examination.interceptor.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 权限验证
 */
@Configuration
public class VerificationConfiguration extends WebMvcConfigurerAdapter {

	private final TeacherVerificationInterceptor teacherVerificationInterceptor;

	@Autowired
	public VerificationConfiguration(TeacherVerificationInterceptor teacherVerificationInterceptor) {
		this.teacherVerificationInterceptor = teacherVerificationInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(teacherVerificationInterceptor)
				.addPathPatterns("/service/teacher/**");
	}
}
