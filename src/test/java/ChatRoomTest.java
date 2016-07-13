import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChatRoomTest {

    @Test
    public void isEmptyWhenCreated() {
        ChatRoom chatRoom = ChatRoom.empty();
        assertTrue(chatRoom.isEmpty());
    }
    
    @Test
    public void afterAddingAPersonTheChatRoomIsNotEmpty() {
        ChatRoom chatRoom = ChatRoom.empty();
        chatRoom.add(Person.random());
        assertFalse(chatRoom.isEmpty());
    }

    @Test
    public void theChatRoomIsEmptyWhenTheOnlyParticipantIsRemoved() {
        ChatRoom chatRoom = ChatRoom.empty();
        Person nick = Person.random();
        chatRoom.add(nick);
        chatRoom.remove(nick);
        assertTrue(chatRoom.isEmpty());
    }

    //Can broadcast to participants

}
