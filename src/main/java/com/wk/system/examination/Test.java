package com.wk.system.examination;

import com.google.gson.Gson;
import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {
	@org.junit.Test
	public void reponseDataTest(){
		System.out.println(ResponseCode.CODE_SUCCESS.value());
		System.out.println(ResponseCode.CODE_ERROR.value());
		System.out.println(ResponseCode.valueOf("CODE_ERROR").value());

		ResponseData.Builder builder = new ResponseData.Builder();
		builder.statusCode(ResponseCode.CODE_WTF).statusMsg("xxxxxx").data(Arrays.asList(1,2,3));
		Gson gson = new Gson();
		System.out.println(gson.toJson(builder.build()));
		ResponseData responseData = gson.fromJson("{\"status\":{\"code\":0,\"msg\":\"xxxxxx\"},\"data\":[1,2,3]}", ResponseData.class);
		System.out.println(responseData);
		ArrayList<Integer> list = (ArrayList<Integer>) responseData.getData();
		System.out.println(list.get(0));
	}

	@org.junit.Test
	public void testTime(){
		Instant instant = new Date("2018-03-02T15:00").toInstant();
		System.out.println(instant.toString());
	}
}
