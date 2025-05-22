package edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions;

import java.util.Random;

import static edu.ntnu.idi.idattx2002.module.ladderGame.Games.SnakesAndLadders.players;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.LandAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.SnakesAndLaddersPlayer;

public class SwitchWithRandomAction implements LandAction {

  private final Random random;

  public SwitchWithRandomAction() {
    random = new Random();
  }

  @Override
  public void perform(SnakesAndLaddersPlayer player) {
      SnakesAndLaddersPlayer playerToSwitch;
      do {
          playerToSwitch = players.get(random.nextInt(players.size()) + 1);
      } while (playerToSwitch.getPlayerID() == player.getPlayerID());

      int playerTileId = player.getCurrentTileId();
      int playerToSwitchTileId = playerToSwitch.getCurrentTileId();

      player.movePlayerToTile(playerToSwitchTileId);
      playerToSwitch.movePlayerToTile(playerTileId);
  }
}
