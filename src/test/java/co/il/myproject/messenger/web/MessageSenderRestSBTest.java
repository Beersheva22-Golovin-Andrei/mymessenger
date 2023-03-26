package co.il.myproject.messenger.web;

import co.il.myproject.messenger.model.EmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static co.il.myproject.messenger.web.Constants.PATH_GET_ALL_TYPES;
import static co.il.myproject.messenger.web.Constants.PATH_TO_SEND_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
public class MessageSenderRestSBTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    public void setUp (){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sendEmailMessageTest_res200() throws Exception {
        String response = "Email message with text \"1111/1111111\" has been sent to ddd@mail.ru";
        EmailMessage message = new EmailMessage();
        message.setType("email");
        message.setText("1111/1111111");
        message.setEmailAddress("ddd@mail.ru");
        setUp();
        MvcResult mvcResult = mvc
                .perform(
                        MockMvcRequestBuilders.post(PATH_TO_SEND_MESSAGE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(message))
                )
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), response);
    }

    @Test
    public void getAllTypesTest_res200() throws Exception {
        List<String> response = Arrays.asList("email", "sms", "tcp");
        setUp();
        mvc
                .perform(
                        MockMvcRequestBuilders.get(PATH_GET_ALL_TYPES)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(response.toString()))
                .andReturn();
    }
}
