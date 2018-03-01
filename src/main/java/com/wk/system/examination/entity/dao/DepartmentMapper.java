package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
	List<Department> getAll();
}
