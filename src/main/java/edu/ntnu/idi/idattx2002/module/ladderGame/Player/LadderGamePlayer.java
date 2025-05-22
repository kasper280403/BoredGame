package edu.ntnu.idi.idattx2002.module.ladderGame.Player;

import edu.ntnu.idi.idattx2002.module.common.Player.Player;
import edu.ntnu.idi.idattx2002.module.ladderGame.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

public class LadderGamePlayer extends Player {

    private final int playerID;
    private int currentTileId;
    private final List<PlayerObserver> playerObservers;

    public LadderGamePlayer(String playerName, int playerID, int iconID) {
        super(playerName, iconID);
        this.playerID = playerID;
        this.currentTileId = 0;

        playerObservers = new ArrayList<>();
    }

    public int getCurrentTileId(){
        return currentTileId;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void addObserver(PlayerObserver playerObserver) {
        playerObservers.add(playerObserver);
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
        for(PlayerObserver playerObserver : playerObservers) {
            playerObserver.update(this);
        }
    }
}
