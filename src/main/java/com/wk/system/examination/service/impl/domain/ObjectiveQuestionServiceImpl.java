package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.ExamInfoMapper;
import com.wk.system.examination.entity.dao.ExamMapper;
import com.wk.system.examination.entity.dao.ObjectiveQuestionMapper;
import com.wk.system.examination.entity.po.ExamInfo;
import com.wk.system.examination.entity.po.ObjectiveQuestion;
import com.wk.system.examination.service.bs.domain.ObjectiveQuestionServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ObjectiveQuestionServiceImpl implements ObjectiveQuestionServiceBs {
	private final ExamMapper examMapper;

	private final ObjectiveQuestionMapper objectiveQuestionMapper;

	private final ExamInfoMapper examInfoMapper;

	@Autowired
	public ObjectiveQuestionServiceImpl(ExamMapper examMapper, ObjectiveQuestionMapper objectiveQuestionMapper, ExamInfoMapper examInfoMapper) {
		this.examMapper = examMapper;
		this.objectiveQuestionMapper = objectiveQuestionMapper;
		this.examInfoMapper = examInfoMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void addNewObjectiveQuestion(String description, int score, int exam_id) {
		Map<String, Object> examInfoMap = examMapper.selectById(exam_id);
		ObjectiveQuestion question = new ObjectiveQuestion();
		question.setDescription(description);
		question.setScore(score);
		question.setDepartment_id(Long.parseLong(examInfoMap.get("department_id").toString()));
		question.setMajor_id(Long.parseLong(examInfoMap.get("major_id").toString()));
		question.setTeacher_id(Long.parseLong(examInfoMap.get("teacher_id").toString()));
		objectiveQuestionMapper.insert(question);
		ExamInfo examInfo = new ExamInfo();
		examInfo.setExam_id((long) exam_id);
		examInfo.setQuestion_id(question.getId());
		examInfo.setType(2L);
		examInfoMapper.insert(examInfo);
	}
}
