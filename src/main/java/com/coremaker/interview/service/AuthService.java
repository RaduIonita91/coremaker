package com.coremaker.interview.service;

import com.coremaker.interview.payload.request.SignupRequest;
import com.coremaker.interview.payload.response.MessageResponse;
import com.coremaker.interview.security.jwt.TokenInfo;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  public ResponseEntity<MessageResponse> signup(SignupRequest signUpRequest);

  public ResponseEntity<?> getUserDetails(TokenInfo tokenInfo);
}
