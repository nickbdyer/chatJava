import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class ChatRoomTest {

    @Test
    public void isEmptyWhenCreated() {
        ChatRoom chatroom = ChatRoom.empty();
        assertTrue(chatroom.isEmpty());
    }

}
