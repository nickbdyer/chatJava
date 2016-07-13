package chatjava;

import java.io.*;

public class BroadCaster {

    private ByteArrayOutputStream output;

    public BroadCaster() {
        this.output = new ByteArrayOutputStream();
    }

    public ByteArrayOutputStream getOutput() {
        return output;
    }

    public void listenAndRelayMessages(ChatRoom chatRoom) {
        for (Representative rep : chatRoom.getParticipants()) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(rep.getOutputToRoom().toByteArray());
            if (inputStream.available() > 0) {
                try {
                    new PrintWriter(output, true).println(new BufferedReader(new InputStreamReader(inputStream)).readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
