package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.codes.QuestionTypeCode;
import com.wk.system.examination.entity.dao.ChoiceQuestionMapper;
import com.wk.system.examination.entity.dao.ExamInfoMapper;
import com.wk.system.examination.entity.dao.ObjectiveQuestionMapper;
import com.wk.system.examination.entity.po.ExamInfo;
import com.wk.system.examination.service.bs.domain.ExamInfoServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExamInfoServiceImpl implements ExamInfoServiceBs {
	private final ExamInfoMapper examInfoMapper;
	private final ChoiceQuestionMapper choiceQuestionMapper;
	private final ObjectiveQuestionMapper objectiveQuestionMapper;

	@Autowired
	public ExamInfoServiceImpl(ExamInfoMapper examInfoMapper, ChoiceQuestionMapper choiceQuestionMapper, ObjectiveQuestionMapper objectiveQuestionMapper) {
		this.examInfoMapper = examInfoMapper;
		this.choiceQuestionMapper = choiceQuestionMapper;
		this.objectiveQuestionMapper = objectiveQuestionMapper;
	}

	@Override
	public List<Map<String, Object>> getExamQuestionByExamIdAndType(int examId, int type) {
		return examInfoMapper.getExamQuestionsByExamIdAndType(examId, type);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void deleteExamInfo(int id) {
		examInfoMapper.delete(id);
	}

	@Override
	public List<Map<String, Object>> getHistoryQuestions(Integer departmentId, Integer majorId, QuestionTypeCode type) {
		if(type == QuestionTypeCode.TYPE_CHOICE){
			return choiceQuestionMapper.getHistory(departmentId, majorId);
		}
		if(type == QuestionTypeCode.TYPE_OBJECTIVE){
			return objectiveQuestionMapper.getHistory(departmentId, majorId);
		}
		return new ArrayList<>();
	}

	@Override
	public void addHistoryQuestion(int examId, int questionId, int type) {
		ExamInfo examInfo = new ExamInfo();
		examInfo.setType((long) type);
		examInfo.setQuestion_id((long) questionId);
		examInfo.setExam_id((long) examId);
		examInfoMapper.insert(examInfo);
	}
}
