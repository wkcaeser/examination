package com.wk.system.examination.controller.register;

import com.google.gson.JsonObject;
import com.wk.system.examination.entity.codes.LevelCode;
import com.wk.system.examination.entity.po.User;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.register.RegisterServiceBs;
import com.wk.system.examination.service.bs.signature.TokenBs;
import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service")
public class RegisterController {

	private final RegisterServiceBs registerServiceBs;
	private final TokenBs tokenBs;
	private final TokenPoolBs tokenPoolBs;

	@Autowired
	public RegisterController(RegisterServiceBs registerServiceBs, TokenBs tokenBs, TokenPoolBs tokenPoolBs) {
		this.registerServiceBs = registerServiceBs;
		this.tokenBs = tokenBs;
		this.tokenPoolBs = tokenPoolBs;
	}

	/**
	 * 注册 注册成功后返回token和用户名
	 * @param user 注册数据
	 * @param levelNum 注册用户类型
	 * @return token username
	 * @throws Exception sql
	 */
	@PostMapping("/register/{levelNum}")
	public ResponseData register(User user, @PathVariable("levelNum") String levelNum) throws Exception {
		//1代表学生，2代表老师
		if(LevelCode.LEVEL_STUDENT.getValueString().equals(levelNum)
				|| LevelCode.LEVEL_TEACHER.getValueString().equals(levelNum)){
			user.setLevel(Long.parseLong(levelNum));
		}else {
			return new ResponseData.Builder().buildFailureResponse();
		}
		user = registerServiceBs.addUser(user);
		String token = tokenBs.getToken(user.getUsername());

		tokenPoolBs.addToken(user.getUsername(), token);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("token", token);
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("level", user.getLevel());
		return new ResponseData.Builder().data(jsonObject.toString()).build();
	}
}
