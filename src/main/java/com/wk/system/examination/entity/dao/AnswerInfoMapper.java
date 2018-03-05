package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.AnswerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerInfoMapper {
	List<Map<String, Object>> selectByExamIdAndUserId(@Param("examId")int examId, @Param("userId")int userId);
	void insert(AnswerInfo answerInfo);

	void update(@Param("answer") String answer, @Param("status") Integer status,
	            @Param("user_id") Integer userId, @Param("exam_id")Integer examId
	            );

	List<Map<String, Object>> selectStudentInfosByExamId(@Param("examId") int examId);
}
