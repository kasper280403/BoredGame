package edu.ntnu.idi.idattx2002.module.ladderGame.Player;

import edu.ntnu.idi.idattx2002.module.common.Player.Player;
import edu.ntnu.idi.idattx2002.module.ladderGame.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the ladder game with a position on the board and a unique ID.
 * <p>
 * Supports observer registration to notify listeners of movement updates.
 * </p>
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class LadderGamePlayer extends Player {

    private final int playerID;
    private int currentTileId;
    private final List<PlayerObserver> playerObservers;

    /**
     * Constructs a new {@code LadderGamePlayer} with the given name, ID, and icon ID.
     *
     * @param playerName the name of the player
     * @param playerID the unique identifier of the player
     * @param iconID the icon ID associated with the player
     */
    public LadderGamePlayer(String playerName, int playerID, int iconID) {
        super(playerName, iconID);
        this.playerID = playerID;
        this.currentTileId = 0;

        playerObservers = new ArrayList<>();
    }

    /**
     * Returns the current tile ID the player is on.
     *
     * @return the current tile ID
     */
    public int getCurrentTileId(){
        return currentTileId;
    }

    /**
     * Returns the unique identifier of the player.
     *
     * @return the player ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Registers a new observer to be notified when the player's position changes.
     *
     * @param playerObserver the observer to add
     */
    public void addObserver(PlayerObserver playerObserver) {
        playerObservers.add(playerObserver);
    }

    /**
     * Moves the player forward by a specified number of steps and notifies observers.
     *
     * @param numberOfSteps the number of steps to move forward
     */
    public void movePlayerBySteps(int numberOfSteps){
        currentTileId += numberOfSteps;
        notifyObservers();
    }

    /**
     * Moves the player directly to the specified tile ID and notifies observers.
     *
     * @param tileId the tile ID to move the player to
     */
    public void movePlayerToTile(int tileId) {
        currentTileId = tileId;
        notifyObservers();
    }

    /**
     * Notifies all registered observers of the player's current state.
     */
    private void notifyObservers() {
        for(PlayerObserver playerObserver : playerObservers) {
            playerObserver.update(this);
        }
    }
}
