package com.coremaker.interview.security.jwt;

public class TokenInfo {
  private String username;
  private String email;
  private String accessToken;

  public TokenInfo(String username, String email, String accessToken) {
    this.username = username;
    this.email = email;
    this.accessToken = accessToken;
  }

  public TokenInfo() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
