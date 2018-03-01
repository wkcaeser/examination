package com.wk.system.examination.controller.domain;

import com.wk.system.examination.entity.vo.ResponseData;
import com.wk.system.examination.service.bs.domain.InformationBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service")
public class InformationController {
	private final InformationBs informationBs;

	@Autowired
	public InformationController(InformationBs informationBs) {
		this.informationBs = informationBs;
	}

	/**
	 * 获取学院列表
	 * @return list
	 */
	@GetMapping("/department")
	public String getAllDepartment(){
		return new ResponseData.Builder()
				.data(informationBs.getListOfAllDepartment())
				.build()
				.toString();
	}

	/**
	 * 通过学院id获取专业列表
	 * @param departmentId 学院id
	 * @return list
	 */
	@GetMapping("/{departmentId}/major")
	public String getMajorsByDepartmentId(@PathVariable long departmentId) throws Exception {
		return new ResponseData.Builder()
				.data(informationBs.getListOfMajorByDepartmentId(departmentId))
				.build()
				.toString();
	}
}
