package com.hoavd.fbstore.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "createdAt")
  @Column(name = "created_at")
  private long createdAt;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "updatedAt")
  @Column(name = "updated_at")
  private long updatedAt;

  public long getCreatedAt() {
    return createdAt;
  }

  public BaseEntity setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public BaseEntity setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public BaseEntity create() {
    long time = System.currentTimeMillis();
    setCreatedAt(time);
    setUpdatedAt(time);
    return this;
  }

  public BaseEntity update() {
    long time = System.currentTimeMillis();
    setUpdatedAt(time);
    return this;
  }
}
