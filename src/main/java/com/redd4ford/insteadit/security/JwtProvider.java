package com.redd4ford.insteadit.security;

import com.redd4ford.insteadit.exception.InsteaditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

import static io.jsonwebtoken.Jwts.parser;

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

  public boolean validateToken(String jwt) {
    parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
    return true;
  }

  private PublicKey getPublickey() {
    try {
      return keyStore.getCertificate("insteadit").getPublicKey();
    } catch (KeyStoreException e) {
      throw new InsteaditException("Exception occurred while retrieving public key from keystore");
    }
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = parser()
        .setSigningKey(getPublickey())
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

}
