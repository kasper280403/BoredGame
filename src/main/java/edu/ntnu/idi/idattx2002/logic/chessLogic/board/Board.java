package edu.ntnu.idi.idattx2002.logic.chessLogic.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

  private Map<Integer, ChessSquare> squareMap;

  public Board() {
    initSquares();
  }

  public boolean isPathClear(ChessSquare square1, ChessSquare square2) {
    for (ChessSquare square : getPath(square1, square2)) {
      if (square.hasPiece()) {
        return false;
      }
    }
    return true;
  }

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

  private void initSquares() {
    squareMap = new HashMap<>();
    for(int x = 1; x <= 8; x++) {
      for(int y = 1; y <= 8; y++) {
        ChessSquare square = new ChessSquare(x, y);
        squareMap.put(square.getSquareId(), square);
      }
    }
  }

  public Map<Integer, ChessSquare> getSquareMap() {
    return squareMap;
  }

  public ChessSquare getSquare(int squareId) {
    return getSquareMap().get(squareId);
  }

  public ChessSquare getSquareByCords(int x, int y) {
    return getSquare((y-1)*8 + x);
  }

}
