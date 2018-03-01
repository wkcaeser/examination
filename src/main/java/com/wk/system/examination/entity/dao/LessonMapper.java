package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LessonMapper {
	void insertLesson(Lesson lesson);
	List<Map<String, Object>> query(@Param("id") Integer id,
	                                @Param("name") String name,
	                                @Param("teacher_id") Integer teacher_id,
	                                @Param("department_id") Integer department_id,
	                                @Param("major_id") Integer major_id,
	                                @Param("status") Integer status
	                                );
	void updateLesson(int lessonId);
}
