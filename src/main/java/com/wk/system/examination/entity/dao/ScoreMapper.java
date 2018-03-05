package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Score;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreMapper {
	void insert(Score score);
}
