package edu.ntnu.idi.idattx2002.Modules.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;

import java.util.Collections;
import java.util.HashMap;


public class Board {

  private final HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public HashMap<Integer, Tile> getTiles() {
    return tiles;
  }

  public Tile getTile(int tileId) {
    Tile tile = tiles.get(tileId);
    if (tile == null) {
      throw new IllegalArgumentException("Tile with id " + tileId + " does not exist");
    }
    return tile;
  }

  public Tile getLastTile() {
    int lastTileID = Collections.max(tiles.keySet());
    return tiles.get(lastTileID);
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);



    for (int i = 1; i < numberOfRows*numberOfColumns+1; i++){
           addTile(new Tile(i));
    }

  }

  public void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
