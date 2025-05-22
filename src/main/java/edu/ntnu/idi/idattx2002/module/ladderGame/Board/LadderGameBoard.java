package edu.ntnu.idi.idattx2002.module.ladderGame.Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the game board for a ladder game.
 * <p>
 * Manages a collection of {@code LadderGameSquare} tiles mapped by their IDs,
 * and provides methods to add, retrieve, and generate tiles for the board.
 * </p>
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class LadderGameBoard {

  private final Map<Integer, LadderGameSquare> squareMap;

  /**
   * Constructs an empty ladder game board.
   */
  public LadderGameBoard() {
    this.squareMap = new HashMap<>();
  }

  /**
   * Returns the internal map of square IDs to their corresponding tiles.
   *
   * @return the map of tiles
   */
  public Map<Integer, LadderGameSquare> getSquareMap() {
    return squareMap;
  }


  /**
   * Retrieves a tile with the given ID.
   *
   * @param tileId the ID of the tile to retrieve
   * @return the tile corresponding to the given ID
   * @throws IllegalArgumentException if the tile does not exist
   */
  public LadderGameSquare getTile(int tileId) {
    LadderGameSquare tile = squareMap.get(tileId);
    if (tile == null) {
      throw new IllegalArgumentException("Tile with id " + tileId + " does not exist");
    }
    return tile;
  }

  /**
   * Returns the last tile on the board (tile with the highest ID).
   *
   * @return the last tile
   */
  public LadderGameSquare getLastTile() {
    int lastTileID = Collections.max(squareMap.keySet());
    return squareMap.get(lastTileID);
  }

  /**
   * Adds a tile to the board.
   *
   * @param tile the tile to add
   */
  public void addTile(LadderGameSquare tile) {
    squareMap.put(tile.getSquareId(), tile);
  }

  /**
   * Creates a grid-based board layout by generating the specified number of rows and columns.
   *
   * @param numberOfRows the number of rows in the board
   * @param numberOfColumns the number of columns in the board
   * @throws IllegalArgumentException if either rows or columns are less than 1
   */
  public void createBoard(int numberOfRows, int numberOfColumns) {
    validateRowsAndColumns(numberOfRows, numberOfColumns);

    for (int i = 1; i < numberOfRows*numberOfColumns+1; i++){
           addTile(new LadderGameSquare(i));
    }
  }

  /**
   * Validates that the given number of rows and columns are greater than zero.
   *
   * @param numberOfRows the number of rows
   * @param numberOfColumns the number of columns
   * @throws IllegalArgumentException if either value is less than 1
   */
  private void validateRowsAndColumns(int numberOfRows, int numberOfColumns) {
    if (numberOfColumns < 1 || numberOfRows < 1) {
      throw new IllegalArgumentException("Rows and columns has to be greater then zero");
    }
  }
}
