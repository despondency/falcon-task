package io.falcon.messaging;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KafkaService<K,V> {

  private final KafkaTemplate<K,V> kafkaTemplate;

  public void emitMessage(K key, V value, String topic) {
    kafkaTemplate.send(topic, key, value);
  }

}
