package com.wk.system.examination.service.bs.users.student;

import java.util.List;
import java.util.Map;

public interface StudentServiceBs {
	Map<String, Object> getStudentInfo(String username);

	Map<String, List<Map<String, Object>>> getExamInfo(int examId);
}
