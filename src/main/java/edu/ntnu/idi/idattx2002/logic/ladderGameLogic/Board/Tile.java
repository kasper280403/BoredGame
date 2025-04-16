package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.Player;

public class Tile {

  private final int tileId;
  private LandAction landAction;

  public Tile (int tileId) {
    validateTileId(tileId);

    this.tileId = tileId;
   }

   private void validateTileId(int tileId) {
    if (tileId < 0) {
      throw new IllegalArgumentException("TileId has to be an positive integer");
    }
   }

   public int getTileId() {
    return this.tileId;
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
