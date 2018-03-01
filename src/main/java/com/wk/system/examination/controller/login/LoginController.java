package com.wk.system.examination.controller.login;

import com.google.gson.JsonObject;
import com.wk.system.examination.entity.po.User;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.login.LoginServiceBs;
import com.wk.system.examination.service.bs.signature.TokenBs;
import com.wk.system.examination.service.bs.signature.TokenPoolBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/service")
public class LoginController {
	private final LoginServiceBs loginServiceBs;

	private final TokenPoolBs tokenPoolBs;

	private final TokenBs tokenBs;

	@Autowired
	public LoginController(LoginServiceBs loginServiceBs, TokenPoolBs tokenPoolBs, TokenBs tokenBs) {
		this.loginServiceBs = loginServiceBs;
		this.tokenPoolBs = tokenPoolBs;
		this.tokenBs = tokenBs;
	}

	@PostMapping("/loginCheck")
	public ResponseData checkLoginData(String username, String password){
		if(loginServiceBs.checkLoginData(username, password)){
			String token = tokenBs.getToken(username);
			tokenPoolBs.addToken(username, token);

			Map<String, Object> user = tokenPoolBs.getUserInfo(username);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("token", token);
			jsonObject.addProperty("username", user.get("username").toString());
			jsonObject.addProperty("level", user.get("level").toString());
			return new ResponseData.Builder().data(jsonObject.toString()).build();
		}
		return new ResponseData.Builder().buildFailureResponse();
	}
}
