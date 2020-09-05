package correcter;

import java.io.IOException;

public class EncodedMessage extends Message{

    @Override

    public void run() throws IOException {
        String pathFrom = "send.txt";
        getMessage(pathFrom);
        encodeMes();
        String pathTo = "encoded.txt";
        sendMessage(pathTo);
    }

    private void encodeMes(){

        StringBuilder messageInBinary = properLength();
        message = new char[messageInBinary.length() / 4];
        int countArrayOfMessage = 0;
        for (int i = 0; i < messageInBinary.length(); i+=4) {
            char[] bits = new char[4];
            messageInBinary.getChars(i, i+4, bits, 0);
            String newByte = getNewByte(bits);
            message[countArrayOfMessage] = (char) Integer.parseInt(newByte, 2);
            countArrayOfMessage++;
        }
    }

    //create new byte: 4 bits from message; 3 parity bits; 1 unused bit that == 0;
    private String getNewByte(char[] chars) {

        char[] bits = new char[8];
        bits[7] = '0';     //it is always '0' by the task
        bits[2] = chars[0];  //information
        bits[4] = chars[1];
        bits[5] = chars[2];
        bits[6] = chars[3];
        bits[0] = getParityBit(bits[2], bits[4], bits[6]);   //parityBits
        bits[1] = getParityBit(bits[2], bits[5], bits[6]);
        bits[3] = getParityBit(bits[4], bits[5], bits[6]);

        return new String(bits);

    }

    //return stringBuilder with message in bite code
    private StringBuilder properLength() {
        StringBuilder stringInBiteCode = new StringBuilder();
        for (char ch :
                message) {
            StringBuilder eightBitChar = create8bitString(ch);
            stringInBiteCode.append(eightBitChar);
        }

        return stringInBiteCode;
    }
}
