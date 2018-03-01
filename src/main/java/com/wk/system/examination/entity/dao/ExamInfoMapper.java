package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.ExamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamInfoMapper {
	void insert(ExamInfo examInfo);

	void delete(@Param("id") Integer id);
}
