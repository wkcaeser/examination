package com.wk.system.examination.entity.po;

public class User {
  private Long id;
  private String username;
  private String password;
  private String email;
  private String name;
  private Long department_id;
  private Long major_id;
  private Long level;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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


  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", department_id=" + department_id +
				", major_id=" + major_id +
				", level=" + level +
				", status=" + status +
				", created_time=" + created_time +
				", updated_time=" + updated_time +
				'}';
	}
}
