package edu.ntnu.idi.idattx2002.module.ladderGame;

import edu.ntnu.idi.idattx2002.module.ladderGame.Dice.Dice;

/**
 * An interface for objects that want to be notified when a {@code Dice} is rolled.
 * <p>
 * Implementing classes define behavior triggered by dice value changes.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public interface DiceObserver {

  /**
   * Called when the observed dice is rolled and its value is updated.
   *
   * @param dice the dice instance that was rolled
   */
  void update(Dice dice);
}
