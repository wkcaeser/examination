package com.wk.system.examination.controller.user.teacher;

import com.wk.system.examination.entity.po.Exam;
import com.wk.system.examination.entity.po.Lesson;
import com.wk.system.examination.entity.vo.ResponseCode;
import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.domain.*;
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

	private final ExamServiceBs examServiceBs;

	private final ObjectiveQuestionServiceBs objectiveQuestionServiceBs;

	private final ChoiceQuestionServiceBs choiceQuestionServiceBs;

	private final ExamInfoServiceBs examInfoServiceBs;

	@Autowired
	public TeacherController(TeacherServiceBs teacherServiceBs, LessonServiceBs lessonServiceBs, ExamServiceBs examServiceBs, ObjectiveQuestionServiceBs objectiveQuestionServiceBs, ChoiceQuestionServiceBs choiceQuestionServiceBs, ExamInfoServiceBs examInfoServiceBs) {
		this.teacherServiceBs = teacherServiceBs;
		this.lessonServiceBs = lessonServiceBs;
		this.examServiceBs = examServiceBs;
		this.objectiveQuestionServiceBs = objectiveQuestionServiceBs;
		this.choiceQuestionServiceBs = choiceQuestionServiceBs;
		this.examInfoServiceBs = examInfoServiceBs;
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

	@GetMapping("/exams")
	public ResponseData queryExams(@RequestParam(value = "teacher_id", required = false) Integer teacherId,
	                               @RequestParam(value = "department_id", required = false) Integer departmentId,
	                               @RequestParam(value = "major_id", required = false) Integer majorId,
	                               @RequestParam(value = "lesson_id", required = false) Integer lessonId,
	                               @RequestParam(value = "name", required = false) String name){
		List res = examServiceBs.getExamList(teacherId, departmentId, majorId, lessonId, name);
		return new ResponseData.Builder()
				.data(res)
				.build();
	}

	@PostMapping("/exam/add")
	public ResponseData addExam(Exam exam){
		examServiceBs.addExam(exam);
		return new ResponseData.Builder().build();
	}

	@PostMapping("/exam/delete/{examId}")
	public ResponseData deleteExam(@PathVariable("examId") Integer examId){
		boolean examIsStart = examServiceBs.checkExamIsStart(examId);
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(examIsStart){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		examServiceBs.deleteExam(examId);
		return new ResponseData.Builder().build();
	}

	@PostMapping("/exam/update")
	public ResponseData updateExam(Exam exam){
		boolean examIsStart = examServiceBs.checkExamIsStart(Integer.parseInt(exam.getId().toString()));
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(examIsStart){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		examServiceBs.updateExam(exam);
		return new ResponseData.Builder().build();
	}

	@PostMapping("/question/choice/add")
	public ResponseData addNewChoiceQuestion(String description, String optionA, String optionB, String optionC, String optionD, String answer, int score, int exam_id){
		boolean examIsStart = examServiceBs.checkExamIsStart(exam_id);
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(examIsStart){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		choiceQuestionServiceBs.addNewChoiceQuestion(description, optionA, optionB, optionC, optionD, answer, score, exam_id);
		return new ResponseData.Builder().build();
	}

	@PostMapping("/question/objective/add")
	public ResponseData addNewChoiceQuestion(String description, int score, int exam_id){
		boolean examIsStart = examServiceBs.checkExamIsStart(exam_id);
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(examIsStart){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		objectiveQuestionServiceBs.addNewObjectiveQuestion(description, score, exam_id);
		return new ResponseData.Builder().build();
	}

	@GetMapping("/question/{examId}/{type}")
	public ResponseData getExamQuestionsByIdAndType(@PathVariable("examId") int examId, @PathVariable("type") int type){
		List<Map<String, Object>> res = examInfoServiceBs.getExamQuestionByExamIdAndType(examId, type);
		return new ResponseData.Builder()
				.data(res)
				.build();
	}

	@PostMapping("/question/delete/{examId}/{id}")
	public ResponseData deleteExamQuestion(@PathVariable("examId") int examId, @PathVariable("id") int id){
		boolean examIsStart = examServiceBs.checkExamIsStart(examId);
		ResponseData.Builder responseBuilder = new ResponseData.Builder();
		if(examIsStart){
			return responseBuilder.statusCode(ResponseCode.CODE_EXAM_TIME_ERROR).build();
		}
		examInfoServiceBs.deleteExamInfo(id);
		return new ResponseData.Builder().build();
	}
}
