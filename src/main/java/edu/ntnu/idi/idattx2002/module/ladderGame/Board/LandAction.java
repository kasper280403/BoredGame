package edu.ntnu.idi.idattx2002.module.ladderGame.Board;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;

/**
 * Represents an action to be performed when a player lands on a square.
 * <p>
 * Implementing classes define specific behaviors triggered by landing.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface LandAction {

  /**
   * Performs the action for the given player.
   *
   * @param player the player who landed on the square
   */
  void perform(LadderGamePlayer player);
}

