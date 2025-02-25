package edu.ntnu.idi.idattx2002.Modules.Player;

public class Player {
    String playerName;
    int playerID;
    int pieceID;
    int currentTileId = 0;

    public Player(String playerName, int playerID, int pieceID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.pieceID = pieceID;
    }

    public void movePlayerBySteps(int numberOfSteps){
        currentTileId += numberOfSteps;
    }

    public void movePlayerToTile(int tileId) {
        currentTileId = tileId;
    }

    public int getCurrentTile(){
        return currentTileId;
    }

    public int getPlayerID() {
        return playerID;
    }
}
