package com.hoavd.fbstore.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(name = "status")
  private boolean status;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  public Admin() {
  }

  public Admin(long id, String username, String password, boolean status, boolean isDeleted) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.status = status;
    this.isDeleted = isDeleted;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }
}
