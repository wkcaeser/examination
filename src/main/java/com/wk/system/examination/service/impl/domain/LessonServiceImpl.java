package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.LessonMapper;
import com.wk.system.examination.entity.po.Lesson;
import com.wk.system.examination.service.bs.domain.LessonServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LessonServiceImpl implements LessonServiceBs {
	private final LessonMapper lessonMapper;

	@Autowired
	public LessonServiceImpl(LessonMapper lessonMapper) {
		this.lessonMapper = lessonMapper;
	}

	/**
	 * 添加课程
	 * @param lesson 课程信息
	 */
	@Override
	public void addLesson(Lesson lesson) {
		lessonMapper.insertLesson(lesson);
	}

	/**
	 * 软删除课程
	 * @param lessonId 课程id
	 */
	@Override
	public void deleteLesson(int lessonId) {
		lessonMapper.updateLesson(lessonId);
	}

	/**
	 * 查询课程信息
	 * @param lesson 查询条件
	 * @return lessons
	 */
	@Override
	public List<Map<String, Object>> queryLesson(Lesson lesson) {
		return lessonMapper.query(
				lesson.getId(), lesson.getName(),
				lesson.getTeacher_id(), lesson.getDepartment_id(),
				lesson.getMajor_id(), lesson.getStatus()
		);
	}
}
