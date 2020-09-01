package com.redd4ford.insteadit.controller;

import com.redd4ford.insteadit.dto.AuthenticationResponse;
import com.redd4ford.insteadit.dto.LoginRequest;
import com.redd4ford.insteadit.dto.RefreshTokenRequest;
import com.redd4ford.insteadit.dto.RegisterRequest;
import com.redd4ford.insteadit.service.AuthService;
import com.redd4ford.insteadit.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;
  private final RefreshTokenService refreshTokenService;

  public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
    this.authService = authService;
    this.refreshTokenService = refreshTokenService;
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
    boolean isSuccessful = authService.signup(registerRequest);
    if (isSuccessful) {
      return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("User with such email and/or username cannot be signed up",
          HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(@Valid @RequestBody
                                             RefreshTokenRequest refreshTokenRequest) {
    refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
    return new ResponseEntity<>("Refresh Token deleted successfully", HttpStatus.OK);
  }

  @GetMapping("/accountVerification/{token}")
  public ResponseEntity<String> verifyAccount(@PathVariable String token) {
    authService.verifyAccount(token);
    return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
  }

  @PostMapping("/refresh/token")
  public AuthenticationResponse refreshTokens(@Valid @RequestBody
                                                    RefreshTokenRequest refreshTokenRequest) {
    return authService.refreshToken(refreshTokenRequest);
  }

}
