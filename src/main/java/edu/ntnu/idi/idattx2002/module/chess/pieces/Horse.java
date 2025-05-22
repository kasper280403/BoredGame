package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a Knight piece in chess (referred to as "Horse" here).
 * <p>
 * A knight moves in an L-shape: two squares in one direction and one square perpendicular to that.
 * Knights can jump over other pieces.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Horse extends Piece{


  /**
   * Constructs a {@code Horse} (Knight) with a starting square and color.
   *
   * @param startSquare the square the knight starts on
   * @param chessColor the color of the knight
   */
  public Horse(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  /**
   * Determines whether a move to the given square follows the L-shaped movement of a knight.
   *
   * @param square the target square
   * @return {@code true} if the move is valid for a knight, {@code false} otherwise
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return (yDiff == 2 && xDiff == 1) || (xDiff == 2 && yDiff == 1);
  }

  /**
   * Determines whether this knight threatens a given square.
   *
   * @param square the square to check
   * @param chess the current chess game
   * @return {@code true} if the knight threatens the square, {@code false} otherwise
   */
  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) &&  super.threatens(square, chess);
  }

  /**
   * Checks if the move to the given square is legal for the knight.
   *
   * @param square the destination square
   * @param chess the current chess game
   * @return {@code true} if the move is legal, {@code false} otherwise
   */
  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) &&  super.isMoveLegal(square, chess);
  }

  /**
   * Moves the knight to the given square if the move is legal.
   *
   * @param square the destination square
   * @param chess the current chess game
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
