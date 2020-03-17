package io.falcon.messaging;

import io.falcon.dto.Message;
import io.falcon.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaMessageListener {

  private final MessageRepository messageRepository;

  @KafkaListener(topics = "messages")
  public void messageListener(@Payload Message message) {
    log.debug("Received message {} and will persist it to db", message);
    messageRepository.save(message);
  }

}
