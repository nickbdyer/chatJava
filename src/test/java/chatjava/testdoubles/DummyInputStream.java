package chatjava.testdoubles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DummyInputStream extends ByteArrayInputStream{

    public DummyInputStream() {
        super("".getBytes());
    }

}
