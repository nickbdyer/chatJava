package chatjava;

import chatjava.testdoubles.DummyCommunicationLink;
import chatjava.testdoubles.DummyInputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import chatjava.testdoubles.DummyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class RepresentativeTest {

    private OutputStream repeatedMessage;
    private InputStream dummyInput;
    private OutputStream dummyOutput;
    private CommunicationLink assertComms;

    @Before
    public void setUp() {
        repeatedMessage = new ByteArrayOutputStream();
        dummyInput = new DummyInputStream();
        dummyOutput = new DummyOutputStream();
        assertComms = new CommunicationLink(dummyInput, repeatedMessage);
    }

    @Test
    public void representativeHasAUserName() {
        Representative nick = new Representative("Nick", new DummyCommunicationLink(), new DummyCommunicationLink());
        Assert.assertEquals("Nick", nick.getName());
    }

    @Test
    public void representativeWillRepeatWhatTheirClientTellsThem() {
        InputStream messageFromClient = new ByteArrayInputStream("Hello".getBytes());
        CommunicationLink clientComms = new CommunicationLink(messageFromClient, dummyOutput);
        Representative nick = new Representative("Nick", clientComms, assertComms);

        nick.listenAndRepeat();

        assertEquals("Hello\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatHeardMessagesToTheirClient() {
        InputStream messageFromRoom = new ByteArrayInputStream("Hi Nick".getBytes());
        CommunicationLink roomComms = new CommunicationLink(messageFromRoom, dummyOutput);
        Representative nick = new Representative("Nick", assertComms, roomComms);

        nick.listenAndRepeat();

        assertEquals("Hi Nick\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatEverythingThatTheirClientTellsThem() {
        InputStream messageFromClient = new ByteArrayInputStream("Hello\nHow are you?".getBytes());
        CommunicationLink clientComms = new CommunicationLink(messageFromClient, dummyOutput);
        Representative nick = new Representative("Nick", clientComms, assertComms);

        nick.listenAndRepeat();

        assertEquals("Hello\nHow are you?\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatEverythingTheyHearToTheirClient() {
        InputStream messageFromRoom = new ByteArrayInputStream("Hi Nick\nWhat is going on?".getBytes());
        CommunicationLink roomComms = new CommunicationLink(messageFromRoom, dummyOutput);
        Representative nick = new Representative("Nick", assertComms, roomComms);

        nick.listenAndRepeat();

        assertEquals("Hi Nick\nWhat is going on?\n", repeatedMessage.toString());
    }
}
