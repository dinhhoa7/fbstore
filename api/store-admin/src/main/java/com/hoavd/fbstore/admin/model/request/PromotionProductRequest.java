package com.hoavd.fbstore.admin.model.request;

public class PromotionProductRequest {
  private long productId;

  private long promotionId;

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(long promotionId) {
    this.promotionId = promotionId;
  }
}
