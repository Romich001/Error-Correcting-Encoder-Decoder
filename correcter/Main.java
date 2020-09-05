package correcter;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        MessageFactory messageFactory = new MessageFactory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the mode: ");
        Message message = messageFactory.processMessage(scanner.next());
        message.run();

    }
}
