package com.coremaker.interview.Entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {

  // This id should be relevant if we are using a real DB.
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank private String username;
  @NotBlank private String password;
  @NotBlank @Email private String email;

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
