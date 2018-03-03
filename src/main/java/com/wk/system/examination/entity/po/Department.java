package com.wk.system.examination.entity.po;


import java.sql.Timestamp;

public class Department {

  private long id;
  private String name;
  private long status;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp updatedTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreatedTime() {
    return (Timestamp) createdTime.clone();
  }

  public void setCreatedTime(java.sql.Timestamp createdTime) {
    this.createdTime = (Timestamp) createdTime.clone();
  }


  public java.sql.Timestamp getUpdatedTime() {
    return (Timestamp) updatedTime.clone();
  }

  public void setUpdatedTime(java.sql.Timestamp updatedTime) {
    this.updatedTime = (Timestamp) updatedTime.clone();
  }

}
