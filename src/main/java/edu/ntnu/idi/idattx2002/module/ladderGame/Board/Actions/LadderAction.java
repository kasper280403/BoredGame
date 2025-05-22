package edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions;

import edu.ntnu.idi.idattx2002.module.ladderGame.Board.LandAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;

public class LadderAction implements LandAction {

  private final int destinationTileId;

  public LadderAction (int destinationTileId) {
    validateDestinationTileId(destinationTileId);
    this.destinationTileId = destinationTileId;
  }

  private void validateDestinationTileId(int destinationTileId) {
    if (destinationTileId < 1) {
      throw new IllegalArgumentException("destinationTile has to be greater than zero");
    }
  }

  public int getDestinationTileId() {
    return destinationTileId;
  }

  @Override
  public void perform(LadderGamePlayer player) {
    player.movePlayerToTile(this.destinationTileId);
  }
}
