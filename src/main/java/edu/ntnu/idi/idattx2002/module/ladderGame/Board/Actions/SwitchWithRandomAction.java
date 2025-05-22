package edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions;

import java.util.Random;

import static edu.ntnu.idi.idattx2002.module.ladderGame.LadderGame.players;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.LandAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;

public class SwitchWithRandomAction implements LandAction {

  private final Random random;

  public SwitchWithRandomAction() {
    random = new Random();
  }

  @Override
  public void perform(LadderGamePlayer player) {
      LadderGamePlayer playerToSwitch;
      do {
          playerToSwitch = players.get(random.nextInt(players.size()) + 1);
      } while (playerToSwitch.getPlayerID() == player.getPlayerID());

      int playerTileId = player.getCurrentTileId();
      int playerToSwitchTileId = playerToSwitch.getCurrentTileId();

      player.movePlayerToTile(playerToSwitchTileId);
      playerToSwitch.movePlayerToTile(playerTileId);
  }
}
