package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.ExamMapper;
import com.wk.system.examination.entity.po.Exam;
import com.wk.system.examination.service.bs.domain.ExamServiceBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 新增exam
	 * @param exam 考试信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void addExam(Exam exam) {
		examMapper.insert(exam);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void deleteExam(int examId) {
		examMapper.delete(examId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updateExam(Exam exam) {
		examMapper.update(exam);
	}
}
