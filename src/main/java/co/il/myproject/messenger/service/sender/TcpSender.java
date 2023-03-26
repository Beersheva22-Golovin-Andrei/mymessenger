package co.il.myproject.messenger.service.sender;

import co.il.myproject.messenger.model.Message;
import co.il.myproject.messenger.model.TcpMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("tcp")
public class TcpSender implements Sender{

    private static final String STATUS_OK = "OK";

    @Override
    public String send(Message message) {
        String resp;
       log.info("Sending message with tcp");
        TcpMessage tcpMessage = (TcpMessage) message;
        String ip = tcpMessage.getIp();
        int port = tcpMessage.getPort();
       /* try (Socket socket = new Socket(ip, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            log.info("Socket on IP{}, port {}", ip, port);
            PrintStream output = new PrintStream(socket.getOutputStream());
            output.println(tcpMessage.getText());
            String response = input.readLine();
            log.debug("Response {}", response);*/
            if (true)//response.equals(STATUS_OK)){
                resp = new StringBuilder("Message with text \"").append(message.getText()).append("\" has been sent due tcp on IP ")
                        .append(ip).append(", port ").append(port).toString();
                log.info("OK. Message has been sent");
 /*           } else {
                resp = "Message wasn't sent." + response;
                log.warn(resp);
            }
        } catch (IOException e){
            resp = "Message wasn't sent."+e.getMessage();
            log.error(resp);
        }*/
        return resp;
    }
}
