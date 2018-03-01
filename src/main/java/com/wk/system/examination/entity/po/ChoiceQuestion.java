package com.wk.system.examination.entity.po;

public class ChoiceQuestion {
  private Long id;
  private String description;
  private String optiona;
  private String optionb;
  private String optionc;
  private String optiond;
  private Long answer;
  private Long department_id;
  private Long major_id;
  private Long teacher_id;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

	public Long getAnswer() {
		return answer;
	}

	public void setAnswer(Long answer) {
		this.answer = answer;
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

  public String getOptiona() {
    return optiona;
  }

  public void setOptiona(String optiona) {
    this.optiona = optiona;
  }

  public String getOptionb() {
    return optionb;
  }

  public void setOptionb(String optionb) {
    this.optionb = optionb;
  }

  public String getOptionc() {
    return optionc;
  }

  public void setOptionc(String optionc) {
    this.optionc = optionc;
  }

  public String getOptiond() {
    return optiond;
  }

  public void setOptiond(String optiond) {
    this.optiond = optiond;
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
    return created_time;
  }

  public void setCreated_time(java.sql.Timestamp created_time) {
    this.created_time = created_time;
  }

  public java.sql.Timestamp getUpdated_time() {
    return updated_time;
  }

  public void setUpdated_time(java.sql.Timestamp updated_time) {
    this.updated_time = updated_time;
  }
}
