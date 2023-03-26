package co.il.myproject.messenger.service.messagehandler;

import co.il.myproject.messenger.model.Message;

public interface MessageHandler {

    String handle(Message message);
}
