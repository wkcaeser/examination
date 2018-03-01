package com.wk.system.examination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableTransactionManagement
//@MapperScan("com.wk.system.examination.entity.dao")
public class ExaminationSpringBootApplication {
	public static void main(String[] args){
		SpringApplication.run(ExaminationSpringBootApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(9999);
		factory.setSessionTimeout(30, TimeUnit.MINUTES);
		return factory;
	}
}
