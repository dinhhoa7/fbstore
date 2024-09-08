package com.hoavd.fbstore.admin.model.request;

public class ProductUpdateRequest {
  private long id;

  private String code;

  private long categoryId;

  private String name;

  private String description;

  private long producerId;

  private boolean status;

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
