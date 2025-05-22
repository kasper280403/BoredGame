package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a Queen piece in chess.
 * <p>
 * The queen combines the movement abilities of the rook and bishop, allowing it
 * to move any number of squares in a straight line horizontally, vertically, or diagonally.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Queen extends Piece{

  /**
   * Constructs a {@code Queen} with a starting square and color.
   *
   * @param startSquare the square the queen starts on
   * @param chessColor the color of the queen
   */
  public Queen(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
  }

  /**
   * Determines whether a move to the given square is possible for the queen.
   * The move must be straight (vertical or horizontal) or diagonal.
   *
   * @param square the target square
   * @return {@code true} if the move is valid for a queen, {@code false} otherwise
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    int yDiff = Math.abs(square.getYCoordinate() - currentSquare.getYCoordinate());
    int xDiff = Math.abs(square.getXCoordinate() - currentSquare.getXCoordinate());
    return xDiff == yDiff || xDiff == 0 || yDiff == 0;
  }

  /**
   * Determines whether this queen threatens the given square.
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
   * Checks whether a move to the given square is legal for the queen.
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
   * Moves the queen to the specified square if the move is legal.
   *
   * @param square the destination square
   * @param chess the current game state
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
    }
  }
}
