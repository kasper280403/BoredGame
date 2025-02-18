package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import java.util.HashMap;


public class Board {

  private HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    for (int j = 0; j < numberOfRows; j++) {
      for (int i = 0; i < numberOfColumns; i++) {
        int tileId = (i+1) + (j * numberOfColumns);
        addTile(new Tile(tileId));
      }
    }
  }
}
