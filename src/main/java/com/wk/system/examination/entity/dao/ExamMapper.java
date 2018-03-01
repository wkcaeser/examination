package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamMapper {
	void insert(Exam exam);

	void delete(@Param("id") Integer id);

	void update(Exam exam);

	List<Map<String, Object>> queryByParams(@Param("teacherId") Integer teacherId,
	                                        @Param("departmentId") Integer departmentId,
	                                        @Param("majorId") Integer majorId,
	                                        @Param("lessonId") Integer lessonId,
	                                        @Param("name") String name);
}
