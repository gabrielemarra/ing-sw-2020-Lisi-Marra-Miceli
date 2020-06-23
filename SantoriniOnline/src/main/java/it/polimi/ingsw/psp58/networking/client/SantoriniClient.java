package it.polimi.ingsw.psp58.networking.client;

import it.polimi.ingsw.psp58.event.core.EventListener;
import it.polimi.ingsw.psp58.event.core.EventSource;
import it.polimi.ingsw.psp58.event.gameEvents.ControllerGameEvent;
import it.polimi.ingsw.psp58.event.gameEvents.connection.PingEvent;
import it.polimi.ingsw.psp58.event.gameEvents.ViewGameEvent;
import it.polimi.ingsw.psp58.view.UI.CLI.utility.MessageUtility;
import it.polimi.ingsw.psp58.view.UI.GUI.GUI;
import javafx.application.Platform;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static it.polimi.ingsw.psp58.event.core.ListenerType.VIEW;


public class SantoriniClient extends EventSource implements Runnable {

    private EventListener userInterface;

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String IP;
    private Socket serverSocket;
    public static final int SERVER_PORT = 7557;
    public final static int SOCKET_TIMEOUT_S = 20;

    private boolean connectionOpen = false;
    private boolean enablePing;

    private GUI guiIstance;



    public SantoriniClient(EventListener userInterface, String ipAddress, boolean enablePing) {
        this.userInterface = userInterface;
        this.IP = ipAddress;
        this.enablePing = enablePing;
        attachListenerByType(VIEW, userInterface);
    }

    public SantoriniClient(EventListener userInterface, String ipAddress, boolean enablePing, GUI guiIstance) {
        this.userInterface = userInterface;
        this.IP = ipAddress;
        this.enablePing = enablePing;
        this.guiIstance = guiIstance;
        attachListenerByType(VIEW, userInterface);
    }

    /**
     * tries to open a socket with the server, the socket has a timeout. This function starts the pinging process too
     * @throws IOException when it's unable to open a socket
     */
    public void begin() throws IOException {

        serverSocket = null;
        //Open a connection with the server
        try {
            serverSocket = new Socket(IP, SERVER_PORT);
            connectionOpen = true;
            if (enablePing) {
                serverSocket.setSoTimeout(SOCKET_TIMEOUT_S * 1000);
            }
        } catch (IOException e) {
            System.err.println("Client: Unable to open a socket");
            throw e;
            ///e.printStackTrace();
        }
        if (enablePing) {
            try {
                InetAddress serverInetAddress = InetAddress.getByName(IP);
          /*  Thread pinger = new Thread(new ClientPing(serverInetAddress), "pinger");
            pinger.start(); */

                Thread ping = new Thread() {
                    public void run() {

                        try {
                            int counter = 0;
                            while (true) {
                                Thread.sleep(5000);
                                out.writeObject(new PingEvent("Ping #" + counter));
                                counter++;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            System.out.println("Unable to send it.polimi.ingsw.sp58.event to server");
                        } finally {
                            Thread.currentThread().interrupt();
                        }
                    }
                };
                ping.start();

            } catch (UnknownHostException e) {
                System.out.println("Unable to convert IP address to InetAddress");
            }
        }

        //open the in/out stream from the server
        try {

            in = new ObjectInputStream(new BufferedInputStream(serverSocket.getInputStream()));
            out = new ObjectOutputStream(serverSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEvent(ControllerGameEvent event) {
        if (connectionOpen) {
            try {
                out.writeObject(event); //event is serializable
                out.flush();
            } catch (IOException e) {
                System.out.println("Unable to send it.polimi.ingsw.sp58.event to server");
            }
        }
    }

    //READS FROM SOCKET
    @Override
    public void run() {
        while (true) {
            try {
                Object received = in.readObject();
                if (!(received instanceof PingEvent)) {
                    ViewGameEvent event = (ViewGameEvent) received;
                    notifyAllObserverByType(VIEW, event);
                }
            } catch (SocketTimeoutException e) {
                MessageUtility.displayErrorMessage("Lost connection with the server");
                connectionOpen = false;
                closeConnection();
                break;
            } catch (IOException | ClassNotFoundException e) {
                MessageUtility.displayErrorMessage("Client: Disconnected from the server");
                connectionOpen = false;
                closeConnection();
                break;
            }
        }
    }

    public boolean isConnectionOpen() {
        return connectionOpen;
    }

    public void closeConnection() {

        try {
            serverSocket.close();
            if (guiIstance != null) {
                Platform.runLater(() -> guiIstance.socketIsClosed("Server socket has been closed."));
            }
        } catch (IOException ex) {
            System.out.println("Error closing the socket and streams.");
        } finally {
            //System.exit(0);
        }
    }
}
