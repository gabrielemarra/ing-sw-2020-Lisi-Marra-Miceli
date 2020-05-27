package it.polimi.ingsw.psp58.view.UI.GUI.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.psp58.auxiliary.CellClusterData;
import it.polimi.ingsw.psp58.auxiliary.IslandData;
import it.polimi.ingsw.psp58.event.gameEvents.ControllerGameEvent;
import it.polimi.ingsw.psp58.event.gameEvents.match.*;
import it.polimi.ingsw.psp58.event.gameEvents.prematch.CV_PlayerPlaceWorkerRequestEvent;
import it.polimi.ingsw.psp58.event.gameEvents.prematch.CV_WaitPreMatchGameEvent;
import it.polimi.ingsw.psp58.event.gamephase.CV_WorkerPlacementGameEvent;
import it.polimi.ingsw.psp58.model.CardEnum;
import it.polimi.ingsw.psp58.model.TurnAction;
import it.polimi.ingsw.psp58.model.WorkerColors;
import it.polimi.ingsw.psp58.model.gamemap.BlockTypeEnum;
import it.polimi.ingsw.psp58.model.gamemap.Worker;
import it.polimi.ingsw.psp58.view.UI.GUI.BoardPopUp;
import it.polimi.ingsw.psp58.view.UI.GUI.GUI;
import it.polimi.ingsw.psp58.view.UI.GUI.Message;
import it.polimi.ingsw.psp58.view.UI.GUI.boardstate.*;
import it.polimi.ingsw.psp58.view.UI.GUI.controller.exceptions.WorkerLockedException;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.psp58.view.UI.GUI.boardstate.GameStateEnum.*;

class WorkerGlow {
    int x, y;
    Worker.IDs id;

    public WorkerGlow(int x, int y, Worker.IDs id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Worker.IDs getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class BoardSceneController {

    private WorkerGlow currentGlow = null;
    private WorkerColors myColor;
    private String myUsername = "";
    private GUI gui;

    private IslandData lastIslandUpdate;
    private StackPane[][] lastGridPane;

    //STATE PATTERN
    private WorkerStatus workerStatus;
    private GameStateAbstract currentStateInstance;
    public GridPane board;

    public Node workerSelectedNode;

    public VBox cardInfo1;
    public VBox cardInfo2;
    public VBox cardInfo3;

    private List<VBox> cardInfoList;

    public ImageView cardImage1;
    public ImageView cardImage2;
    public ImageView cardImage3;

    private List<ImageView> cardImagesList;

    public Label player1;
    public Label player2;
    public Label player3;

    public HBox workerPrePlaceHBox;

    private List<Label> playersList;

    //ACTION BUTTONS
    public Button moveButton, buildButton, passButton;

    //BLOCKS
    public HBox L1Box, L2Box, L3Box, DomeBox;

    //WORKERS
    public ImageView workerSlotA;
    public ImageView workerSlotB;

    //TURN SEQUENCE
    public Label turnSequence;


    public void init(CV_WorkerPlacementGameEvent event, String myUsername) {
        initializeIsland();

        updateTurnSequence(event.getTurnSequence());
        this.myUsername = myUsername;
        myColor = event.getPlayerWorkerColors().get(myUsername.toLowerCase());

        String url = getWorkerUrl(myColor);

        workerSlotA.setImage(new Image(url));
        workerSlotB.setImage(new Image(url));

        resetTurnStatus();


        //update blocks counter to starting state
        updateBlocksCounter(22, 18, 14, 18);
    }


    /* ----------------------------------------------------------------------------------------------
                                         BOARD CLICK
       ----------------------------------------------------------------------------------------------*/

//    public void onClickEventCellClusterEX(MouseEvent mouseEvent) {
//        if (!currentStateInstance.getState().equals(NOT_YOUR_TURN)) {
//
//            StackPane source = (StackPane) mouseEvent.getSource();
//            Integer colIndex = GridPane.getColumnIndex(source);
//            Integer rowIndex = GridPane.getRowIndex(source);
//            System.out.printf("Mouse clicked cell [%d, %d]%n", colIndex, rowIndex);
//
//            //gets the id of the clicked worker
//            Worker.IDs workerID = getWorkerID(colIndex, rowIndex);
//
//            ControllerGameEvent event = currentStateInstance.handleClick(myUsername, colIndex, rowIndex, workerStatus.getSelectedWorker(), currentState);
//
//            if (currentStateInstance instanceof CommandGameState) { //when it's my turn and I have to answer with a command request
//                if (workerID == null) {
//                    if (!getWorkerStatus().isAlreadySelectedWorker()) {  //your turn, no worker selected
//                        getWorkerStatus().deleteSelectedWorker();
//                        displayMessage("please select a valid worker");
//                    }
//                } else {
//
//                    if (!getWorkerStatus().isAlreadySelectedWorker()) {
//                        if (lastIslandUpdate.getCellCluster(colIndex, rowIndex).getWorkerColor().equals(myColor)) {
//                            setWorkerGlow(true, colIndex, rowIndex);
//                            System.out.println("DEBUG: worker set, glow set");
//                            currentGlow = new WorkerGlow(colIndex, rowIndex, workerID);
//                            getWorkerStatus().deleteSelectedWorker();
//                            try {
//                                getWorkerStatus().setSelectedWorker(workerID);
//                            } catch (WorkerLockedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//
//                }
//            }
//
//            if (event != null) {
//                if (event instanceof VC_PlayerCommandGameEvent) {
//                    if (isCommandEventValid((VC_PlayerCommandGameEvent) event)) {
//                        gui.sendEvent(event);
//                    } else {
//                        //restore turn status
//                        System.out.println("Something in the Event was wrong");
//                    }
//                } else {
//                    gui.sendEvent(event);
//                }
//               /* if (currentState == MOVE || currentState == BUILD) {
//                    alreadyMadeAMoveThisTurn = true;
//                } */
//            }
//
//
//
//
//            /*
//            if ((workerID != null || workerOnAction != null)) {
//                if (workerOnAction == null) {
//                    setWorkerOnAction(workerID);
//                    showPossibleBlockAction(workerID);
//                } else {
//                    ControllerGameEvent event = currentStateInstance.handleClick(gui.getUsername(), colIndex, rowIndex, workerOnAction, currentState);
//                    if (event != null) {
//                        gui.sendEvent(event);
//                        if (currentState == MOVE || currentState == BUILD) {
//                            alreadyMadeAMoveThisTurn = true;
//                        }
//                    }
//                }
//            } */
//        }
//    }

    public void onClickEventCellCluster(MouseEvent mouseEvent) {
        StackPane source = (StackPane) mouseEvent.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse clicked cell [%d, %d]%n", colIndex, rowIndex);
        currentStateInstance.handleClickOnButton(colIndex, rowIndex);
    }

    /* ----------------------------------------------------------------------------------------------
                                         BUTTONS CLICK
       ----------------------------------------------------------------------------------------------*/

//    public void moveButtonClick() {
//        moveButton.setDisable(true);
//        currentState = MOVE;
//    }
//
//    public void buildButtonClick() {
//        buildButton.setDisable(true);
//        currentState = BUILD;
//    }
//
//    public void passButtonClick() {
//        gui.sendEvent(new VC_PlayerCommandGameEvent("", TurnAction.PASS, myUsername, null, null, null));
//        disableAllActionButtons();
//        currentState = NOT_YOUR_TURN;
//        currentGlow = null;
//        resetTurnStatus();
//    }

    public void moveButtonClick() {
        currentStateInstance.handleClickOnButton(TurnAction.MOVE);
    }

    public void buildButtonClick() {
        currentStateInstance.handleClickOnButton(TurnAction.BUILD);

    }

    public void passButtonClick() {
        currentStateInstance.handleClickOnButton(TurnAction.PASS);
    }

    /* ----------------------------------------------------------------------------------------------
                                         BLOCKS CLICK
       ----------------------------------------------------------------------------------------------*/

    public void level1Click() {
        currentStateInstance.handleClickOnButton(BlockTypeEnum.LEVEL1);
    }

    public void level2Click() {
        currentStateInstance.handleClickOnButton(BlockTypeEnum.LEVEL2);
    }

    public void level3Click() {
        currentStateInstance.handleClickOnButton(BlockTypeEnum.LEVEL3);
    }

    public void domeClick() {
        currentStateInstance.handleClickOnButton(BlockTypeEnum.DOME);
    }

    /* ----------------------------------------------------------------------------------------------
                                         STATUS METHODS
       ----------------------------------------------------------------------------------------------*/

    //STATUS
    public void resetTurnStatus() {
        workerStatus = new WorkerStatus();
    }

    public WorkerStatus getWorkerStatus() {
        return workerStatus;
    }

    public void setWaitingView() {
        disableAllActionButtons();
    }

    public void hideWorkerPlacementBox() {
        //workerPrePlaceHBox.setVisible(false);
    }

    public void handle(CV_PlayerPlaceWorkerRequestEvent event) {
        GameStateAbstract nextState = new PlaceWorkerGameState(event, gui, this);
        try {
            workerStatus.setSelectedWorker(event.getWorkerToPlace());
        } catch (WorkerLockedException e) {
            e.printStackTrace();
        }

        setLastIslandUpdate(event.getIsland());
        updateIsland(event.getIsland());
        handleWorkerPlacement(event.getWorkerToPlace());

        this.currentStateInstance = nextState;
    }

    public void handle(CV_WorkerPlacementGameEvent event) {
        GameStateAbstract nextState = new WaitGameState(gui);
        setWaitingView();
        this.currentStateInstance = nextState;
    }

    public void handle(CV_WaitPreMatchGameEvent event) {
        GameStateAbstract nextState = new WaitGameState(event, gui);
        setWaitingView();
        this.currentStateInstance = nextState;
    }

    public void handle(CV_CommandRequestEvent event) {
        enableActionButtons(event.getAvailableActions());
    }

    public void handle(CV_NewTurnEvent event) {
        updateTurnSequence(event.getTurnRotation());

        if (event.getCurrentPlayerUsername().equals(myUsername)) {
            currentStateInstance = new CommandGameState(event, gui, this);
            resetTurnStatus();
            displayMessage("IT'S YOUR TURN!");
        }
    }

    public void handle(CV_GameStartedGameEvent event) {
        GameStateAbstract nextState = new WaitGameState(gui);
        setWaitingView();
        this.currentStateInstance = nextState;
        displayMessage("Game is starting!");
    }

    public void handleWorkerPlacement(Worker.IDs workerRequested) {
        disableAllActionButtons();
        switch (workerRequested) {

            case A:
                workerSlotA.setEffect(new Glow(0.8));
                BoardPopUp.show("Please place worker " + workerRequested.toString(), gui.getStage());
                break;
            case B:
                workerSlotB.setEffect(new Glow(0.8));
                BoardPopUp.show("Please place worker " + workerRequested.toString(), gui.getStage());
                break;
        }
    }

    /* ----------------------------------------------------------------------------------------------
                                         ACTION BUTTONS METHODS
       ----------------------------------------------------------------------------------------------*/

    public void disableAllActionButtons() {
        moveButton.setDisable(true);
        buildButton.setDisable(true);
        passButton.setDisable(true);
    }

    public void enableActionButtons(List<TurnAction> availableActions) {
        for (TurnAction action : availableActions) {
            switch (action) {
                case MOVE:
                    moveButton.setDisable(false);
                    break;
                case BUILD:
                    buildButton.setDisable(false);
                    break;
                case PASS:
                    passButton.setDisable(false);
                    break;
            }
        }
    }

    /* ----------------------------------------------------------------------------------------------
                                         BUILDING BLOCKS METHODS
       ----------------------------------------------------------------------------------------------*/

    private void updateBlocksCounter(int lev1, int lev2, int lev3, int dome) {
        ((Label) L1Box.getChildren().get(1)).setText(Integer.toString(lev1));
        ((Label) L2Box.getChildren().get(1)).setText(Integer.toString(lev2));
        ((Label) L3Box.getChildren().get(1)).setText(Integer.toString(lev3));
        ((Label) DomeBox.getChildren().get(1)).setText(Integer.toString(dome));
    }

    /* ----------------------------------------------------------------------------------------------
                                         TURN SEQUENCE METHODS
       ----------------------------------------------------------------------------------------------*/

    public void updateTurnSequence(Map<String, CardEnum> turnSequenceFromEvent) {

        //fill turn sequence
        Map<String, CardEnum> sequence = turnSequenceFromEvent;
        List<String> turnSequence = new ArrayList<>();
        for (String player : sequence.keySet()) {
            turnSequence.add(player);
        }
        updateTurnSequence(turnSequence);
    }

    public void updateTurnSequence(List<String> sequence) {
        String turnSequenceText = "";
        boolean first = true;
        for (String s : sequence) {
            if (first) {
                turnSequenceText += " ";
                first = false;
            } else {
                turnSequenceText += " << ";
            }
            turnSequenceText += s.toUpperCase();
        }
        turnSequence.setText(turnSequenceText);
    }

     /* ----------------------------------------------------------------------------------------------
                                         ISLAND METHODS
       ----------------------------------------------------------------------------------------------*/

    public void handle(CV_IslandUpdateEvent event) {
        updateIsland(event.getNewIsland());
    }

    public IslandData getLastIslandUpdate() {
        return lastIslandUpdate;
    }

    public void updateIsland(String islandDataJSON) {
        IslandData island = islandDataFromJson(islandDataJSON);

        setLastIslandUpdate(island);

        String url = "";
        Platform.runLater(() -> {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    CellClusterData cellClusterData = island.getCellCluster(x, y);
                    StackPane stackPane = new StackPane();
                    Pane pane = new Pane();
                    Pane upperPane = new Pane();
                    upperPane.setVisible(false);

                    //if there are construction blocks adds the image
                    if (cellClusterData.getBlocks() != null && (cellClusterData.getBlocks().length > 0 || cellClusterData.isDomeOnTop())) {
                        pane.getChildren().add(getBlockImage(cellClusterData));
                    }

                    //if there is a worker adds the image
                    if (cellClusterData.getWorkerOnTop() != null) {
                        pane.getChildren().add(getWorkerImage(cellClusterData));


                    }
                    stackPane.getChildren().addAll(pane, upperPane);
                    stackPane.setOnMouseClicked(this::onClickEventCellCluster);

                    GridPane.setConstraints(stackPane, x, y);

                    board.getChildren().remove(lastGridPane[x][y]);
                    board.getChildren().add(stackPane);

                    lastGridPane[x][y] = stackPane;

                    if (cellClusterData.getWorkerOnTop() != null) {
                        if (currentGlow != null && cellClusterData.getWorkerColor().equals(myColor) && currentGlow.getId().equals(cellClusterData.getWorkerOnTop())) {
                            setWorkerGlow(true, x, y);

                            System.out.println("DEBUG: RESTORING GLOW");
                        }
                    }
                }
            }
        });

    }

    public void setLastIslandUpdate(IslandData lastIslandUpdate) {
        this.lastIslandUpdate = lastIslandUpdate;
    }

    public void setLastIslandUpdate(String lastIslandUpdate) {
        this.lastIslandUpdate = islandDataFromJson(lastIslandUpdate);
    }

    public void initializeIsland() {
        lastGridPane = new StackPane[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                StackPane stackPane = new StackPane();
                Pane pane = new Pane();
                Pane upperPane = new Pane();
                upperPane.setVisible(false);

                stackPane.getChildren().addAll(pane, upperPane);
                stackPane.setOnMouseClicked(this::onClickEventCellCluster);
                GridPane.setConstraints(stackPane, y, x); //node, column, row

                board.getChildren().add(stackPane);

                lastGridPane[x][y] = stackPane;
            }
        }
    }

    public ImageView getBlockImage(CellClusterData cellClusterData) {
        String url = getUrlFromCellCluster(cellClusterData);

        ImageView image = new ImageView();
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setPreserveRatio(true);
        image.setImage(new Image(url));
        return image;
    }

    public void activateGlowOnPanels(List<int[]> panelPositions) {

//        for (int[] position : panelPositions) {
//            StackPane stackPane = (StackPane) getNodeByRowColumnIndex(position[0], position[1]);
//            if (currentState == MOVE) {
//                Pane pane = (Pane) stackPane.getChildren().get(1);
//                pane.setVisible(true);
//                pane.setStyle("-fx-background-color: #00FFFF");
//            }
//            if (currentState == BUILD) {
//                stackPane.getChildren().get(1).setVisible(true);
//                stackPane.getChildren().get(1).setStyle("-fx-background-color: #A52A2A");
//            }
//            board.getChildren().remove(position[0], position[1]);
//            board.add(stackPane, position[0], position[1]);
//        }
    }

    public String getUrlFromCellCluster(CellClusterData cellClusterData) {
        String url = "/images/cellcluster/";

        int[] blocks = cellClusterData.getBlocks();
        boolean domeOnTop = cellClusterData.isDomeOnTop();
        Worker.IDs workerID = cellClusterData.getWorkerOnTop();
        WorkerColors color = cellClusterData.getWorkerColor();

        //construct the url of the image
        if ((blocks.length == 1 && blocks[0] == 4)) {
            if (domeOnTop) url = url + "L0_DOME.png";
            return url;
        } else {
            if (blocks[blocks.length - 1] == 4) {
                blocks = Arrays.copyOfRange(blocks, 0, blocks.length - 1);
            }
            switch (blocks[blocks.length - 1]) {
                case 1: {
                    url = url + "L1";
                    break;
                }
                case 2: {
                    url = url + "L2";
                    break;
                }
                case 3: {
                    url = url + "L3";
                    break;
                }
            }
            if (domeOnTop) {
                url = url + "_DOME";
            }
            url = url + ".png";
        }
        return url;
    }

    /*public void showPossibleBlockAction(Worker.IDs workerID) {
        if (currentState != MOVE && currentState != BUILD) {
            Message.show("BEFORE SELECT AN ACTION FROM THE BUTTONS BELOW", gui.getStage());
        } else {
            CV_CommandRequestEvent event = (CV_CommandRequestEvent) currentStateInstance.getEvent();
            if (currentState == MOVE) {
                switch (workerID) {
                    case A:
                        activateGlowOnPanels(event.getAvailableMovementBlocksA());
                        break;
                    case B:
                        activateGlowOnPanels(event.getAvailableMovementBlocksB());
                        break;
                }
            } else if (currentState == BUILD) {
                switch (workerID) {
                    case A:
                        activateGlowOnPanels(event.getAvailableBuildBlocksA());
                        break;
                    case B:
                        activateGlowOnPanels(event.getAvailableBuildBlocksB());
                        break;
                }
            }
        }

    }*/

    //utility to get a cell from the board
    public Node getNodeByRowColumnIndex(final int row, final int column) {

        return lastGridPane[row][column];
//        Node result = null;
//        ObservableList<Node> children = board.getChildren();
//
//        for (Node node : children) {
//            if (node instanceof StackPane && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
//                result = node;
//                break;
//            }
//        }
//
//        return result;
    }

    private IslandData islandDataFromJson(String islandJson) {
        //display island
        Gson gson = new Gson();
        return gson.fromJson(islandJson, IslandData.class);
    }

    /* ----------------------------------------------------------------------------------------------
                                         UTILITY METHODS
       ----------------------------------------------------------------------------------------------*/

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * Displays a message to the user with a popup panel
     *
     * @param message the message you would like to display
     */
    public void displayMessage(String message) {
        //TODO messaggio sotto e non popup
        BoardPopUp.show(message.toUpperCase(), gui.getStage());
    }


    private boolean isCommandEventValid(VC_PlayerCommandGameEvent commandEvent) {
        return commandEvent.isCommandEventValid() && myUsername.equals(commandEvent.getFromPlayer());
    }

    /* ----------------------------------------------------------------------------------------------
                                         WORKER UTILITY AND METHODS
       ----------------------------------------------------------------------------------------------*/

    private String getWorkerUrl(WorkerColors color) {
        String url = "/images/cellcluster/";
        switch (color) {
            case WHITE:
                url = url + "W_PINK.png";
                break;
            case BLUE:
                url = url + "W_BLUE.png";
                break;
            case BEIGE:
                url = url + "W_ORANGE.png";
                break;
        }
        return url;
    }

    public void setWorkerGlow(boolean active, int x, int y) {
        Node point = getNodeByRowColumnIndex(x, y);
        if (active) {

            //point.setEffect(new Glow(0.7));
            System.out.println("DEBUG: setWorkerGlow on " + x + " " + y);
            workerSelectedNode = point;

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(14.5);
            dropShadow.setWidth(30);
            dropShadow.setHeight(30);
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            dropShadow.setSpread(0.6);
            dropShadow.setColor(Color.rgb(255, 234, 5));
            point.setEffect(dropShadow);
        } else {
            point.setEffect(null);
            point = null;
        }
    }

    public void setWorkerGlow(boolean active, Worker.IDs workerID) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                CellClusterData cellClusterData = lastIslandUpdate.getCellCluster(i, j);
                if (cellClusterData.getUsernamePlayer().equals(myUsername) && cellClusterData.getWorkerOnTop().equals(workerID)) {
                    setWorkerGlow(active, i, j);
                }
            }
        }
    }

    public Worker.IDs getWorkerID(int x, int y) {
        return lastIslandUpdate.getCellCluster(x, y).getWorkerOnTop();
    }

    public ImageView getWorkerImage(CellClusterData cellClusterData) {
        String url = getWorkerUrl(cellClusterData.getWorkerColor());
        ImageView workerImage = new ImageView();
        workerImage.setFitWidth(100);
        workerImage.setFitHeight(100);
        workerImage.setPreserveRatio(true);
        workerImage.setImage(new Image(url));
        return workerImage;
    }

    //    private void initializePlayersList(){
//        playersList = new ArrayList<>();
//        playersList.add(player1);
//        playersList.add(player2);
//        playersList.add(player3);
//    }
//
//    private void initializeCardImages(){
//        cardImagesList = new ArrayList<>();
//        cardImagesList.add(cardImage1);
//        cardImagesList.add(cardImage2);
//        cardImagesList.add(cardImage3);
//
//    }
//
//    private void initializeCardInfo(){
//        cardInfoList = new ArrayList<>();
//        cardInfoList.add(cardInfo1);
//        cardInfoList.add(cardInfo2);
//        cardInfoList.add(cardInfo3);
//    }
//
//    public void setUpPlayersCardsCorrespondence(Map<String, CardEnum> playersCardsCorrespondence){
//        int index = 0;
//        for (Map.Entry<String, CardEnum> entry : playersCardsCorrespondence.entrySet()) {
//            playersList.get(index).setText(entry.getKey());
//            cardImagesList.get(index).setImage(new Image(entry.getValue().getImgUrl()));
//
//            //set up the card info
//            ObservableList<Node> children = cardInfoList.get(index).getChildren();
//            //set up the card name
//            Label cardName = (Label) children.get(0);
//            cardName.setText(entry.getValue().getName());
//            //set up the card description
//            Label cardDescription = (Label) children.get(1);
//            cardDescription.setText(entry.getValue().getDescription());
//
//            index ++ ;
//        }
//
//    }

}
