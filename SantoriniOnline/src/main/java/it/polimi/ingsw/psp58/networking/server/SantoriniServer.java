package it.polimi.ingsw.psp58.networking.server;

import it.polimi.ingsw.psp58.controller.Lobby;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SantoriniServer {

    public final static int SOCKET_PORT = 7557;
    public final static int SOCKET_TIMEOUT_S = 20;

    public static void main(String[] args) throws IOException {

        boolean pingStamp = false;

        Lobby lobby = Lobby.instance();
        ServerSocket socket;

        try {
            //Creates a socket on the server
            socket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.err.println("Unable to open the server socket.");
            System.exit(1);
            return;
        }

        if (args != null) {
            for (String currentArgument : args) {
                switch (currentArgument) {
                    case "-ping":
                        pingStamp = true;
                        System.out.println("SERVER SETTINGS: <Ping Stamp> ON");
                        break;
                }
            }
        }
        System.out.println("SERVER: The server is running");
        while (true) { //waiting for a client to connect


            Socket clientIncoming = socket.accept(); //get the client socket
            clientIncoming.setSoTimeout(SOCKET_TIMEOUT_S * 1000);
            String clientIpAddress = clientIncoming.getInetAddress().toString().substring(1);
            System.out.println("SERVER: New client accepted:\tIPAddress: " + clientIpAddress + "\tPort: " + clientIncoming.getPort());
            String threadID = clientIpAddress + "@" + clientIncoming.getPort();
            SantoriniServerClientHandler handler = new SantoriniServerClientHandler(clientIncoming, threadID, pingStamp);

            //Starts a new thread to handle this client
            Thread t = new Thread(handler, "santorini_server_" + threadID);
            t.start();
            System.out.println("SERVER: Client " + clientIpAddress + " in thread: " + threadID);
        }


    }


}
