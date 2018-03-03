package com.wk.system.examination.entity.po;

import java.sql.Timestamp;

public class ExamInfo {
  private Long id;
  private Long exam_id;
  private Long question_id;
  private Long type;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getExam_id() {
    return exam_id;
  }

  public void setExam_id(Long exam_id) {
    this.exam_id = exam_id;
  }

  public Long getQuestion_id() {
    return question_id;
  }

  public void setQuestion_id(Long question_id) {
    this.question_id = question_id;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
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
