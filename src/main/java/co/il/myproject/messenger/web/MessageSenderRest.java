package co.il.myproject.messenger.web;

import co.il.myproject.messenger.model.Message;
import co.il.myproject.messenger.service.messagehandler.MessageHandler;
import co.il.myproject.messenger.service.typemessage.MessageTypeReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static co.il.myproject.messenger.web.Constants.PATH_GET_ALL_TYPES;
import static co.il.myproject.messenger.web.Constants.PATH_TO_SEND_MESSAGE;


@Slf4j
@RestController
public class MessageSenderRest {

    private final MessageTypeReadService messageTypeReadService;

    private final MessageHandler messageHandler;

    @Autowired
    public MessageSenderRest(MessageTypeReadService messageTypeReadService, MessageHandler messageHandler) {
        this.messageTypeReadService = messageTypeReadService;
        this.messageHandler = messageHandler;
    }

    @PostMapping(value = PATH_TO_SEND_MESSAGE)
    public String sendMessage (@Valid @RequestBody Message message){
        log.info("Incoming message");
        return messageHandler.handle(message);
    }

    @GetMapping(PATH_GET_ALL_TYPES)
    public List<String> getAllMessageType (){
        log.info ("Request of all supported types of messages");
        return messageTypeReadService.getAllTypes();
    }
}
