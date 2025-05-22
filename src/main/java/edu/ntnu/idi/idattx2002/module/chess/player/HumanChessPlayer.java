package edu.ntnu.idi.idattx2002.module.chess.player;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;

/**
 * Represents a human-controlled player in a chess game.
 *
 * <p>Inherits all behavior from {@code ChessPlayer} and requires no additional logic.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class HumanChessPlayer extends ChessPlayer {

  /**
   * Constructs a new {@code HumanChessPlayer} with the specified name, icon ID, and color.
   *
   * @param name the name of the player
   * @param iconId the icon ID representing the player
   * @param chessColor the color assigned to the player
   */
  public HumanChessPlayer(String name, int iconId, ChessColor chessColor) {
    super(name, iconId, chessColor);
  }
}
