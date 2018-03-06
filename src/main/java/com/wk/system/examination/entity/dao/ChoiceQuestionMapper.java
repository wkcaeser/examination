package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.ChoiceQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChoiceQuestionMapper {
	void insert(ChoiceQuestion choiceQuestion);

	void delete(@Param("id") Integer id);

	ChoiceQuestion selectById(@Param("id") Integer id);
}
