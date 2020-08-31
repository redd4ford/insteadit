package com.redd4ford.insteadit.controller;

import com.redd4ford.insteadit.dto.AuthenticationResponse;
import com.redd4ford.insteadit.dto.LoginRequest;
import com.redd4ford.insteadit.dto.RegisterRequest;
import com.redd4ford.insteadit.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
    boolean isSuccessful = authService.signup(registerRequest);
    if (isSuccessful) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>("User with such email and/or username cannot be signed up",
          HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @GetMapping("accountVerification/{token}")
  public ResponseEntity<String> verifyAccount(@PathVariable String token) {
    authService.verifyAccount(token);
    return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
  }

}
