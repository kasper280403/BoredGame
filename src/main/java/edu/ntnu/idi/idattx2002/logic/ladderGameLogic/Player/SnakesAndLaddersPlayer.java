package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player;

import edu.ntnu.idi.idattx2002.logic.common.Player.Player;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

public class SnakesAndLaddersPlayer extends Player {
    int playerID;
    int currentTileId;
    List<PlayerObserver> playerObservers;

    public SnakesAndLaddersPlayer(String playerName, int playerID, int iconID) {
        super(playerName, iconID);
        this.playerID = playerID;
        this.currentTileId = 0;

        playerObservers = new ArrayList<>();
    }

    public void addObserver(PlayerObserver playerObserver) {
        playerObservers.add(playerObserver);
        System.out.println("Added observer");
    }

    public void movePlayerBySteps(int numberOfSteps){
        currentTileId += numberOfSteps;
        notifyObservers();
    }

    public void movePlayerToTile(int tileId) {
        currentTileId = tileId;
        notifyObservers();
    }

    private void notifyObservers() {
        System.out.println("Entered notify observers");
        for(PlayerObserver playerObserver : playerObservers) {
            playerObserver.update(this);
        }
    }

    public int getCurrentTileId(){
        return currentTileId;
    }

    public int getPlayerID() {
        return playerID;
    }

}
