package chatjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Representative {

    private String name;
    private PrintWriter outputToClient;
    private PrintWriter outputToRoom;
    private BufferedReader inputFromClient;
    private BufferedReader inputFromRoom;

    public Representative(String name, CommunicationLink client, CommunicationLink room) {
        this.name = name;
        this.outputToClient = new PrintWriter(client.getOutput(), true);
        this.inputFromClient = new BufferedReader(new InputStreamReader(client.getInput()));
        this.outputToRoom = new PrintWriter(room.getOutput(), true);
        this.inputFromRoom = new BufferedReader(new InputStreamReader(room.getInput()));
    }

    public String getName() {
        return name;
    }

    public void listenAndRelayMessages() {
        routeMessages(inputFromClient, outputToRoom);
        routeMessages(inputFromRoom, outputToClient);
    }

    private void routeMessages(BufferedReader input, PrintWriter output) {
        while (true) {
            try {
                if (!input.ready()) break;
                output.println(input.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
