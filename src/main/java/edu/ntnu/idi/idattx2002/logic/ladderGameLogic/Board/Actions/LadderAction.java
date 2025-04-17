package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.LandAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;

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
  public void perform(SnakesAndLaddersPlayer player) {
    player.movePlayerToTile(this.destinationTileId);
  }
}
