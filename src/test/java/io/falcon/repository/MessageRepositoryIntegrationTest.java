package io.falcon.repository;

import io.falcon.dto.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MessageRepositoryIntegrationTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testCreateMessageAndPersist() {
        Message message = new Message(null, "message");
        Message message1 = messageRepository.save(message);
        Message persisted = messageRepository.findById(message1.getId())
                        .orElseThrow(() -> new IllegalStateException("Not found in db"));

        Assertions.assertEquals(message1, persisted);
    }



}
