package co.il.myproject.messenger.web;

import co.il.myproject.messenger.model.EmailMessage;
import co.il.myproject.messenger.model.Message;
import co.il.myproject.messenger.model.SmsMessage;
import co.il.myproject.messenger.model.TcpMessage;
import co.il.myproject.messenger.service.messagehandler.MessageHandler;
import co.il.myproject.messenger.service.typemessage.MessageTypeReadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static co.il.myproject.messenger.web.Constants.PATH_GET_ALL_TYPES;
import static co.il.myproject.messenger.web.Constants.PATH_TO_SEND_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest (MessageSenderRest.class)
public class MessageSenderRestWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageHandler messageHandler;

    @MockBean
    private MessageTypeReadService messageTypeReadService;


    @Test
    public void sendingEmailMessageTest_then200() throws Exception {
        String response = "Email message with text \"1111/1111111\" has been sent to ddd@mail.ru";
        Mockito.when(messageHandler.handle(any(Message.class))).thenReturn(response);
        EmailMessage message = new EmailMessage();
        message.setType("email");
        message.setText("1111/1111111");
        message.setEmailAddress("ddd@mail.ru");
        mockMvc.perform(
                post(PATH_TO_SEND_MESSAGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(message))
        )
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void getAllTypesTest_then200 () throws Exception {
        List<String> response = Arrays.asList("email", "sms", "tcp");
        Mockito.when(messageTypeReadService.getAllTypes()).thenReturn(response);
        mockMvc.perform(
                MockMvcRequestBuilders.get(PATH_GET_ALL_TYPES))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(response)));
    }

    @Test
    public void sendingTcpMessageTest_then400() throws Exception {
        TcpMessage message = new TcpMessage();
        message.setType("tcp");
        message.setText("1111/1111111");
        message.setPort(1000);
        mockMvc.perform(
                        post(PATH_TO_SEND_MESSAGE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(message))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void sendingSmsMessageTest_then400() throws Exception {
        SmsMessage message = new SmsMessage();
        message.setType("sms");
        message.setText("1111/1111111");
        mockMvc.perform(
                        post(PATH_TO_SEND_MESSAGE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(message))
                )
                .andExpect(status().isBadRequest());
    }
}
