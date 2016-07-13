package chatjava;

import chatjava.testdoubles.DummyInputStream;
import chatjava.testdoubles.DummyOutputStream;
import chatjava.testdoubles.DummyRepresentative;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChatRoomTest {

    private DummyOutputStream dos;
    private DummyInputStream dis;

    @Before
    public void setUp() {
        dos = new DummyOutputStream();
        dis = new DummyInputStream();
    }

    @Test
    public void isEmptyWhenCreated() {
        ChatRoom chatRoom = ChatRoom.empty();
        assertTrue(chatRoom.isEmpty());
    }
    
    @Test
    public void afterAddingAPersonTheChatRoomIsNotEmpty() {
        ChatRoom chatRoom = ChatRoom.empty();
        chatRoom.add(new DummyRepresentative());
        assertFalse(chatRoom.isEmpty());
    }

    @Test
    public void theChatRoomIsEmptyWhenTheOnlyParticipantIsRemoved() {
        ChatRoom chatRoom = ChatRoom.empty();
        Representative nick = new DummyRepresentative();
        chatRoom.add(nick);
        chatRoom.remove(nick);
        assertTrue(chatRoom.isEmpty());
    }

    @Test
    public void aRepresentativeCanSendAMessageToTheBroadcaster() {
        ByteArrayInputStream input = new ByteArrayInputStream("Broadcast this message!".getBytes());
        BroadCaster broadCaster = new BroadCaster();
        ChatRoom chatRoom = ChatRoom.empty();
        Representative nick = new Representative("Nick", new CommunicationLink(input, dos), new CommunicationLink(dis, new ByteArrayOutputStream()));
        chatRoom.add(nick);

        nick.listenAndRelayMessages();
        broadCaster.listenAndRelayMessages(chatRoom);

        assertEquals("Broadcast this message!\n", broadCaster.getOutput().toString());
    }

    @Test
    public void aRepresentativesMessageToTheBroadcasterIsSentToOtherRepresentatives() {
        ByteArrayInputStream input = new ByteArrayInputStream("Broadcast this message!".getBytes());
        ByteArrayOutputStream nicksReceivedMessage = new ByteArrayOutputStream();
        ByteArrayOutputStream mateusReceivedMessage = new ByteArrayOutputStream();
        BroadCaster broadCaster = new BroadCaster();
        ChatRoom chatRoom = ChatRoom.empty();
        Representative nick = new Representative("Nick", new CommunicationLink(input, nicksReceivedMessage), new CommunicationLink(new ByteArrayInputStream(broadCaster.getOutput().toByteArray()), new ByteArrayOutputStream()));
        Representative mateu = new Representative("Mateu", new CommunicationLink(dis, mateusReceivedMessage), new CommunicationLink(new ByteArrayInputStream(broadCaster.getOutput().toByteArray()), new ByteArrayOutputStream()));
        chatRoom.add(nick);
        chatRoom.add(mateu);

        nick.listenAndRelayMessages();
        mateu.listenAndRelayMessages();
        broadCaster.listenAndRelayMessages(chatRoom);

        assertEquals("Broadcast this message!\n", nicksReceivedMessage.toString());
        assertEquals("Broadcast this message!\n", mateusReceivedMessage.toString());
    }

}
