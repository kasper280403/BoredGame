package Modules;

public class Tile {

  private final int tileId;
  private TileAction landAction;

  //TileAction in constructor
  public Tile (int tileId) {
    validateTileId(tileId);

    this.tileId = tileId;
   }

   private void validateTileId(int tileId) {
    if (tileId < 0) {
      throw new IllegalArgumentException("TileId has to be an positive integer");
    }
   }

   public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
   }

  public boolean hasLandAction() {
    return this.landAction != null;
  }

  public void landPlayer(Player player) {
    if (hasLandAction()) {
      landAction.perform(player);
    }
   }
}
