
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/* This class represents server and client as player */
public class Player implements Serializable {

    private final String name;
    private AtomicInteger messageCount;
    private String message;

    public Player(String name, AtomicInteger messageCount, String message) {
        this.name = name;
        this.messageCount = messageCount;
        this.message = message;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getMessageCount() {
        return messageCount;
    }

    public String getMessage() {
        return message;
    }

     /*
     * It creates the answer based on previous message from other side.
     * @param sender
     */
    public void createMessage(Player sender) {

        this.messageCount.incrementAndGet();
        this.message = sender.getMessage() + " " + this.getMessageCount();
    }
}