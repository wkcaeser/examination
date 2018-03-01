package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.ExamMapper;
import com.wk.system.examination.service.bs.domain.ExamServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamServiceBs {
	private final ExamMapper examMapper;

	@Autowired
	public ExamServiceImpl(ExamMapper examMapper) {
		this.examMapper = examMapper;
	}

	/**
	 * 多条件查询考试信息
	 * @param teacherId 教师id
	 * @param departmentId 学院id
	 * @param majorId 专业id
	 * @param lessonId 课程id
	 * @param name 考试名称
	 * @return examList
	 */
	@Override
	public List<Map<String, Object>> getExamList(Integer teacherId, Integer departmentId, Integer majorId, Integer lessonId, String name) {
		return examMapper.queryByParams(teacherId, departmentId, majorId, lessonId, name);
	}
}
