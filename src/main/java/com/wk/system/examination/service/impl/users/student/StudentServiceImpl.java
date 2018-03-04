package com.wk.system.examination.service.impl.users.student;

import com.wk.system.examination.entity.codes.QuestionTypeCode;
import com.wk.system.examination.entity.dao.ExamInfoMapper;
import com.wk.system.examination.entity.dao.UserMapper;
import com.wk.system.examination.service.bs.users.student.StudentServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentServiceBs {
	private final UserMapper userMapper;
	private final ExamInfoMapper examInfoMapper;

	@Autowired
	public StudentServiceImpl(UserMapper userMapper, ExamInfoMapper examInfoMapper) {
		this.userMapper = userMapper;
		this.examInfoMapper = examInfoMapper;
	}

	@Override
	public Map<String, Object> getStudentInfo(String username) {
		return userMapper.queryByUsername(username);
	}

	@Override
	public Map<String, List<Map<String, Object>>> getExamInfo(int examId) {
		Map<String, List<Map<String, Object>>> examQuestions = new HashMap<>();
		List<Map<String, Object>> choiceQuestions = examInfoMapper.getExamQuestionsByExamIdAndType(examId, QuestionTypeCode.TYPE_CHOICE.getValue());
		choiceQuestions.forEach(obj -> obj.put("answer", ""));
		examQuestions.put("choiceQuestions", choiceQuestions);
		List<Map<String, Object>> objectiveQuestions = examInfoMapper.getExamQuestionsByExamIdAndType(examId, QuestionTypeCode.TYPE_OBJECTIVE.getValue());
		objectiveQuestions.forEach(obj -> obj.put("answer", ""));
		examQuestions.put("objectiveQuestions", objectiveQuestions);
		return examQuestions;
	}
}
