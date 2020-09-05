package correcter;

import java.io.IOException;

public class DecodedMessage extends Message{
    @Override
    public void run() throws IOException {
        String pathFrom = "received.txt";
        getMessage(pathFrom);
        decodeMes();
        String pathTo = "decoded.txt";
        sendMessage(pathTo);
    }
    //decode received message
    private void decodeMes() {

        StringBuilder decodeMesInBytes = decodeMesInBits();

        StringBuilder decodeMes  = new StringBuilder();
        for (int i = 0; i < decodeMesInBytes.length(); i+=8) {
            String charInByte = decodeMesInBytes.substring(i, i + 8);
            decodeMes.append((char)Integer.parseInt(charInByte, 2));
        }
        message = new String(decodeMes).toCharArray();
    }
//looks at each char of message as byte
    private StringBuilder decodeMesInBits() {
        StringBuilder decodeMesInBytes = new StringBuilder();
        for (char ch :
                message) {
            char[] fourBits = getFourBit(ch);
            decodeMesInBytes.append(fourBits);

        }
        return decodeMesInBytes;
    }
//get 4 bits from encoded message
    private char[] getFourBit(char ch) {
        char[] mesInBits = new String(create8bitString(ch)).toCharArray();
//check for errors in byte code
        //byte: [parityBit, parityBit, information, parityBit, info, info, info, noMeaning'0'
        int indOfErr =
                //find out the index of bit where mistake occurs. Need to sum up the indexes of parityBits that are wrong
                //add up all indexes of parityBits that multiples at 0 if there are not mistake and at 1 if there are.
                checkErr(mesInBits[0], mesInBits[2], mesInBits[4], mesInBits[6])
                        + 2 * checkErr(mesInBits[1], mesInBits[2], mesInBits[5], mesInBits[6])
                        + 4 * checkErr(mesInBits[3], mesInBits[4], mesInBits[5], mesInBits[6])
                        - 1;
        if(indOfErr != -1) {
            mesInBits[indOfErr] = mesInBits[indOfErr] == '0' ? '1' : '0';
        }
        return new char[]{mesInBits[2], mesInBits[4], mesInBits[5], mesInBits[6]};
    }
//check errors. if curParityBit == to received parityBit then return 0 else 1
    private int checkErr(char ch1, char ch2, char ch3, char ch4) {
        char parityBit = getParityBit(ch2, ch3, ch4);
        return parityBit == ch1 ? 0 : 1;
    }
}
