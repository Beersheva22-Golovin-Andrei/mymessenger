package co.il.myproject.messenger.model;

import co.il.myproject.messenger.service.sender.Sender;
import co.il.myproject.messenger.service.sender.TcpSender;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonRootName("tcp")
public class TcpMessage extends Message{

    @Pattern(regexp = "^(([01]?dd?|2[0-4]d|25[0-5])\\.){3}([01]?dd?|2[0-4]d|25[0-5])$")
    private String ip;

    @Min(1024)
    @Max(5000)
    private int port;


    @Override
    public Sender createSender() {
        return new TcpSender();
    }
}
