package edu.ntnu.idi.idattx2002.Modules.Board.Actions;

import java.util.Random;

import static edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders.players;
import edu.ntnu.idi.idattx2002.Modules.Board.LandAction;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;

public class SwitchWithRandomAction implements LandAction {

  private final Random random;

  public SwitchWithRandomAction() {
    random = new Random();
  }

  @Override
  public void perform(Player player) {
    Player playerToSwitch = players.get(random.nextInt(players.size()) + 1);

    int playerTileId = player.getCurrentTile();
    int playerToSwitchTileId = playerToSwitch.getCurrentTile();

    player.movePlayerToTile(playerToSwitchTileId);
    playerToSwitch.movePlayerToTile(playerTileId);
  }



}
