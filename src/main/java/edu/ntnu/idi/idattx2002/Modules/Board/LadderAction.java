package edu.ntnu.idi.idattx2002.Modules.Board;

import edu.ntnu.idi.idattx2002.Modules.Player.Player;

public class LadderAction implements TileAction {

  private final int destinationTileId;

  public LadderAction (int destinationTileId) {
    validateDestinationTileId(destinationTileId);
    this.destinationTileId = destinationTileId;
  }

  private void validateDestinationTileId(int destinationTileId) {
    if (destinationTileId < 0) {
      throw new IllegalArgumentException("destinationTile has to be greater than zero");
    }
  }

  @Override
  public void perform(Player player) {
    player.movePlayerToTile(this.destinationTileId);
  }
}
