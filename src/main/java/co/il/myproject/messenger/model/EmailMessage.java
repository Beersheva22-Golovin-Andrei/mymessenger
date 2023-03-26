package co.il.myproject.messenger.model;

import co.il.myproject.messenger.service.sender.EmailSender;
import co.il.myproject.messenger.service.sender.Sender;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@JsonRootName("email")
@Getter
@Setter
public class EmailMessage extends Message{

    @Email
    private String emailAddress;

    @Override
    public Sender createSender() {
        return new EmailSender();
    }
}

