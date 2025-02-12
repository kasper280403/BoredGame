package Modules;

public class Tile {

  private final int tileId;
  //TileAction ...

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
}
