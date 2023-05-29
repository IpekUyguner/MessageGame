
import java.io.IOException;

/* Server and Client are started seperately with command argument */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if("server".equals(args[0])){
            Server server = new Server(5000);
        }
        else if ("client".equals(args[0])) {
            Client client = new Client("127.0.0.1", 5000);
        }
    }
}