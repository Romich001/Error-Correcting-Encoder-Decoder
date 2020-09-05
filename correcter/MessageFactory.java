package correcter;

public class MessageFactory {

    public Message processMessage(String type) {

        if("decode".equals(type)) {
            return new DecodedMessage();
        }else if("send".equals(type)) {
            return  new SendMessage();
        }

        return new EncodedMessage();


    }
}
