package com.coremaker.interview.payload.response;

public class JWTResponse {

  private String token;
  private String type = "Bearer";

  public JWTResponse(String accessToken) {
    this.token = accessToken;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
}
