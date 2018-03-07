package com.wk.system.examination.service.bs.domain;

import com.wk.system.examination.entity.codes.QuestionTypeCode;

import java.util.List;
import java.util.Map;

public interface ExamInfoServiceBs {
	List<Map<String, Object>> getExamQuestionByExamIdAndType(int examId, int type);

	void deleteExamInfo(int id);

	List<Map<String, Object>> getHistoryQuestions(Integer departmentId, Integer majorId, QuestionTypeCode type);

	void addHistoryQuestion(int examId, int questionId, int type);
}
