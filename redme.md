Write WEB service with two end-point methods: method String sendMessage(Message message) - POST method taking Message having a message type, text and additional fields in accordance with the message type and method List<String> getAllMessageTypes() - GET method
*sendMessage*: defines sender service matching the message type and calls method send(message) like sender.send(message). Sender just returns response as a string like SMS message with text <text> has been sent to phone <phone number>. No actual sending is required;
getAllMessageTypes() - returns all supported message types ;
There should be provided three Sender services: SmsSender, EmailSender, TcpSender
Appropriately there should be three Message classes extending class Message
SmsMessage - additional fields: String phoneNumber
EmailMessage - additional fields: String emailAddress
TcpMessage - additional fields: String IP address; int port;
Requirements:
1. Adding new sender type must not cause updating code (No one code line of existing code must be changed)
2. Controller should provide the data validation using jakarta validation constraints, like regular expression for IP address, port may be any number in the range (1024 - 5000), etc.
3. The code should use a logger
4. Write test for only Controller
5. Write test for all Sender service