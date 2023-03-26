package co.il.myproject.messenger.model;


import co.il.myproject.messenger.service.sender.Sender;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailMessage.class, name = "email"),
        @JsonSubTypes.Type(value = SmsMessage.class, name = "sms"),
        @JsonSubTypes.Type(value = TcpMessage.class, name = "tcp")
})
@Getter
@Setter
public abstract class Message {

    @NotNull
    private String type;

    @NotNull
    @NotBlank
    private String text;

    public String process (){
        Sender sender = createSender();
        return sender.send(this);
    }

    public abstract Sender createSender();
}
