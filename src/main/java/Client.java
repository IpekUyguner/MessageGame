
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private Socket socket = null;

    void messanger(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException {
        Player sender;
        while ((sender = (Player) is.readObject()) != null) {
            // waits for the first message
            player.createMessage(sender);
            System.out.println("Getting from server: " + sender.getMessage());
            //Stop when 10 message is achieved.
            if (sender.getMessageCount().intValue() >= 10 && player.getMessageCount().intValue() >= 10) {
                System.out.println("Closing connection in client");
                is.close();
                os.close();
                socket.close();
                break;
            }

            System.out.println("Sending from client: " + player.getMessage());
            os.reset();
            os.writeObject(player);
        }
    }
    public Client(String address, int port) throws IOException, ClassNotFoundException {
        try {
            socket = new Socket(address, port);
            System.out.println("Client connected.");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            AtomicInteger i = new AtomicInteger(0);
            Player player = new Player("Player 2", i, "");
            System.out.println("Server found. Client Connected!");
            messanger(player, is, os);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}

