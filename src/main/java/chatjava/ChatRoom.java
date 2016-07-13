package chatjava;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    private List<Representative> participants;
    private BroadCaster broadcaster;

    public static ChatRoom empty() {
        return new ChatRoom();
    }

    public static ChatRoom withBroadCaster(BroadCaster broadCaster) {
        ChatRoom chatroom = new ChatRoom();
        chatroom.setBroadcaster(broadCaster);
        return chatroom;
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

    public void setBroadcaster(BroadCaster broadcaster) {
        this.broadcaster = broadcaster;
    }
}
