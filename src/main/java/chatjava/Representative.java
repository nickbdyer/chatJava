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

    public static Representative random(CommunicationLink client, CommunicationLink room) {
        String name = "guest" + String.valueOf(Math.round(Math.random() * 1000));
        return new Representative(name, client, room);
    }

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

    public void listenAndRepeat() {
        try {
            while (true) {
                if (!inputFromClient.ready()) break;
                outputToRoom.println(inputFromClient.readLine());
            }
            while (true) {
                if (!inputFromRoom.ready()) break;
                outputToClient.println(inputFromRoom.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
