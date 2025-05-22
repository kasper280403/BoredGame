package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a Bishop piece in chess.
 *
 * <p>A bishop moves diagonally any number of squares. This class extends the {@code Piece} class
 * and provides bishop-specific movement logic.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Bishop extends Piece {

  /**
   * Constructs a {@code Bishop} with a starting square and a color.
   *
   * @param startSquare the square the bishop starts on
   * @param chessColor the color of the bishop
   */
  public Bishop(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  /**
   * Determines whether a move to the given square is possible for a bishop (i.e., it lies on the
   * same diagonal).
   *
   * @param square the target square
   * @return {@code true} if the move is diagonal, {@code false} otherwise
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return xDiff == yDiff;
  }

  /**
   * Determines whether the bishop threatens the given square.
   *
   * @param square the square to check
   * @param chess the current chess game
   * @return {@code true} if the square is threatened, {@code false} otherwise
   */
  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  /**
   * Checks if the move to the given square is legal based on game state.
   *
   * @param square the destination square
   * @param chess the current chess game
   * @return {@code true} if the move is legal, {@code false} otherwise
   */
  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  /**
   * Moves the bishop to a new square if the move is legal.
   *
   * @param square the destination square
   * @param chess the current chess game
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if (isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
