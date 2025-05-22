package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a Rook piece in chess.
 *
 * <p>A rook moves any number of squares horizontally or vertically. It plays a key role in castling
 * and tracks whether it has moved during the game.
 *
 * <p>This class includes support for castling checks via the {@code hasMoved} flag.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Rook extends Piece {

  private boolean hasMoved;

  /**
   * Constructs a {@code Rook} with the given starting square and color.
   *
   * @param startSquare the square the rook starts on
   * @param chessColor the color of the rook
   */
  public Rook(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    hasMoved = false;
  }

  /**
   * Returns whether the rook has moved during the game.
   *
   * @return {@code true} if it has moved, {@code false} otherwise
   */
  public boolean hasMoved() {
    return hasMoved;
  }

  /**
   * Sets the moved status of the rook.
   *
   * @param status {@code true} if the rook has moved, {@code false} otherwise
   */
  public void setMoved(boolean status) {
    hasMoved = status;
  }

  /**
   * Determines whether a move to the given square is possible for a rook. The move must be in a
   * straight line (horizontal or vertical).
   *
   * @param square the target square
   * @return {@code true} if the move is valid for a rook, {@code false} otherwise
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = square.getYCoordinate() - currentSquare.getYCoordinate();
    int xDiff = square.getXCoordinate() - currentSquare.getXCoordinate();
    return (xDiff == 0 || yDiff == 0);
  }

  /**
   * Determines whether this rook threatens the given square.
   *
   * @param square the target square
   * @param chess the current game state
   * @return {@code true} if the square is threatened, {@code false} otherwise
   */
  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  /**
   * Checks whether a move to the given square is legal for the rook.
   *
   * @param square the destination square
   * @param chess the current game
   * @return {@code true} if the move is legal, {@code false} otherwise
   */
  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  /**
   * Moves the rook to the specified square if the move is legal, and marks the rook as having
   * moved.
   *
   * @param square the destination square
   * @param chess the current game state
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if (isMoveLegal(square, chess)) {
      super.move(square, chess);
      hasMoved = true;
    }
  }
}
