package com.wk.system.examination.service.bs.domain;

import com.wk.system.examination.entity.po.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonServiceBs {
	/**
	 * 新增课程
	 * @param lesson 课程信息
	 */
	void addLesson(Lesson lesson);

	/**
	 * 删除课程
	 * @param lessonId 课程id
	 */
	void deleteLesson(int lessonId);

	/**
	 * 查询课程信息
	 * @param lesson 查询条件
	 * @return 课程信息
	 */
	List<Map<String, Object>> queryLesson(Lesson lesson);
}
