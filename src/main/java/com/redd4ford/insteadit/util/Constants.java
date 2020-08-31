package com.redd4ford.insteadit.util;

import com.redd4ford.insteadit.service.AuthService;
import org.slf4j.Logger;

public final class Constants {

  public static final String ACTIVATION_EMAIL =
      "http://localhost:8080/api/auth/accountVerification";

  public static final String POST_URL =
      "http://localhost:8080/api/posts/";

  public static final org.slf4j.Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(AuthService.class);

  private Constants() {
  }

  public static String getActivationEmail() {
    return ACTIVATION_EMAIL;
  }

  public static String getPostUrl() {
    return POST_URL;
  }

  public static Logger getLOGGER() {
    return LOGGER;
  }

}
