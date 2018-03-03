package com.wk.system.examination.service.bs.domain;

import java.util.List;
import java.util.Map;

public interface ExamInfoServiceBs {
	List<Map<String, Object>> getExamQuestionByExamIdAndType(int examId, int type);

	void deleteExamInfo(int id);
}
