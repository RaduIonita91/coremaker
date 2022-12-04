package com.coremaker.interview.service;

import com.coremaker.interview.Entity.User;
import com.coremaker.interview.payload.request.SignupRequest;
import com.coremaker.interview.payload.response.MessageResponse;
import com.coremaker.interview.repository.UserRepository;
import com.coremaker.interview.security.jwt.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired UserRepository userRepository;

  @Autowired PasswordEncoder encoder;

  @Override
  public ResponseEntity<MessageResponse> signup(SignupRequest signUpRequest) {

    ResponseEntity<MessageResponse> invalidSignupRequest = validateSignUpRequest(signUpRequest);
    if (invalidSignupRequest != null) {
      return invalidSignupRequest;
    }

    // Create new user's account
    User user =
        new User(
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getEmail());

    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @Override
  public ResponseEntity<?> getUserDetails(TokenInfo tokenInfo) {
    String username = tokenInfo.getUsername();
    Optional<User> byUsername = userRepository.findByUsername(username);
    return ResponseEntity.ok(byUsername.get());
  }

  private ResponseEntity<MessageResponse> validateSignUpRequest(SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already taken!"));
    }
    return null;
  }
}
