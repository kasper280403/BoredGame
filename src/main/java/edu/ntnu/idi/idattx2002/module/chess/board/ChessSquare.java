package edu.ntnu.idi.idattx2002.module.chess.board;

import edu.ntnu.idi.idattx2002.module.chess.pieces.Piece;
import edu.ntnu.idi.idattx2002.module.common.Board.Square;

/**
 * Represents a single square on a chess board with a specific coordinate.
 * <p>
 * Each square may contain a chess piece and supports observer notification
 * for updates such as piece placement or removal.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class ChessSquare extends Square {

  private final int xCoordinate;
  private final int yCoordinate;
  private Piece piece;

  private SquareObserver observer;

  /**
   * Constructs a new {@code ChessSquare} with the given coordinates.
   *
   * @param xCoordinate the file (1–8)
   * @param yCoordinate the rank (1–8)
   * @throws IllegalArgumentException if the coordinates are outside the 1–8 range
   */
  public ChessSquare(int xCoordinate, int yCoordinate) {
    super(((yCoordinate - 1) * 8) + xCoordinate);

    verifyCoordinates(xCoordinate, yCoordinate);
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  /**
   * Returns the y-coordinate (rank) of this square.
   *
   * @return the y-coordinate
   */
  public int getYCoordinate() {
    return yCoordinate;
  }

  /**
   * Returns the x-coordinate (file) of this square.
   *
   * @return the x-coordinate
   */
  public int getXCoordinate() {
    return xCoordinate;
  }

  /**
   * Returns the piece currently on this square, if any.
   *
   * @return the piece or {@code null} if empty
   */
  public Piece getPiece() {
    if(piece != null) {
      return piece;
    }
    return null;
  }

  /**
   * Verifies that the given coordinates are within the valid 1–8 chessboard range.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @throws IllegalArgumentException if coordinates are invalid
   */
  private void verifyCoordinates(int x, int y) {
    if(x < 1 || x > 8 || y < 1 || y > 8) {
      throw new IllegalArgumentException("Square coordinates are invalid");
    }
  }

  /**
   * Sets the observer for this square.
   *
   * @param observer the {@code SquareObserver} to notify on changes
   */
  public void setObserver(SquareObserver observer) {
    this.observer = observer;
  }


  /**
   * Checks whether this square currently has a piece.
   *
   * @return {@code true} if a piece is present; {@code false} otherwise
   */
  public boolean hasPiece() {
    return (piece != null);
  }

  /**
   * Places a piece on this square. If a piece is already present, it is removed and marked as dead.
   *
   * @param piece the piece to place
   */
  public void placePiece(Piece piece) {
    if(this.piece != null) {
      this.piece.die();
      piece.setCurrentSquare(null);
      removePiece();
    }
    this.piece = piece;
    notifyObserver();
  }

  /**
   * Removes any piece currently on this square and notifies the observer.
   */
  public void removePiece() {
    this.piece = null;
    notifyObserver();
  }


  /**
   * Notifies the registered observer of a change to this square.
   */
  private void notifyObserver() {
    if(observer != null) {
      observer.update(this);
    }
  }
}
