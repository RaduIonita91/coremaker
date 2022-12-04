package com.coremaker.interview.security.services;

import com.coremaker.interview.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String username;

  private String email;

  @JsonIgnore private String password;

  public UserDetailsImpl(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public static UserDetailsImpl build(User user) {

    return new UserDetailsImpl(user.getUsername(), user.getEmail(), user.getPassword());
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }

  // This method would be usefull if we would use Roles for our users
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(username, user.getUsername()) && Objects.equals(email, user.getEmail());
  }
}
