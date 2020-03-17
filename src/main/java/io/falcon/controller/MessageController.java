package io.falcon.controller;

import io.falcon.dto.Message;
import io.falcon.service.MessageService;
import java.io.IOException;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api")
@AllArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping(value = "/message")
  public Message createMessage(Principal principal, @RequestBody Message message) throws IOException {
    messageService.pushToKafka(message);
    messageService.pushToWebsocket(principal, "/topic/message", message);
    return message;
  }

}
