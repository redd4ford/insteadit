package com.redd4ford.insteadit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/")
  public ResponseEntity<String> homePage() {
    return new ResponseEntity<>("Hello world!", HttpStatus.OK);
  }

}
