package com.coremaker.interview.controller;

import com.coremaker.interview.payload.request.LoginRequest;
import com.coremaker.interview.payload.request.SignupRequest;
import com.coremaker.interview.payload.response.JWTResponse;
import com.coremaker.interview.security.jwt.JwtUtils;
import com.coremaker.interview.security.jwt.TokenInfo;
import com.coremaker.interview.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired AuthenticationManager authenticationManager;
  @Autowired AuthService authService;
  @Autowired JwtUtils jwtUtils;

  @PostMapping("/auth/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.signup(signUpRequest);
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.ok(new JWTResponse(jwt));
  }

  @GetMapping("/user")
  public ResponseEntity<?> userAccess() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // this tokenInfo should be populated with data from the authentication
    // jwtUtils.getTokenInfo(authentication);
    TokenInfo tokenInfo = new TokenInfo();

    return authService.getUserDetails(tokenInfo);
  }
}
