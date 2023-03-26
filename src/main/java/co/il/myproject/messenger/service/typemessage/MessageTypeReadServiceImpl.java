package co.il.myproject.messenger.service.typemessage;

import co.il.myproject.messenger.service.sender.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
public class MessageTypeReadServiceImpl implements MessageTypeReadService {


    private static ApplicationContext ctx;

    @Autowired
    public MessageTypeReadServiceImpl(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> typesList = Arrays.asList(ctx.getBeanNamesForType(Sender.class));
        log.debug("Count of supported types: {}", typesList.size());
        return typesList;
    }
}
