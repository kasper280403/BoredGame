package Modules;

public class LadderAction {

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
}
