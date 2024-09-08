package com.hoavd.fbstore.admin.model.dto;

public class AuthModel {
  private String username;
  private long userId;
  private String provider;
  private String activeMode;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getActiveMode() {
    return activeMode;
  }

  public void setActiveMode(String activeMode) {
    this.activeMode = activeMode;
  }
}
