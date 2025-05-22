package edu.ntnu.idi.idattx2002.module.common;

import edu.ntnu.idi.idattx2002.module.common.player.Player;

/**
 * An interface for observers that are notified when a player wins.
 *
 * <p>Implementing classes should define what happens when a player is declared the winner.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface WinObserver {
  void update(Player player);
}
