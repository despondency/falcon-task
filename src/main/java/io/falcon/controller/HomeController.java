package io.falcon.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/message-receiver")
  public String messageReceiver() {
    return "index";
  }

  @GetMapping("/")
  public String home() {
    return "index";
  }
}
