package com.wk.system.examination.entity.po;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class Exam {
  private Long id;
  private String name;
  private Long lesson_id;
  private String releaseTime;
  private Long duration;
  private Long status;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp updated_time;


	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

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

  public Long getLesson_id() {
    return lesson_id;
  }

  public void setLesson_id(Long lesson_id) {
    this.lesson_id = lesson_id;
  }
	public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
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
