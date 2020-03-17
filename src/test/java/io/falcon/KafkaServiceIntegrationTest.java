package io.falcon;


import io.falcon.dto.Message;
import io.falcon.messaging.KafkaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KafkaServiceIntegrationTest {

  private static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, false, "messages");

  @Autowired
  private KafkaService<String, Message> messageKafkaService;

  @Test
  public void testService() {
    messageKafkaService.emitMessage("key", new Message(1L, "hello"), "messages");
  }



}
