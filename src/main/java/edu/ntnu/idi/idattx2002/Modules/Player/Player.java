package edu.ntnu.idi.idattx2002.Modules.Player;

public class Player {
    String playerName;
    int playerID;
    int pieceID;
    int currentTile = 0;

    public Player(String playerName, int playerID, int pieceID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.pieceID = pieceID;
    }

    public void movePlayerBySteps(int numberOfSteps){
        currentTile += numberOfSteps;
    }

    public void movePlayerToTile(int tileId) {
        currentTile = tileId;
    }

    public int getCurrentTile(){
        return currentTile;
    }
}
