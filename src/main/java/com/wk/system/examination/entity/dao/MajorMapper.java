package com.wk.system.examination.entity.dao;

import com.wk.system.examination.entity.po.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MajorMapper {
	List<Major> getAllByDepartmentId(@Param("departmentId") long id);
}
