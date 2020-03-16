package io.falcon.controller;

import io.falcon.dto.Message;
import java.io.IOException;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api")
@AllArgsConstructor
public class MessageController {

  private SimpMessageSendingOperations messaging;

  @PostMapping(value = "/message")
  public Message createMessage(Principal principal, @RequestBody Message message) throws IOException {
    messaging.convertAndSendToUser(principal.getName(), "/topic/message", message);
    return message;
  }

}
