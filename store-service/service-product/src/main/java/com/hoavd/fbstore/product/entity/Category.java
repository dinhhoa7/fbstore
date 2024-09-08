package com.hoavd.fbstore.product.entity;

import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "status")
  private boolean status;

  public Category() {
  }

  public Category(long id, String name, String description, boolean status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
