package com.wk.system.examination.exception.controller;

import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandle {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandle(Exception e){
		e.printStackTrace();
		return new ResponseData.Builder()
				.statusCode(ResponseCode.CODE_ERROR)
				.statusMsg("this is controllerAdvice")
				.build()
				.toString();
	}
}
