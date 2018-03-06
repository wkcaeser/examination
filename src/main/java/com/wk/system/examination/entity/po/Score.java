package com.wk.system.examination.entity.po;

import java.sql.Timestamp;

public class Score {
  private Long id;
  private Long exam_id;
  private Long user_id;
  private Long score_choice;
  private Long score_objective;
  private Long score_max;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;

	public Long getScore_max() {
		return score_max;
	}

	public void setScore_max(Long score_max) {
		this.score_max = score_max;
	}

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

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getScore_choice() {
    return score_choice;
  }

  public void setScore_choice(Long score_choice) {
    this.score_choice = score_choice;
  }

  public Long getScore_objective() {
    return score_objective;
  }

  public void setScore_objective(Long score_objective) {
    this.score_objective = score_objective;
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
