package com.wk.system.examination.entity.vo;

import com.google.gson.Gson;

/**
 * 服务返回数据类
 * builder模式
 * 数据拷贝支持引用复制，修改赋值后的data会导致此类data同步修改
 */
public class ResponseData {
	private static Gson gson = new Gson();
	private Status status;
	private Object data;
	private ResponseData(){
		status = new Status();
	}

	public Status getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	private class Status{
		private int code;
		private String msg;

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

		@Override
		public String toString() {
			return "{" +
					"code=" + code +
					", msg='" + msg + '\'' +
					'}';
		}
	}

	public static class Builder{
		private ResponseData responseData;

		public Builder(){
			responseData = new ResponseData();
			responseData.status.code = ResponseCode.CODE_SUCCESS.value();
			responseData.status.msg = "请求成功";
		}

		public Builder statusCode(ResponseCode code){
			responseData.status.code = code.value();
			return this;
		}
		public Builder statusMsg(String msg){
			responseData.status.msg = msg;
			return this;
		}
		public Builder data(Object data){
			responseData.data = data;
			return this;
		}

		public ResponseData build() {
			return responseData;
		}
		public ResponseData buildFailureResponse(){
			if (responseData == null) {
				responseData = new ResponseData();
			}
			responseData.status.code = ResponseCode.CODE_ERROR.value();
			responseData.status.msg = "请求异常";
			return responseData;
		}
	}

	@Override
	public String toString() {
		return gson.toJson(this);
	}
}
