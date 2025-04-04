package edu.ntnu.idi.idattx2002.Modules;

import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.view.DiceWindow;

public interface DiceObserver {

  public void update(Dice dice);
}
