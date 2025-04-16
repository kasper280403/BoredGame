package edu.ntnu.idi.idattx2002.logic.chessLogic.board;

import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;
import edu.ntnu.idi.idattx2002.logic.common.Board.Square;

public class ChessSquare extends Square {

  private final int xCoordinate;
  private final int yCoordinate;
  private Piece piece;

  private SquareObserver observer;

  public ChessSquare(int xCoordinate, int yCoordinate) {
    super(((yCoordinate - 1) * 8) + xCoordinate);

    verifyCoordinates(xCoordinate, yCoordinate);
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public void setObserver(SquareObserver observer) {
    this.observer = observer;
  }

  public void verifyCoordinates(int x, int y) {
    if(x < 1 || x > 8 || y < 1 || y > 8) {
      throw new IllegalArgumentException("Square coordinates are invalid");
    }
  }

  public int getYCoordinate() {
    return yCoordinate;
  }

  public int getXCoordinate() {
    return xCoordinate;
  }

  public boolean hasPiece() {
    return (piece != null);
  }

  public Piece getPiece() {
    if(piece != null) {
      return piece;
    }
    return null;
  }

  public void placePiece(Piece piece) {
    if(this.piece != null) {
      this.piece.die();
      piece.setCurrentSquare(null);
      removePiece();
    }
    this.piece = piece;
    notifyObserver();
  }

  public void removePiece() {
    this.piece = null;
    notifyObserver();
  }

  private void notifyObserver() {
    if(observer != null) {
      observer.update(this);
    }
  }
}
