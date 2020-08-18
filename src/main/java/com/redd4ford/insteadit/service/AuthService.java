package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.RegisterRequest;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.model.NotificationEmail;
import com.redd4ford.insteadit.model.User;
import com.redd4ford.insteadit.model.VerificationToken;
import com.redd4ford.insteadit.repository.UserRepository;
import com.redd4ford.insteadit.repository.VerificationTokenRepository;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.redd4ford.insteadit.util.Constants.ACTIVATION_EMAIL;
import static java.time.Instant.now;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final VerificationTokenRepository verificationTokenRepository;
  private final MailContentBuilder mailContentBuilder;
  private final MailService mailService;

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                     VerificationTokenRepository verificationTokenRepository,
                     MailContentBuilder mailContentBuilder,
                     MailService mailService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.verificationTokenRepository = verificationTokenRepository;
    this.mailContentBuilder = mailContentBuilder;
    this.mailService = mailService;
  }

  @Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(encodePassword(registerRequest.getPassword()));
    user.setCreated(now());
    user.setEnabled(false);

    userRepository.save(user);

    String token = generateVerificationToken(user);
    String message = mailContentBuilder.build("Thank you for signing up to InsteadIt! Please click on the link below to activate your account: "
        + ACTIVATION_EMAIL + "/" + token);
    mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
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
    Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
    verificationTokenOptional.orElseThrow(() -> new InsteaditException("Invalid Token"));
    fetchUserAndEnable(verificationTokenOptional.get());
  }

  public void fetchUserAndEnable(VerificationToken verificationToken) {
    String username = verificationToken.getUser().getUsername();
    User user = userRepository.findByUsername(username).orElseThrow(() -> new InsteaditException("User Not Found with id - " + username));
    user.setEnabled(true);
    userRepository.save(user);
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

  public static Logger getLog() {
    return log;
  }

}