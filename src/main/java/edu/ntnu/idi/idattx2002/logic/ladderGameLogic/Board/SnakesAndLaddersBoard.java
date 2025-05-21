package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SnakesAndLaddersBoard {

  private final Map<Integer, SnakesAndLaddersSquare> squareMap;

  public SnakesAndLaddersBoard() {
    this.squareMap = new HashMap<>();
  }

  public Map<Integer, SnakesAndLaddersSquare> getSquareMap() {
    return squareMap;
  }

  public SnakesAndLaddersSquare getTile(int tileId) {
    SnakesAndLaddersSquare tile = squareMap.get(tileId);
    if (tile == null) {
      throw new IllegalArgumentException("Tile with id " + tileId + " does not exist");
    }
    return tile;
  }

  public SnakesAndLaddersSquare getLastTile() {
    int lastTileID = Collections.max(squareMap.keySet());
    return squareMap.get(lastTileID);
  }

  public void addTile(SnakesAndLaddersSquare tile) {
    squareMap.put(tile.getSquareId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);

    for (int i = 1; i < numberOfRows*numberOfColumns+1; i++){
           addTile(new SnakesAndLaddersSquare(i));
    }
  }

  private void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
