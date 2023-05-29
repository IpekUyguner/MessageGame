import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;

    void messanger(Player player, ObjectInputStream in, ObjectOutputStream os) throws IOException, ClassNotFoundException {
        Player sender;
        while ((sender = (Player) in.readObject()) != null) {
            player.createMessage(sender);
            System.out.println("Getting from client: " + sender.getMessage());
            System.out.println("Sending from server: " + player.getMessage());
            os.reset();
            os.writeObject(player);
            if (sender.getMessageCount().intValue() >= 10 && player.getMessageCount().intValue() >= 10) {
                System.out.println("Closing connection in server");
                socket.close();
                in.close();
            }

        }
    }

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client...");

            socket = server.accept();
            System.out.println("Client joined.");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            /* Send the first greeting message and start chatting...*/
            AtomicInteger i = new AtomicInteger(0);
            Player player = new Player("Player 1", i, "Hello");
            System.out.println("Server says: " + player.getMessage());
            os.writeObject(player);
            messanger(player, is, os);
            System.out.println("Closing connection...");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }


    }

}