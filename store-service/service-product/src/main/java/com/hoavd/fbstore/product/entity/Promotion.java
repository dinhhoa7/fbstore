package com.hoavd.fbstore.product.entity;

import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "promotion")
public class Promotion extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "start_date")
  private long startDate;

  @Column(name = "end_date")
  private long endDate;

  @Column(name = "status")
  private boolean status;

  @Column(name = "expired")
  private boolean expired;

  @Column(name = "description")
  @Size(max = 1000)
  private String description;

  public Promotion() {
  }

  public Promotion(long id, String name, long startDate, long endDate, boolean status, boolean expired,
    String description) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.expired = expired;
    this.description = description;
  }

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

  public long getStartDate() {
    return startDate;
  }

  public void setStartDate(long startDate) {
    this.startDate = startDate;
  }

  public long getEndDate() {
    return endDate;
  }

  public void setEndDate(long endDate) {
    this.endDate = endDate;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
