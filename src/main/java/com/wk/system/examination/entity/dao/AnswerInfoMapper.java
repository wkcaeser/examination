package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.AnswerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerInfoMapper {
	void insert(AnswerInfo answerInfo);

	void update(@Param("answer") String answer, @Param("status") Integer status,
	            @Param("user_id") Integer userId, @Param("exam_id")Integer examId
	            );
}
