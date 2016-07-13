package chatjava;

import chatjava.testdoubles.DummyCommunicationLink;
import chatjava.testdoubles.DummyInputStream;
import org.junit.Assert;
import org.junit.Test;
import chatjava.testdoubles.DummyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class RepresentativeTest {

    @Test
    public void representativeHasAUserName() {
        Representative nick = new Representative("Nick", new DummyCommunicationLink(), new DummyCommunicationLink());
        Assert.assertEquals("Nick", nick.getName());
    }

    @Test
    public void representativeWillRepeatWhatTheirClientTellsThem() {
        OutputStream repeatedMessage = new ByteArrayOutputStream();
        InputStream messageFromClient = new ByteArrayInputStream("Hello".getBytes());
        CommunicationLink clientComms = new CommunicationLink(messageFromClient, new DummyOutputStream());
        CommunicationLink assessedComms = new CommunicationLink(new DummyInputStream(), repeatedMessage);
        Representative nick = new Representative("Nick", clientComms, assessedComms);

        nick.listenAndRepeat();

        assertEquals("Hello\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatHeardMessagesToTheirClient() {
        OutputStream repeatedMessage = new ByteArrayOutputStream();
        InputStream messageFromRoom = new ByteArrayInputStream("Hi Nick".getBytes());
        CommunicationLink clientComms = new CommunicationLink(new DummyInputStream(), repeatedMessage);
        CommunicationLink assessedComms = new CommunicationLink(messageFromRoom, new DummyOutputStream());
        Representative nick = new Representative("Nick", clientComms, assessedComms);

        nick.listenAndRepeat();

        assertEquals("Hi Nick\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatEverythingThatTheirClientTellsThem() {
        OutputStream repeatedMessage = new ByteArrayOutputStream();
        InputStream messageFromClient = new ByteArrayInputStream("Hello\nHow are you?".getBytes());
        CommunicationLink clientComms = new CommunicationLink(messageFromClient, new DummyOutputStream());
        CommunicationLink assessedComms = new CommunicationLink(new DummyInputStream(), repeatedMessage);
        Representative nick = new Representative("Nick", clientComms, assessedComms);

        nick.listenAndRepeat();

        assertEquals("Hello\nHow are you?\n", repeatedMessage.toString());
    }

    @Test
    public void representativeWillRepeatEverythingTheyHearToTheirClient() {
        OutputStream repeatedMessage = new ByteArrayOutputStream();
        InputStream messageFromRoom = new ByteArrayInputStream("Hi Nick\nWhat is going on?".getBytes());
        CommunicationLink clientComms = new CommunicationLink(new DummyInputStream(), repeatedMessage);
        CommunicationLink assessedComms = new CommunicationLink(messageFromRoom, new DummyOutputStream());
        Representative nick = new Representative("Nick", clientComms, assessedComms);

        nick.listenAndRepeat();

        assertEquals("Hi Nick\nWhat is going on?\n", repeatedMessage.toString());
    }
}
