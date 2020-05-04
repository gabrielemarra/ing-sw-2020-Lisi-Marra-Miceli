package it.polimi.ingsw.psp58.event.gameEvents.prematch;

import it.polimi.ingsw.psp58.event.core.EventListener;
import it.polimi.ingsw.psp58.event.gameEvents.GameEvent;
import it.polimi.ingsw.psp58.model.gamemap.Worker;

public class CV_PlayerPlaceWorkerRequestEvent extends GameEvent {
    private String actingPlayer;
    private String islandData;
    private Worker.IDs workerToPlace;

    public CV_PlayerPlaceWorkerRequestEvent(String description, String actingPlayer, String islandData, Worker.IDs worketToPlace) {
        super(description);
        this.actingPlayer = actingPlayer;
        this.workerToPlace = worketToPlace;
        this.islandData = islandData;
    }

    public String getActingPlayer() {
        return actingPlayer;
    }

    @Override
    public void notifyHandler(EventListener listener) {
        listener.handleEvent(this);
    }

    public Worker.IDs getWorkerToPlace() {
        return workerToPlace;
    }

    public String getIsland() {
        return islandData;
    }
}