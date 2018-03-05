package com.wk.system.examination.service.bs.domain;


import com.wk.system.examination.entity.po.Exam;

import java.util.List;
import java.util.Map;

public interface ExamServiceBs {
	/**
	 * 根据多条件查询考试信息
	 * @param teacherId 教师id
	 * @param departmentId 学院id
	 * @param majorId 专业id
	 * @param lessonId 课程id
	 * @param name 考试名称
	 * @return examList
	 */
	List<Map<String, Object>> getExamList(Integer teacherId,
	                                      Integer departmentId,
	                                      Integer majorId,
	                                      Integer lessonId,
	                                      String name
	                                      );

	/**
	 * 添加exam
	 * @param exam 考试信息
	 */
	void addExam(Exam exam);

	/**
	 * 删除exam
	 * @param examId exam id
	 */
	void deleteExam(int examId);

	/**
	 * 更新exam信息
	 * @param exam 考试信息
	 */
	void updateExam(Exam exam);

	boolean checkTimeOfDoExam(int examId);

	boolean checkExamIsStart(int examId);

	boolean checkExamIsEnd(int examId);
}
