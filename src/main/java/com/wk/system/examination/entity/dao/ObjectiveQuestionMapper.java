package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.ObjectiveQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ObjectiveQuestionMapper {

	void insert(ObjectiveQuestion objectiveQuestion);

	ObjectiveQuestion selectById(@Param("id") Integer id);

	List<Map<String, Object>> getHistory(@Param("department_id") Integer departmentId,
	                                     @Param("major_id") Integer major_id
	);
}
