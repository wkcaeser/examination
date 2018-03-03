package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.ExamInfoMapper;
import com.wk.system.examination.service.bs.domain.ExamInfoServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ExamInfoServiceImpl implements ExamInfoServiceBs {
	private final ExamInfoMapper examInfoMapper;

	@Autowired
	public ExamInfoServiceImpl(ExamInfoMapper examInfoMapper) {
		this.examInfoMapper = examInfoMapper;
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
}
