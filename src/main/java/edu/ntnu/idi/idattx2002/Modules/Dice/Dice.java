package edu.ntnu.idi.idattx2002.Modules.Dice;
import edu.ntnu.idi.idattx2002.Modules.DiceObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

  private final Random randomNumberGenerator;

  private List<DiceObserver> observers;

  public Dice() {
    randomNumberGenerator = new Random();
    observers = new ArrayList<>();
  }

  public int throwDice() {
    return randomNumberGenerator.nextInt(6) + 1;
  }

  public void addObserver(DiceObserver diceObserver) {
    observers.add(diceObserver);
  }

  public void notifyObservers(int A) {
    for (DiceObserver diceObserver : observers) {
      diceObserver.update(A);
    }
  }
}
