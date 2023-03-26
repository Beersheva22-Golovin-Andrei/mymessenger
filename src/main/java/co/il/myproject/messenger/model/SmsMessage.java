package co.il.myproject.messenger.model;

import co.il.myproject.messenger.service.sender.Sender;
import co.il.myproject.messenger.service.sender.SmsSender;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
@JsonRootName("sms")
public class SmsMessage extends Message{

    @NotNull
    private String phoneNumber;

    @Override
    public Sender createSender() {
        return new SmsSender();
    }
}
