package edu.ntnu.idi.idattx2002.module.ladderGame.Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class LadderGameBoard {

  private final Map<Integer, LadderGameSquare> squareMap;

  public LadderGameBoard() {
    this.squareMap = new HashMap<>();
  }

  public Map<Integer, LadderGameSquare> getSquareMap() {
    return squareMap;
  }

  public LadderGameSquare getTile(int tileId) {
    LadderGameSquare tile = squareMap.get(tileId);
    if (tile == null) {
      throw new IllegalArgumentException("Tile with id " + tileId + " does not exist");
    }
    return tile;
  }

  public LadderGameSquare getLastTile() {
    int lastTileID = Collections.max(squareMap.keySet());
    return squareMap.get(lastTileID);
  }

  public void addTile(LadderGameSquare tile) {
    squareMap.put(tile.getSquareId(), tile);
  }

  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);

    for (int i = 1; i < numberOfRows*numberOfColumns+1; i++){
           addTile(new LadderGameSquare(i));
    }
  }

  private void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
