package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import java.util.HashMap;


public class Board {

  private HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }
}
