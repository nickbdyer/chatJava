package chatjava;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CommunicationLink {

    private InputStream input;
    private ByteArrayOutputStream output;

    public CommunicationLink(InputStream input, ByteArrayOutputStream output) {
        this.input = input;
        this.output = output;
    }

    public InputStream getInput() {
        return input;
    }

    public ByteArrayOutputStream getOutput() {
        return output;
    }
}
