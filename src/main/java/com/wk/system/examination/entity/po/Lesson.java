package com.wk.system.examination.entity.po;


import java.sql.Timestamp;

public class Lesson {

  private Integer id;
  private String name;
  private Integer teacher_id;
  private Integer department_id;
  private Integer major_id;
  private Integer status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getMajor_id() {
		return major_id;
	}

	public void setMajor_id(Integer major_id) {
		this.major_id = major_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreated_time() {
		return (Timestamp) created_time.clone();
	}

	public void setCreated_time(Timestamp created_time) {
		this.created_time = (Timestamp) created_time.clone();
	}

	public Timestamp getUpdated_time() {
		return (Timestamp) updated_time.clone();
	}

	public void setUpdated_time(Timestamp updated_time) {
		this.updated_time = (Timestamp) updated_time.clone();
	}
}
