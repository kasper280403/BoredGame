package edu.ntnu.idi.idattx2002.module.ladderGame;

import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;

/**
 * An interface for observers that are notified when a {@code LadderGamePlayer} updates its state.
 * <p>
 * Implementing classes define how to respond to player movements or changes.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface PlayerObserver {

  /**
   * Called when the observed player updates (e.g. moves to a new tile).
   *
   * @param player the player whose state has changed
   */
  void update(LadderGamePlayer player);
}
