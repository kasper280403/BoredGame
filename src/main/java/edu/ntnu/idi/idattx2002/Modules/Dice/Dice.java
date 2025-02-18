package edu.ntnu.idi.idattx2002.Modules.Dice;
import java.util.Random;

public class Dice {

  private final Random randomNumberGenerator;

  public Dice() {
    randomNumberGenerator = new Random();
  }

  public int throwDice() {
    return randomNumberGenerator.nextInt(6) + 1;
  }
}
