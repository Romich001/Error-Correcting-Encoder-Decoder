package correcter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Message {
    char[] message;

    //using in decodeMessage and EncodedMessage classes
    char getParityBit(int ch1, int ch2, int ch3) {

        return (char)(ch1 ^ ch2 ^ ch3);
    }

    void getMessage(String path) throws IOException {

        byte[] input = Files.readAllBytes(Paths.get(path));
        message = new char[input.length];
        for (int i = 0; i < message.length; i++) {
            message[i] = (char)input[i];
        }

    }

    void sendMessage(String path) throws IOException {

        OutputStream outputStream = new FileOutputStream(path);

        for (char ch:
                message) {
            outputStream.write((byte)ch);
        }
    }
//create a string of 8 chars.
    StringBuilder create8bitString(char ch) {

        StringBuilder stringBuilder = new StringBuilder(Integer.toBinaryString(ch));
        while (stringBuilder.length() > 8) {
            stringBuilder.deleteCharAt(0);
        }
        while (stringBuilder.length() < 8) {
            stringBuilder.insert(0, '0');
        }
        return stringBuilder;
    }
//start of process
    public abstract void run() throws IOException;
}
