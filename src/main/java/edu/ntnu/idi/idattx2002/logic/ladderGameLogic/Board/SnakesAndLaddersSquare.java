package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board;

import edu.ntnu.idi.idattx2002.logic.common.Board.Square;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.Player;

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

   //TODO exeption handling if tileAction exists
   public void setLandAction(LandAction landAction) {
    this.landAction = landAction;
   }

  public boolean hasLandAction() {
    return this.landAction != null;
  }

  public void landPlayer(Player player) {
    if (hasLandAction()) {
      landAction.perform(player);
    }
   }
}
