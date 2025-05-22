package edu.ntnu.idi.idattx2002.module.chess.board;

/**
 * An interface for observing updates to a {@code ChessSquare}.
 * <p>
 * Implementing classes define how to respond when a square changes, such as piece placement or removal.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface SquareObserver {

  /**
   * Called when a {@code ChessSquare} is updated.
   *
   * @param square the square that was updated
   */
  void update(ChessSquare square);
}
