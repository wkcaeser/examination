package com.wk.system.examination.controller.user.teacher;

import com.wk.system.examination.entity.po.Lesson;
import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.domain.LessonServiceBs;
import com.wk.system.examination.service.bs.users.teacher.TeacherServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/teacher")
public class TeacherController {

	private final TeacherServiceBs teacherServiceBs;

	private final LessonServiceBs lessonServiceBs;

	@Autowired
	public TeacherController(TeacherServiceBs teacherServiceBs, LessonServiceBs lessonServiceBs) {
		this.teacherServiceBs = teacherServiceBs;
		this.lessonServiceBs = lessonServiceBs;
	}

	@GetMapping("/info/{username}")
	public ResponseData getTeacherInfo(@PathVariable("username")String username){
		System.out.println(username);
		return new ResponseData.Builder()
				.data(teacherServiceBs.getTeacherInfo(username))
				.build();
	}

	@PostMapping("/lesson/add")
	public ResponseData addLesson(Lesson lesson){
		lessonServiceBs.addLesson(lesson);
		return new ResponseData.Builder().build();
	}

	@PostMapping("/lesson/delete/{lessonId}")
	public ResponseData deleteLesson(@PathVariable("lessonId")String lessonId){
		int id;
		try {
			id = Integer.parseInt(lessonId);
		}catch (Exception e){
			return new ResponseData.Builder()
					.statusCode(ResponseCode.CODE_PARAM_ERROR).build();
		}
		lessonServiceBs.deleteLesson(id);
		return new ResponseData.Builder().build();
	}

	@GetMapping("/lessons")
	public ResponseData queryLessons(Lesson lesson){
		List<Map<String, Object>> lessons = lessonServiceBs.queryLesson(lesson);
		return new ResponseData.Builder()
				.data(lessons)
				.build();
	}
}
