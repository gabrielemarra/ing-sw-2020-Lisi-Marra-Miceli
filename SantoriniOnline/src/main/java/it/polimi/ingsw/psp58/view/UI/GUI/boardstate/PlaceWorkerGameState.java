package it.polimi.ingsw.psp58.view.UI.GUI.boardstate;

import com.google.gson.Gson;
import it.polimi.ingsw.psp58.auxiliary.IslandData;
import it.polimi.ingsw.psp58.event.gameEvents.ControllerGameEvent;
import it.polimi.ingsw.psp58.event.gameEvents.ViewGameEvent;
import it.polimi.ingsw.psp58.event.gameEvents.prematch.CV_PlayerPlaceWorkerRequestEvent;
import it.polimi.ingsw.psp58.event.gameEvents.prematch.VC_PlayerPlacedWorkerEvent;
import it.polimi.ingsw.psp58.model.TurnAction;
import it.polimi.ingsw.psp58.model.gamemap.BlockTypeEnum;
import it.polimi.ingsw.psp58.model.gamemap.Worker;
import it.polimi.ingsw.psp58.view.UI.GUI.GUI;
import it.polimi.ingsw.psp58.view.UI.GUI.controller.BoardSceneController;

public class PlaceWorkerGameState extends GameStateAbstract {
    private CV_PlayerPlaceWorkerRequestEvent eventArrived;
    private final GUI gui;

    private final GameStateEnum state = GameStateEnum.PLACE_WORKER;

    public PlaceWorkerGameState(CV_PlayerPlaceWorkerRequestEvent eventArrived, GUI gui) {
        this.eventArrived = eventArrived;
        this.gui=gui;
    }

    @Override
    public void setState(BoardSceneController boardController) {
        boardController.setLastIslandUpdate(eventArrived.getIsland());
        boardController.updateIsland(eventArrived.getIsland());
        boardController.handleWorkerPlacement(eventArrived.getWorkerToPlace());
    }

    @Override
    public ControllerGameEvent handleClick(String username, int x, int y, Worker.IDs workerID, GameStateEnum state) {
        return new VC_PlayerPlacedWorkerEvent("", username, x, y, workerID);
    }

    @Override
    public void handleClickOnButton(int x, int y) {
        System.out.println("Place Worker State Handle Click On Board - Not yet implemented");
        //place the worker
    }

    @Override
    public void handleClickOnButton(TurnAction buttonPressed) {
        System.out.println("Place Worker State Handle Click On Button - ERROR");
        //PRINT ERROR
    }

    @Override
    public void handleClickOnButton(BlockTypeEnum blockClicked) {
        System.out.println("Place Worker State Handle Click On Button - ERROR");
        //PRINT ERROR
    }

    @Override
    public ViewGameEvent getEvent() {
        return eventArrived;
    }

    @Override
    public GameStateEnum getState() {
        return state;
    }



}
