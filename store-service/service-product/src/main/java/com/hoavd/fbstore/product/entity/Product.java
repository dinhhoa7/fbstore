package com.hoavd.fbstore.product.entity;

import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "code")
  private String code;

  @Column(name = "category_id")
  private long categoryId;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "producer_id")
  private long producerId;

  @Column(name = "status")
  private boolean status;

  public Product() {
  }

  public Product(long id, String code, long categoryId, String name, String description, long producerId,
    boolean status) {
    this.id = id;
    this.code = code;
    this.categoryId = categoryId;
    this.name = name;
    this.description = description;
    this.producerId = producerId;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
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

  public long getProducerId() {
    return producerId;
  }

  public void setProducerId(long producerId) {
    this.producerId = producerId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
