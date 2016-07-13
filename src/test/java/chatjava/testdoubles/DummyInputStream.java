package chatjava.testdoubles;

import java.io.IOException;
import java.io.InputStream;

public class DummyInputStream extends InputStream{

    public int read() throws IOException {
        return 0;
    }

}
