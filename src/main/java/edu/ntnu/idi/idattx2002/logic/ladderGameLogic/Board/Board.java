package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board;

import java.util.Collections;
import java.util.HashMap;


public class Board {

  private final HashMap<Integer, SnakesAndLaddersSquare> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public HashMap<Integer, SnakesAndLaddersSquare> getTiles() {
    return tiles;
  }

  public SnakesAndLaddersSquare getTile(int tileId) {
    SnakesAndLaddersSquare tile = tiles.get(tileId);
    if (tile == null) {
      throw new IllegalArgumentException("Tile with id " + tileId + " does not exist");
    }
    return tile;
  }

  public SnakesAndLaddersSquare getLastTile() {
    int lastTileID = Collections.max(tiles.keySet());
    return tiles.get(lastTileID);
  }

  public void addTile(SnakesAndLaddersSquare tile) {
    tiles.put(tile.getSquareId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);



    for (int i = 1; i < numberOfRows*numberOfColumns+1; i++){
           addTile(new SnakesAndLaddersSquare(i));
    }

  }

  public void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
