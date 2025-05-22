package edu.ntnu.idi.idattx2002.module.chess;

import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Piece;

/**
 * Represents a move in a chess game.
 *
 * <p>Encapsulates the logic for executing a move from a starting square to a destination square,
 * and provides a method to verify whether the move was executed successfully.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Move {

  private final ChessSquare startSquare;
  private final ChessSquare endSquare;
  private final Chess chess;

  private final Piece pieceToMove;

  /**
   * Constructs a {@code Move} object for the given start and end squares within the context of a
   * chess game.
   *
   * @param startSquare the square the piece is moving from
   * @param endSquare the square the piece is moving to
   * @param chess the chess game instance this move belongs to
   */
  public Move(ChessSquare startSquare, ChessSquare endSquare, Chess chess) {
    this.startSquare = startSquare;
    this.endSquare = endSquare;
    this.chess = chess;

    pieceToMove = startSquare.getPiece();
  }

  /** Executes the move by calling the piece's move logic. */
  public void execute() {
    pieceToMove.move(endSquare, chess);
  }

  /**
   * Checks if the move was successful. A move is considered successful if the piece is no longer on
   * the starting square.
   *
   * @return {@code true} if the move was successful, {@code false} otherwise
   */
  public boolean successful() {
    return pieceToMove != null && startSquare.getPiece() != pieceToMove;
  }
}
