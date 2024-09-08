package com.hoavd.fbstore.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "dob")
  private long dob;

  @Column(name = "gender")
  private String gender;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "status")
  private boolean status;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  public User() {
  }

  public User(long id, String username, String password, String fullName, long dob, String gender, String email,
    String address, String phoneNumber, boolean status, boolean isDeleted) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.dob = dob;
    this.gender = gender;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
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

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public long getDob() {
    return dob;
  }

  public void setDob(long dob) {
    this.dob = dob;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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
