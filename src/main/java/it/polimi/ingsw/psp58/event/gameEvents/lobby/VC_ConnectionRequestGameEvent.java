package it.polimi.ingsw.psp58.event.gameEvents.lobby;

import it.polimi.ingsw.psp58.event.core.ControllerListener;
import it.polimi.ingsw.psp58.event.gameEvents.ControllerGameEvent;

/**
 * Event sent by the client to the server to request a connection.
 * Contains the proposed username, the ip address and the port.
 */
public class VC_ConnectionRequestGameEvent extends ControllerGameEvent {

    private final String IP;
    private final int port;
    private final String username;

    public VC_ConnectionRequestGameEvent(String description, String IP, int port, String username) {
        super(description);
        this.IP = IP;
        this.port=port;
        this.username = username;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public void notifyHandler(ControllerListener listener) {
        listener.handleEvent(this);
    }
}
