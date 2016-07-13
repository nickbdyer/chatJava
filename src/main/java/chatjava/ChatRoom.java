package chatjava;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    private List<Representative> participants;

    public static ChatRoom empty() {
        return new ChatRoom();
    }

    public ChatRoom() {
        this.participants = new ArrayList<>();
    }

    public boolean isEmpty() {
        return participants.isEmpty();
    }

    public void add(Representative representative) {
        participants.add(representative);
    }

    public void remove(Representative representative) {
        participants.remove(representative);
    }

    public List<Representative> getParticipants() {
        return participants;
    }

}
