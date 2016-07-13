package chatjava.testdoubles;

import chatjava.CommunicationLink;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class DummyCommunicationLink extends CommunicationLink {

    public DummyCommunicationLink() {
        super(new ByteArrayInputStream("".getBytes()), new ByteArrayOutputStream());
    }

}
