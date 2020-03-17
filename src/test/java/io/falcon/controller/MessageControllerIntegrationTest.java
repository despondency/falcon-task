package io.falcon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.dto.Message;
import io.falcon.service.MessageService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class MessageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    @WithMockUser(username = "user")
    public void testGetAll() throws Exception {
        List<Message> ans = new ArrayList<>();
        Message message = new Message(1L, "message");
        ans.add(message);
        Mockito.when(messageService.getAllMessages()).thenReturn(ans);
        mockMvc.perform(get("/v1/api/message/"))
                .andExpect(status().is(200))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(ans)));
        Mockito.verify(messageService, times(1)).getAllMessages();
    }

    @Test
    @WithMockUser(username = "user")
    public void testCreateMessage() throws Exception {
        Message message = new Message(null, "message");
        Mockito.doNothing().when(messageService).pushToKafka(message);
        Mockito.doNothing().when(messageService).pushToWebsocket(Mockito.any(Principal.class), Mockito.anyString(), Mockito.eq(message));
        mockMvc.perform(post("/v1/api/message/")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(message)))
                .andExpect(status().is(200))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(message)));

        Mockito.verify(messageService, times(1)).pushToKafka(message);
        Mockito.verify(messageService, times(1)).pushToWebsocket(Mockito.any(Principal.class), Mockito.anyString(), Mockito.eq(message));
    }

}