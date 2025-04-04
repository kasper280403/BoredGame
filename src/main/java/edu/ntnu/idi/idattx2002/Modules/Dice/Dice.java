package edu.ntnu.idi.idattx2002.Modules.Dice;
import edu.ntnu.idi.idattx2002.Modules.DiceObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

  private final Random randomNumberGenerator;

  private List<DiceObserver> observers;
  private int currentValue;

  public Dice() {
    randomNumberGenerator = new Random();
    observers = new ArrayList<>();
    currentValue = 6;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public int throwDice() {
    currentValue = randomNumberGenerator.nextInt(6) + 1;
    notifyObservers();
    return currentValue;
  }

  public void addObserver(DiceObserver diceObserver) {
    observers.add(diceObserver);
    notifyObservers();
  }

  public void notifyObservers() {
    for (DiceObserver diceObserver : observers) {
      diceObserver.update(this);
    }
  }
}
