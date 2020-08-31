package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.AuthenticationResponse;
import com.redd4ford.insteadit.dto.LoginRequest;
import com.redd4ford.insteadit.dto.RegisterRequest;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.model.NotificationEmail;
import com.redd4ford.insteadit.model.User;
import com.redd4ford.insteadit.model.VerificationToken;
import com.redd4ford.insteadit.repository.UserRepository;
import com.redd4ford.insteadit.repository.VerificationTokenRepository;
import com.redd4ford.insteadit.security.JwtProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.redd4ford.insteadit.util.Constants.ACTIVATION_EMAIL;
import static com.redd4ford.insteadit.util.Constants.LOGGER;
import static java.time.Instant.now;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final VerificationTokenRepository verificationTokenRepository;
  private final MailContentBuilder mailContentBuilder;
  private final MailService mailService;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager,
                     JwtProvider jwtProvider,
                     VerificationTokenRepository verificationTokenRepository,
                     MailContentBuilder mailContentBuilder,
                     MailService mailService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
    this.verificationTokenRepository = verificationTokenRepository;
    this.mailContentBuilder = mailContentBuilder;
    this.mailService = mailService;
  }

  @Transactional
  public boolean signup(RegisterRequest registerRequest) {
    boolean isSuccessful = false;
    boolean isPresentWithEmail = userRepository
        .findByEmail(registerRequest.getEmail())
        .isPresent();
    boolean isPresentWithUsername = userRepository
        .findByUsername(registerRequest.getUsername())
        .isPresent();

    if (isPresentWithEmail && isPresentWithUsername) {
      LOGGER.info("User with email: " + registerRequest.getEmail() + " and username: " +
          registerRequest.getUsername() + "already exists.");
    } else if (isPresentWithEmail) {
      LOGGER.info("User with email: " + registerRequest.getEmail() + " already exists.");
    } else if (isPresentWithUsername) {
      LOGGER.info("User with username: " + registerRequest.getUsername() + " already exists.");
    } else {
      isSuccessful = true;
      User user = new User();
      user.setUsername(registerRequest.getUsername());
      user.setEmail(registerRequest.getEmail());
      user.setPassword(encodePassword(registerRequest.getPassword()));
      user.setCreated(now());
      user.setEnabled(false);

      userRepository.save(user);
      LOGGER.info("User is registered successfully. Sending an activation email...");

      String token = generateVerificationToken(user);
      String message = mailContentBuilder.build("Thank you for signing up to InsteadIt!" +
          "Please click on the link below to activate your account: "
          + ACTIVATION_EMAIL + "/" + token);
      mailService.sendMail(new NotificationEmail("Welcome to InsteadIt!", user.getEmail(), message));
    }

    return isSuccessful;
  }

  private String generateVerificationToken(User user) {
    String token = UUID.randomUUID().toString();
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setToken(token);
    verificationToken.setUser(user);
    verificationTokenRepository.save(verificationToken);
    return token;
  }

  @Transactional
  public void verifyAccount(String token) {
    Optional<VerificationToken> verificationTokenOptional =
        verificationTokenRepository.findByToken(token);
    verificationTokenOptional.orElseThrow(() -> new InsteaditException("Invalid Token"));
    fetchUserAndEnable(verificationTokenOptional.get());
  }

  public void fetchUserAndEnable(VerificationToken verificationToken) {
    String username = verificationToken.getUser().getUsername();
    User user = userRepository.findByUsername(username).orElseThrow(() ->
        new InsteaditException("User with id " + username + " is not found"));
    user.setEnabled(true);
    userRepository.save(user);
  }

  public AuthenticationResponse login(LoginRequest loginRequest) {
    Authentication authenticate = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authenticate);
    String authenticationToken = jwtProvider.generateToken(authenticate);
    return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
  }

  @Transactional(readOnly = true)
  public User getCurrentUser() {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    return userRepository.findByUsername(principal.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found - " +
            principal.getUsername()));
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public boolean isLoggedIn() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated();
  }

}
