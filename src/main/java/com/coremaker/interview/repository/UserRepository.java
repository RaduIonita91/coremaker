package com.coremaker.interview.repository;

import com.coremaker.interview.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// This class Should extend a JPARepository from Spring
public class UserRepository {

  // I chose ArrayList because it's faster at getting elements than LinkedLists.
  // and i assumed that there are going to be more operations of getting users that creating
  private List<User> users = new ArrayList<>();

  public void save(User user) {
    users.add(user);
  }

  public Optional<User> findByUsername(String username) {
    return users.stream().filter(u -> username.equalsIgnoreCase(u.getUsername())).findFirst();
  }

  public boolean existsByUsername(String username) {
    Optional<User> existingUsername =
        users.stream().filter(u -> username.equalsIgnoreCase(u.getUsername())).findAny();
    return existingUsername.isPresent();
  }

  public boolean existsByEmail(String email) {
    Optional<User> existingEmail =
        users.stream().filter(u -> email.equalsIgnoreCase(u.getUsername())).findAny();
    return existingEmail.isPresent();
  }
}
