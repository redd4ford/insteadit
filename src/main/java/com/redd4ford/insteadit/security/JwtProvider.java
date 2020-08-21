package com.redd4ford.insteadit.security;

import com.redd4ford.insteadit.exception.InsteaditException;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

  private KeyStore keyStore;

  @PostConstruct
  public void init() {
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream = getClass().getResourceAsStream("/insteadit.jks");
      keyStore.load(resourceAsStream, "P4r4d0x!".toCharArray());
    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
      throw new InsteaditException("An exception occurred while loading keystore");
    }
  }
  public String generateToken(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(getPrivateKey())
        .compact();
  }
  private PrivateKey getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("insteadit", "P4r4d0x!".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
      throw new InsteaditException("An exception occurred" +
          "while retrieving public key from keystore");
    }
  }

}
