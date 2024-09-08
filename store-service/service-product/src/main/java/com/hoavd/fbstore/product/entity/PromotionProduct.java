package com.hoavd.fbstore.product.entity;

import com.hoavd.fbstore.common.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "promotion_product")
public class PromotionProduct extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "promotion_id")
  private long promotionId;

  @Column(name = "product_id")
  private long productId;

  @Column(name = "status")
  private boolean status;

  public PromotionProduct() {
  }

  public PromotionProduct(long id, long promotionId, long productId, boolean status) {
    this.id = id;
    this.promotionId = promotionId;
    this.productId = productId;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(long promotionId) {
    this.promotionId = promotionId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
