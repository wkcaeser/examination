package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScoreMapper {
	void insert(Score score);

	void update(Score score);

	List<Map<String, Object>> selectByUserIdAndExamId(@Param("examId")int examId, @Param("userId")int userId);
}
