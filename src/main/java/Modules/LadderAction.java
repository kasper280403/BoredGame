package Modules;

public class LadderAction implements TileAction {

  //TODO Implementere spiller Actions

  private int destinationTileId;

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
    //player.goToTile, eller lignende
  }
}
