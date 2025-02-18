package edu.ntnu.idi.idattx2002.Modules.Board.Actions;

import edu.ntnu.idi.idattx2002.Modules.Board.LandAction;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;

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

  @Override
  public void perform(Player player) {
    player.movePlayerToTile(this.destinationTileId);
  }
}
