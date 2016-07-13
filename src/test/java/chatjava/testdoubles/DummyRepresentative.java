package chatjava.testdoubles;

import chatjava.Representative;

public class DummyRepresentative extends Representative {

    public DummyRepresentative() {
        super(null, new DummyCommunicationLink(), new DummyCommunicationLink());
    }

}
