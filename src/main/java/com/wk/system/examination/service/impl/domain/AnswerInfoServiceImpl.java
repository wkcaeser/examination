package com.wk.system.examination.service.impl.domain;

import com.google.gson.Gson;
import com.wk.system.examination.entity.dao.AnswerInfoMapper;
import com.wk.system.examination.entity.po.AnswerInfo;
import com.wk.system.examination.service.bs.domain.AnswerInfoServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AnswerInfoServiceImpl implements AnswerInfoServiceBs {
	private final AnswerInfoMapper answerInfoMapper;

	@Autowired
	public AnswerInfoServiceImpl(AnswerInfoMapper answerInfoMapper) {
		this.answerInfoMapper = answerInfoMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void submitAnswer(Map<String, Object> answer) {
		int examId = Integer.parseInt(answer.get("exam_id").toString());
		int userId = Integer.parseInt(answer.get("user_id").toString());
		List res = answerInfoMapper.selectByExamIdAndUserId(examId, userId);
		Gson gson = new Gson();
		String ans = gson.toJson(answer);
		if(res.size()>0){
			answerInfoMapper.update(ans, null, userId, examId);
		}else {
			AnswerInfo answerInfo = new AnswerInfo();
			answerInfo.setAnswer(ans);
			answerInfo.setExam_id((long) examId);
			answerInfo.setUser_id((long) userId);
			answerInfoMapper.insert(answerInfo);
		}
	}

	@Override
	public List<Map<String, Object>> getAnswer(int examId, int userId) {
		return answerInfoMapper.selectByExamIdAndUserId(examId, userId);
	}

	@Override
	public List<Map<String, Object>> getStudentInfosByExamId(int examId) {
		return answerInfoMapper.selectStudentInfosByExamId(examId);
	}
}
