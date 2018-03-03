package com.wk.system.examination.entity.po;

import java.sql.Timestamp;

public class ObjectiveQuestion {
  private Long id;
  private String description;
  private Integer score;
  private Long department_id;
  private Long major_id;
  private Long teacher_id;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(Long department_id) {
    this.department_id = department_id;
  }

  public Long getMajor_id() {
    return major_id;
  }

  public void setMajor_id(Long major_id) {
    this.major_id = major_id;
  }

  public Long getTeacher_id() {
    return teacher_id;
  }

  public void setTeacher_id(Long teacher_id) {
    this.teacher_id = teacher_id;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public java.sql.Timestamp getCreated_time() {
    return (Timestamp) created_time.clone();
  }

  public void setCreated_time(java.sql.Timestamp created_time) {
    this.created_time = (Timestamp) created_time.clone();
  }

  public java.sql.Timestamp getUpdated_time() {
    return (Timestamp) updated_time.clone();
  }

  public void setUpdated_time(java.sql.Timestamp updated_time) {
    this.updated_time = (Timestamp) updated_time.clone();
  }
}
