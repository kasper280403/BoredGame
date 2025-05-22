package edu.ntnu.idi.idattx2002.module.ladderGame.board.actions;

import edu.ntnu.idi.idattx2002.module.ladderGame.board.LandAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.player.LadderGamePlayer;

/**
 * Represents a ladder action that moves a player to a different tile.
 *
 * <p>This class is used in ladder-style board games where landing on a tile triggers a jump to a
 * new destination tile.
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class LadderAction implements LandAction {

  private final int destinationTileId;

  /**
   * Constructs a LadderAction with the specified destination tile ID.
   *
   * @param destinationTileId the ID of the tile to move the player to; must be greater than zero
   * @throws IllegalArgumentException if {@code destinationTileId} is less than 1
   */
  public LadderAction(int destinationTileId) {
    validateDestinationTileId(destinationTileId);
    this.destinationTileId = destinationTileId;
  }

  private void validateDestinationTileId(int destinationTileId) {
    if (destinationTileId < 1) {
      throw new IllegalArgumentException("destinationTile has to be greater than zero");
    }
  }

  /**
   * Returns the destination tile ID associated with this ladder action.
   *
   * @return the destination tile ID
   */
  public int getDestinationTileId() {
    return destinationTileId;
  }

  /**
   * Performs the ladder action, moving the given player to the destination tile.
   *
   * @param player the player to move
   */
  @Override
  public void perform(LadderGamePlayer player) {
    player.movePlayerToTile(this.destinationTileId);
  }
}
