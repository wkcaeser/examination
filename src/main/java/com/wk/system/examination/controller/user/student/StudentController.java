package com.wk.system.examination.controller.user.student;

import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.users.student.StudentServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/service/student")
public class StudentController {
	private final StudentServiceBs studentServiceBs;

	@Autowired
	public StudentController(StudentServiceBs studentServiceBs) {
		this.studentServiceBs = studentServiceBs;
	}

	@GetMapping("/info/{username}")
	public ResponseData getStudentInfo(@PathVariable("username") String username){
		Map<String, Object> info = studentServiceBs.getStudentInfo(username);
		return new ResponseData.Builder().data(info).build();
	}
}
