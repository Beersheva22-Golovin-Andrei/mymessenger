package co.il.myproject.messenger.service.sender;

import co.il.myproject.messenger.model.SmsMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

@Slf4j
@Service("sms")
public class SmsSender implements Sender{

    private static final String MY_PHONE_NUMBER = "+97253*******";
    private static final String ACCOUNT_SID = "${twilio.account.sid}";
    private static final String AUTH_TOKEN = "${twilio.auth.token}";
    private static final String STATUS_OK = "sent";

    @Override
    public String send(co.il.myproject.messenger.model.Message message) {
        String resp;
        SmsMessage smsMes = (SmsMessage)message;
        log.info("Sending sms with Twilio service");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber from = new PhoneNumber(MY_PHONE_NUMBER);
        PhoneNumber rec = new PhoneNumber(smsMes.getPhoneNumber());
        Message sms = Message.creator(rec, from, smsMes.getText()).create();
       if (sms.getStatus().toString().equals(STATUS_OK)) {
           log.info("OK. SMS has been sent");
           resp = "SMS with text \""+message.getText()+"\" has been sent to phone number "+(smsMes.getPhoneNumber());
        } else {
           log.error("Some problem. Details: {}", sms.getSid());
           resp = "SMS wasn't sent. "+sms.getErrorMessage();
       }
        return resp;
    }
}
