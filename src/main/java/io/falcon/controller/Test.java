package io.falcon.controller;

import io.falcon.dto.Message;
import java.io.IOException;
import java.security.Principal;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class Test {



  @SendToUser(value = "/topic/message")
  public Message createMessage( @Payload Message message) throws IOException {
    return message; // have to push to websocket here as well
  }

}
