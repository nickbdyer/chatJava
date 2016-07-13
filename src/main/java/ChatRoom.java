import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    private List<Person> participants;

    public static ChatRoom empty() {
        return new ChatRoom();
    }

    public ChatRoom() {
        this.participants = new ArrayList<Person>();
    }

    public boolean isEmpty() {
        return participants.isEmpty();
    }

    public void add(Person person) {
        participants.add(person);
    }

    public void remove(Person person) {
        participants.remove(person);
    }
}
