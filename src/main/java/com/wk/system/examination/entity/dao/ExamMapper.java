package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamMapper {
	void insert(Exam exam);

	void delete(@Param("id") Integer id);

	void update(Exam exam);
}
