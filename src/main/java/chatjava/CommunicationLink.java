package chatjava;

import java.io.InputStream;
import java.io.OutputStream;

public class CommunicationLink {

    private InputStream input;
    private OutputStream output;

    public CommunicationLink(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public InputStream getInput() {
        return input;
    }

    public OutputStream getOutput() {
        return output;
    }
}
