package io.falcon.service;

import io.falcon.dto.Message;
import io.falcon.messaging.KafkaService;
import io.falcon.repository.MessageRepository;
import java.security.Principal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

  private final KafkaService<String, Message> kafkaService;

  private final MessageRepository messageRepository;

  private final SimpMessageSendingOperations messaging;

  public void pushToKafka(Message message) {
    kafkaService.emitMessage(null, message, "messages");
  }

  public void pushToWebsocket(Principal principal, String endpoint, Message message) {
    messaging.convertAndSend(endpoint, message);
  }

  public Iterable<Message> getAllMessages() {
    return messageRepository.findAll();
  }

}
