package co.il.myproject.messenger.service.sender;

import co.il.myproject.messenger.model.Message;

public interface Sender {

    String send(Message message);
}
