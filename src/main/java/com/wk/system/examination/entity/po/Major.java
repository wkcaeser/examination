package com.wk.system.examination.entity.po;

public class Major {
  private Long id;
  private String name;
  private Long department_id;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
