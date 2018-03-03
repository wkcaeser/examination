package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.ChoiceQuestionMapper;
import com.wk.system.examination.entity.dao.ExamInfoMapper;
import com.wk.system.examination.entity.dao.ExamMapper;
import com.wk.system.examination.entity.po.ChoiceQuestion;
import com.wk.system.examination.entity.po.ExamInfo;
import com.wk.system.examination.service.bs.domain.ChoiceQuestionServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionServiceBs {
	private final ExamMapper examMapper;

	private final ChoiceQuestionMapper choiceQuestionMapper;

	private final ExamInfoMapper examInfoMapper;

	@Autowired
	public ChoiceQuestionServiceImpl(ExamMapper examMapper, ChoiceQuestionMapper choiceQuestionMapper, ExamInfoMapper examInfoMapper) {
		this.examMapper = examMapper;
		this.choiceQuestionMapper = choiceQuestionMapper;
		this.examInfoMapper = examInfoMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void addNewChoiceQuestion(String description, String optionA, String optionB, String optionC, String optionD, String answer, int score, int exam_id) {
		Map<String, Object> examInfoMap = examMapper.selectById(exam_id);
		ChoiceQuestion question = new ChoiceQuestion();
		question.setDescription(description);
		question.setOptionb(optionB);
		question.setOptionc(optionC);
		question.setOptiona(optionA);
		question.setOptiond(optionD);
		question.setAnswer((answer.charAt(0) - 'A' + 1L));
		question.setScore(score);
		question.setDepartment_id(Long.parseLong(examInfoMap.get("department_id").toString()));
		question.setMajor_id(Long.parseLong(examInfoMap.get("major_id").toString()));
		question.setTeacher_id(Long.parseLong(examInfoMap.get("teacher_id").toString()));
		choiceQuestionMapper.insert(question);
		ExamInfo examInfo = new ExamInfo();
		examInfo.setExam_id((long) exam_id);
		examInfo.setQuestion_id(question.getId());
		examInfo.setType(1L);
		examInfoMapper.insert(examInfo);
	}
}
