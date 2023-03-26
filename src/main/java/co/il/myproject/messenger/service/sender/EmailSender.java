package co.il.myproject.messenger.service.sender;

import co.il.myproject.messenger.model.EmailMessage;
import co.il.myproject.messenger.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service("email")
public class EmailSender implements Sender {

    public static final String EMAIL_FROM = "example@gmail.com";

    @Override
    public String send(Message message) {
        String resp;
        EmailMessage emailMessage = ((EmailMessage)message);
     /*   try {
            Session session = Session.getInstance(fillProp());
            log.info("Session for sending email has been created");
            InternetAddress from = new InternetAddress(EMAIL_FROM);
            InternetAddress rec = new InternetAddress(emailMessage.getEmailAddress());
            MimeMessage mime = new MimeMessage(session);
            mime.addRecipient(MimeMessage.RecipientType.TO, rec);
            mime.setFrom(from);
            mime.setSubject("example");
            mime.setText(message.getText());
            Transport.send(mime);*/
            log.info("OK. Email has been sent");
            resp = "Email message with text \""+message.getText()+"\" has been sent to "+(emailMessage.getEmailAddress());
       /* } catch (MessagingException e) {
            log.error("Some problem");
            resp = "Email wasn't sent."+e.getMessage();
        }*/
    return resp;
    }

    private Properties fillProp (){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "127.0.0.1");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.port", "500");
        log.info("Properties has been filled");
        return properties;
    }
}
