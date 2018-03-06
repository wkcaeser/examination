package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.ObjectiveQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ObjectiveQuestionMapper {

	void insert(ObjectiveQuestion objectiveQuestion);

	ObjectiveQuestion selectById(@Param("id") Integer id);
}
