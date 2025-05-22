package edu.ntnu.idi.idattx2002.module.ladderGame.board;

import edu.ntnu.idi.idattx2002.module.common.board.Square;
import edu.ntnu.idi.idattx2002.module.ladderGame.player.LadderGamePlayer;

/**
 * Represents a square on the ladder game board that may contain a land action.
 *
 * <p>A {@code LandAction} can be assigned to this square to define behavior when a player lands on
 * it.
 *
 * @author Sindre Mjøs and KAsper Karlsen
 * @version 1.0
 */
public class LadderGameSquare extends Square {

  private LandAction landAction;

  /**
   * Constructs a LadderGameSquare with the specified square ID.
   *
   * @param squareId the unique ID of the square
   */
  public LadderGameSquare(int squareId) {
    super(squareId);
  }

  /**
   * Returns the land action associated with this square, if any.
   *
   * @return the land action, or {@code null} if none is set
   */
  public LandAction getLandAction() {
    if (hasLandAction()) {
      return landAction;
    }
    return null;
  }

  /**
   * Assigns a land action to this square.
   *
   * @param landAction the land action to assign
   * @throws IllegalArgumentException if a land action is already set
   */
  public void setLandAction(LandAction landAction) {
    if (hasLandAction()) {
      throw new IllegalArgumentException("LandAction is already set");
    }
    this.landAction = landAction;
  }

  /**
   * Checks if this square has a land action assigned.
   *
   * @return {@code true} if a land action is set, {@code false} otherwise
   */
  public boolean hasLandAction() {
    return this.landAction != null;
  }

  /**
   * Executes the land action if one is assigned when the player lands on this square.
   *
   * @param player the player landing on the square
   */
  public void landPlayer(LadderGamePlayer player) {
    if (hasLandAction()) {
      landAction.perform(player);
    }
  }
}
