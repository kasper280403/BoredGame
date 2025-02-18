package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import java.util.HashMap;


public class Board {

  private final HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public Tile getTile(int tileId) {
    try {
      return tiles.get(tileId);
    } catch (Exception e) {
      throw new IllegalArgumentException("TileId does not exist");
    }
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);

    for (int j = 0; j < numberOfRows; j++) {
      for (int i = 0; i < numberOfColumns; i++) {
        int tileId = (i+1) + (j * numberOfColumns);
        addTile(new Tile(tileId));
      }
    }
  }
  public void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
