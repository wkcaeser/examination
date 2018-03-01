package com.wk.system.examination.service.bs.domain;

import com.wk.system.examination.entity.po.Department;
import com.wk.system.examination.entity.po.Major;

import java.util.List;

public interface InformationBs {
	/**
	 * 获取所有有效学院信息
	 * @return list of department
	 */
	List<Department> getListOfAllDepartment();

	/**
	 * 根据学院id获取专业列表
	 * @param departmentId 学院id
	 * @return list of major
	 */
	List<Major> getListOfMajorByDepartmentId(long departmentId);
}
