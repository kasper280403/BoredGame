package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board;

import edu.ntnu.idi.idattx2002.logic.common.Board.Square;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;

public class SnakesAndLaddersSquare extends Square {

  private LandAction landAction;

  public SnakesAndLaddersSquare(int squareId) {
    super(squareId);
   }

   public LandAction getLandAction(){
    if(hasLandAction()){
      return landAction;
    }
    return null;
   }

   public void setLandAction(LandAction landAction) {
    if (hasLandAction()) {
      throw new IllegalArgumentException("LandAction is already set");
    }
    this.landAction = landAction;
   }

  public boolean hasLandAction() {
    return this.landAction != null;
  }

  public void landPlayer(SnakesAndLaddersPlayer player) {
    if (hasLandAction()) {
      landAction.perform(player);
    }
   }
}
