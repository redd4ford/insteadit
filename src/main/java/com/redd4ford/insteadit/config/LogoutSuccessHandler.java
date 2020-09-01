package com.redd4ford.insteadit.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                              Authentication authentication) throws IOException, ServletException {
    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals("JWT") || cookie.getName().equals("JSESSIONID")) {
        // clear one cookie
        cookie.setValue("deleted");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        Cookie cookieWithSlash = (Cookie) cookie.clone();
        cookieWithSlash.setPath(request.getContextPath() + "/");
        response.addCookie(cookieWithSlash);
      }
    }

    super.onLogoutSuccess(request, response, authentication);
  }

}
