package co.il.myproject.messenger.service.messagehandler;

import co.il.myproject.messenger.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageHandlerImpl implements MessageHandler {

    @Override
    public String handle(Message message) { //Sender sender = ctx.getBean(message.getType(), Sender.class);
        return message.process();
    }
}
