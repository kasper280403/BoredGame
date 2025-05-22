package edu.ntnu.idi.idattx2002.module.ladderGame.dice;

import edu.ntnu.idi.idattx2002.module.ladderGame.DiceObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a standard six-sided dice used in the ladder game.
 *
 * <p>Supports observer registration to notify listeners when the dice is rolled.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Dice {

  private final Random randomNumberGenerator;

  private final List<DiceObserver> observers;
  private int currentValue;

  /** Constructs a new {@code Dice} object with an initial value of 6. */
  public Dice() {
    randomNumberGenerator = new Random();
    observers = new ArrayList<>();
    currentValue = 6;
  }

  /**
   * Returns the current face value of the dice.
   *
   * @return the current value (1–6)
   */
  public int getCurrentValue() {
    return currentValue;
  }

  /**
   * Rolls the dice to generate a random number between 1 and 6. Notifies all registered observers
   * of the new value.
   *
   * @return the newly rolled value
   */
  public int throwDice() {
    currentValue = randomNumberGenerator.nextInt(6) + 1;
    notifyObservers();
    return currentValue;
  }

  /**
   * Adds an observer to be notified whenever the dice is rolled.
   *
   * @param diceObserver the observer to add
   */
  public void addObserver(DiceObserver diceObserver) {
    observers.add(diceObserver);
  }

  /** Notifies all registered observers of the dice's current value. */
  public void notifyObservers() {
    for (DiceObserver diceObserver : observers) {
      diceObserver.update(this);
    }
  }
}
