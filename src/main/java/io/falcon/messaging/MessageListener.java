package io.falcon.messaging;

import io.falcon.dto.Message;
import io.falcon.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageListener {

  private final MessageRepository messageRepository;

  @KafkaListener(topics = "messages")
  public void messageListener(Message message) {
    messageRepository.save(message);
  }

}
