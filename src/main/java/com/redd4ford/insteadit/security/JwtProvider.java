package com.redd4ford.insteadit.security;

import com.redd4ford.insteadit.exception.InsteaditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

import static com.redd4ford.insteadit.util.Constants.JWT_EXPIRATION_IN_MILLS;

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
        .setIssuedAt(from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plusMillis(JWT_EXPIRATION_IN_MILLS)))
        .signWith(getPrivateKey())
        .compact();
  }

  public String generateTokenWithUserName(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plusMillis(JWT_EXPIRATION_IN_MILLS)))
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

  public boolean validateToken(String jwt) {
    parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
    return true;
  }

  private PublicKey getPublicKey() {
    try {
      return keyStore.getCertificate("insteadit").getPublicKey();
    } catch (KeyStoreException e) {
      throw new InsteaditException("Exception occurred while retrieving public key from keystore");
    }
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = parser()
        .setSigningKey(getPublicKey())
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

}
