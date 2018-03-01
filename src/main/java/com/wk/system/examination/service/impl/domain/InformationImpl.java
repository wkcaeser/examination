package com.wk.system.examination.service.impl.domain;

import com.wk.system.examination.entity.dao.DepartmentMapper;
import com.wk.system.examination.entity.dao.MajorMapper;
import com.wk.system.examination.entity.po.Department;
import com.wk.system.examination.entity.po.Major;
import com.wk.system.examination.service.bs.domain.InformationBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationImpl implements InformationBs{
	private final DepartmentMapper departmentMapper;
	private final MajorMapper majorMapper;

	@Autowired
	public InformationImpl(DepartmentMapper departmentMapper, MajorMapper majorMapper) {
		this.departmentMapper = departmentMapper;
		this.majorMapper = majorMapper;
	}

	/**
	 * 获取所有有效学院信息
	 * @return list of department
	 */
	@Override
	public List<Department> getListOfAllDepartment() {
		return departmentMapper.getAll();
	}

	/**
	 * 根据学院id获取专业列表
	 * @param departmentId 学院id
	 * @return list of major
	 */
	@Override
	public List<Major> getListOfMajorByDepartmentId(long departmentId) {
		return majorMapper.getAllByDepartmentId(departmentId);
	}
}
