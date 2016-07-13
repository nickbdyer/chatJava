package chatjava;

import java.io.*;

public class Representative {

    private String name;
    private ByteArrayOutputStream outputToClient;
    private ByteArrayOutputStream outputToRoom;
    private BufferedReader inputFromClient;
    private BufferedReader inputFromRoom;

    public Representative(String name, CommunicationLink client, CommunicationLink room) {
        this.name = name;
        this.outputToClient = client.getOutput();
        this.inputFromClient = new BufferedReader(new InputStreamReader(client.getInput()));
        this.outputToRoom = room.getOutput();
        this.inputFromRoom = new BufferedReader(new InputStreamReader(room.getInput()));
    }

    public String getName() {
        return name;
    }

    public void listenAndRelayMessages() {
        routeMessages(inputFromClient, outputToRoom);
        routeMessages(inputFromRoom, outputToClient);
    }

    private void routeMessages(BufferedReader input, ByteArrayOutputStream output) {
        while (true) {
            try {
                if (!input.ready()) break;
                new PrintWriter(output, true).println(input.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ByteArrayOutputStream getOutputToRoom() {
        return outputToRoom;
    }
}
