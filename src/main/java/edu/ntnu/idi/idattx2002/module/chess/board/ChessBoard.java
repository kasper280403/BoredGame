package edu.ntnu.idi.idattx2002.module.chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an 8x8 chess board composed of {@code ChessSquare} instances.
 * <p>
 * Provides methods for retrieving squares, checking for clear paths, and computing movement paths.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class ChessBoard {

  private final Map<Integer, ChessSquare> squareMap;

  /**
   * Constructs a new chess board and initializes all squares.
   */
  public ChessBoard() {
    squareMap = new HashMap<>();
    initSquares();
  }

  /**
   * Returns the internal map of square IDs to chess squares.
   *
   * @return the map of squares
   */
  public Map<Integer, ChessSquare> getSquareMap() {
    return squareMap;
  }

  /**
   * Retrieves a square by its unique square ID.
   *
   * @param squareId the ID of the square
   * @return the corresponding {@code ChessSquare}
   */
  public ChessSquare getSquare(int squareId) {
    return getSquareMap().get(squareId);
  }

  /**
   * Retrieves a square using 1-based board coordinates.
   *
   * @param x the x-coordinate (file)
   * @param y the y-coordinate (rank)
   * @return the corresponding {@code ChessSquare}
   */
  public ChessSquare getSquareByCords(int x, int y) {
    return getSquare((y-1)*8 + x);
  }

  /**
   * Checks if the path between two squares is clear (i.e., no pieces in between).
   *
   * @param square1 the starting square
   * @param square2 the ending square
   * @return {@code true} if the path is clear, {@code false} otherwise
   */
  public boolean isPathClear(ChessSquare square1, ChessSquare square2) {
    for (ChessSquare square : getPath(square1, square2)) {
      if (square.hasPiece()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the list of squares in the path between two given squares.
   * Supports straight and diagonal paths.
   *
   * @param square1 the starting square
   * @param square2 the ending square
   * @return a list of {@code ChessSquare} objects forming the path
   */
  public List<ChessSquare> getPath(ChessSquare square1, ChessSquare square2) {
    List<ChessSquare> path = new ArrayList<>();

    int xDiff = square1.getXCoordinate() - square2.getXCoordinate();
    int yDiff = square1.getYCoordinate() - square2.getYCoordinate();

    if(Math.abs(xDiff) == Math.abs(yDiff)) {
      path = getDiagPath(square1, square2);
    }
    else if(xDiff == 0 || yDiff == 0) {
      path = getStraightPath(square1, square2);
    }

    return path;
  }

  /**
   * Returns the straight-line path (horizontal or vertical) between two squares.
   *
   * @param square1 the starting square
   * @param square2 the ending square
   * @return a list of {@code ChessSquare} objects in the path
   */
  private List<ChessSquare> getStraightPath(ChessSquare square1, ChessSquare square2) {
    List<ChessSquare> path = new ArrayList<>();

    int xDiff = square1.getXCoordinate() - square2.getXCoordinate();
    int yDiff = square1.getYCoordinate() - square2.getYCoordinate();

    int upOrDownFactor = xDiff + yDiff > 0 ? 1 : -1;

    if(xDiff == 0) {
      int x = square1.getXCoordinate();
      int y;

      for (int i = 1; i < yDiff*upOrDownFactor; i++){
        y = square1.getYCoordinate() - i * upOrDownFactor;
        path.add(getSquareByCords(x, y));
        }
      }

    else{
      int y = square1.getYCoordinate();
      int x;

      for (int i = 1; i < xDiff*upOrDownFactor; i++){
        x = square1.getXCoordinate() - i * upOrDownFactor;
        path.add(getSquareByCords(x, y));
      }
    }
    return path;
  }

  /**
   * Returns the diagonal path between two squares.
   *
   * @param square1 the starting square
   * @param square2 the ending square
   * @return a list of {@code ChessSquare} objects in the diagonal path
   */
  private List<ChessSquare> getDiagPath(ChessSquare square1, ChessSquare square2) {
    List<ChessSquare> path = new ArrayList<>();

    int xDiff = square1.getXCoordinate() - square2.getXCoordinate();
    int yDiff = square1.getYCoordinate() - square2.getYCoordinate();
    int xFactor = xDiff > 0 ? 1 : -1;
    int yFactor = yDiff > 0 ? 1 : -1;

    int x;
    int y;
    for (int i = 1; i < xDiff * xFactor; i++) {
      x = square1.getXCoordinate() - i * xFactor;
      y = square1.getYCoordinate() - i * yFactor;
      path.add(getSquareByCords(x, y));
      }
    return path;
  }

  /**
   * Initializes all 64 squares on the chess board and maps them by ID.
   */
  private void initSquares() {
    for(int x = 1; x <= 8; x++) {
      for(int y = 1; y <= 8; y++) {
        ChessSquare square = new ChessSquare(x, y);
        squareMap.put(square.getSquareId(), square);
      }
    }
  }
}
