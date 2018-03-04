package com.wk.system.examination.entity.vo;

public enum ResponseCode {
	//成功状态码
	CODE_SUCCESS(200),
	//服务器异常状态码
	CODE_ERROR(500),
	//权限错误
	CODE_LOGIN_TIMEOUT(555),
	//参数错误
	CODE_PARAM_ERROR(666),
	//考试时间不对
	CODE_EXAM_TIME_ERROR(777),
	//卖个萌
	CODE_WTF(0);
	private int value = 0;
	ResponseCode(int value) {
		this.value = value;
	}
	public int value(){
		return this.value;
	}
}
