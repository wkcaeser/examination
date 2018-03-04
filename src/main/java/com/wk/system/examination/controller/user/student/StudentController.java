package com.wk.system.examination.controller.user.student;

import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.domain.ExamServiceBs;
import com.wk.system.examination.service.bs.users.student.StudentServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/student")
public class StudentController {
	private final StudentServiceBs studentServiceBs;

	private final ExamServiceBs examServiceBs;
	@Autowired
	public StudentController(StudentServiceBs studentServiceBs, ExamServiceBs examServiceBs) {
		this.studentServiceBs = studentServiceBs;
		this.examServiceBs = examServiceBs;
	}

	@GetMapping("/info/{username}")
	public ResponseData getStudentInfo(@PathVariable("username") String username){
		Map<String, Object> info = studentServiceBs.getStudentInfo(username);
		return new ResponseData.Builder().data(info).build();
	}

	@GetMapping("/exams")
	public ResponseData getExamList(@RequestParam(value = "teacher_id", required = false) Integer teacherId,
	                                @RequestParam(value = "department_id", required = false) Integer departmentId,
	                                @RequestParam(value = "major_id", required = false) Integer majorId,
	                                @RequestParam(value = "name", required = false) String name){
		List res = examServiceBs.getExamList(teacherId, departmentId, majorId, null, name);
		return new ResponseData.Builder()
				.data(res)
				.build();
	}

	@GetMapping("/exam/do/getQuestionList/{examId}")
	public ResponseData getExamQuestionList(@PathVariable("examId") Integer examId){
		boolean timeIsCorrect = examServiceBs.checkTimeOfDoExam(examId);
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(!timeIsCorrect){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		Map examQuestions = studentServiceBs.getExamInfo(examId);
		return responseBuilder.data(examQuestions).build();
	}
}
