package edu.ntnu.idi.idattx2002.module.ladderGame.Board;

import edu.ntnu.idi.idattx2002.module.common.Board.Square;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;

public class LadderGameSquare extends Square {

  private LandAction landAction;

  public LadderGameSquare(int squareId) {
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

  public void landPlayer(LadderGamePlayer player) {
    if (hasLandAction()) {
      landAction.perform(player);
    }
   }
}
