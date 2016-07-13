package chatjava;

import java.io.*;

public class BroadCaster {

    private byte[] input;
    private ByteArrayOutputStream output;

    public BroadCaster() {
        this.input = new byte[]{};
        this.output = new ByteArrayOutputStream();
    }


    public void listenAndRelayMessages() {
        PrintWriter writer = new PrintWriter(output);
        writer.println(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input))));
    }
}
