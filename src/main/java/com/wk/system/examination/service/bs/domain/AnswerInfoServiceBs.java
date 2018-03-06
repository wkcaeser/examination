package com.wk.system.examination.service.bs.domain;

import java.util.List;
import java.util.Map;

public interface AnswerInfoServiceBs {
	void submitAnswer(Map<String, Object> answer);

	List<Map<String, Object>> getAnswer(int examId, int userId);

	List<Map<String, Object>> getStudentInfosByExamId(int examId);

	void setScore(int examId, int studentId, int scoreOfObjective);

	List<Map<String, Object>> getScoresByStudentId(int studentId);
}
