package correcter;

import java.io.IOException;
import java.util.Random;

public class SendMessage extends Message{

    @Override

    public void run() throws IOException {
        String pathFrom = "encoded.txt";
        getMessage(pathFrom);
        makeErr();
        String pathTo = "received.txt";
        sendMessage(pathTo);
    }
//simulate sending a message: creates mistake in a random bite for each byte
    private void makeErr() {

        Random random = new Random();

        for (int i = 0; i < message.length; i++) {

            StringBuilder stringBuilder = create8bitString(message[i]); //fills with '0' the start of string
            int indexOfRandomBit = random.nextInt(stringBuilder.length() - 1);
            char bit = stringBuilder.charAt(indexOfRandomBit);
            stringBuilder.setCharAt(indexOfRandomBit, getBit(bit));
            message[i] = (char)Integer.parseInt(new String(stringBuilder), 2);

        }

    }

    //change bit to opposite value
    private char getBit(char ch) {

        return ch == '0' ? '1' : '0';
    }
}
