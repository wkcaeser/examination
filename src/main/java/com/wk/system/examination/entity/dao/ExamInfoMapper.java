package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.ExamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamInfoMapper {
	void insert(ExamInfo examInfo);

	void delete(@Param("id") Integer id);

	List<Map<String, Object>> getExamQuestionsByExamIdAndType(@Param("examId") int examId, @Param("type") int type);
}
