package com.wk.system.examination.controller;

import com.google.gson.JsonObject;
import com.wk.system.examination.entity.dao.DepartmentMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class TestController {

	private final DepartmentMapper departmentMapper;

	@Autowired
	public TestController(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}

	@GetMapping(value = "/test")
	public String testController(){
		System.out.println("get request");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", 1);
		List list = departmentMapper.getAll();
		System.out.println(list.size());
		return jsonObject.toString();
	}

	@PostMapping(value = "/login")
	public String testLogin(String username,String password){
		System.out.println(username + " : " + password);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", 1);
		return jsonObject.toString();
	}

}
