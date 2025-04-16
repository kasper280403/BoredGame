package edu.ntnu.idi.idattx2002.logic.Player;

import edu.ntnu.idi.idattx2002.logic.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    int playerID;
    int pieceID;
    int currentTileId;
    List<PlayerObserver> playerObservers;

    public Player(String playerName, int playerID, int pieceID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.pieceID = pieceID;
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

    public int getCurrentTile(){
        return currentTileId;
    }

    public int getPieceID() {
        return pieceID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
