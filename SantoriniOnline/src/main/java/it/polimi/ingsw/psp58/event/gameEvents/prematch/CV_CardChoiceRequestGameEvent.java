package it.polimi.ingsw.psp58.event.gameEvents.prematch;

import it.polimi.ingsw.psp58.event.core.ViewListener;
import it.polimi.ingsw.psp58.event.gameEvents.ViewGameEvent;
import it.polimi.ingsw.psp58.model.CardEnum;

import java.util.List;

/**
 * sent by the PreGame when a player must choose a card to play the game with
 * the it.polimi.ingsw.sp58.view receiving this it.polimi.ingsw.sp58.event must notify the user and handle a card choice FROM the remaining cards
 * choosingPlayer the player that is choosing the card
 * available cards: the list of cards from which the user must choose his god.

 */
public class CV_CardChoiceRequestGameEvent extends ViewGameEvent {

    private List <CardEnum> availableCards;
    private List <CardEnum> usedCards;
    private String username;

    public CV_CardChoiceRequestGameEvent(String description, List<CardEnum> availableCards, String username) {
        super(description);
        this.availableCards = availableCards;
        this.username=username;
    }
    public CV_CardChoiceRequestGameEvent(String description, List<CardEnum> availableCards, List<CardEnum> usedCards, String username) {
        super(description);
        this.availableCards = availableCards;
        this.username=username;
        this.usedCards=usedCards;
    }

    public List<CardEnum> getAvailableCards() {
        return availableCards;
    }

    public String getUsername() {
        return username;
    }

    public List<CardEnum> getUsedCards() {
        return usedCards;
    }

    @Override
    public void notifyHandler(ViewListener listener) {
        listener.handleEvent(this);
    }
}
