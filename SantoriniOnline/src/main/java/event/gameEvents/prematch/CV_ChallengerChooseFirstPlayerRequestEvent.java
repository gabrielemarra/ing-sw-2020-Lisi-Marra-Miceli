package event.gameEvents.prematch;

import event.core.EventListener;
import event.gameEvents.GameEvent;

import java.util.List;

public class CV_ChallengerChooseFirstPlayerRequestEvent extends GameEvent {

    private String challenger;
    private List<String> players;

    public CV_ChallengerChooseFirstPlayerRequestEvent(String description, String challenger, List<String> players) {
        super(description);
        this.challenger = challenger;
        this.players = players;
    }

    public String getChallenger() {
        return challenger;
    }

    public List<String> getPlayers() {
        return players;
    }

    @Override
    public void notifyHandler(EventListener listener) {
        listener.handleEvent(this);
    }
}