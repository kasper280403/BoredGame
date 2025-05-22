package edu.ntnu.idi.idattx2002.module.ladderGame.board.actions;

import static edu.ntnu.idi.idattx2002.module.ladderGame.LadderGame.players;

import edu.ntnu.idi.idattx2002.module.ladderGame.board.LandAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.player.LadderGamePlayer;
import java.util.Random;

/**
 * Represents an action where the player switches positions with a randomly selected other player.
 *
 * <p>The selected player must not be the same as the player executing the action.
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class SwitchWithRandomAction implements LandAction {

  private final Random random;

  /** Constructs a new SwitchWithRandomAction with a fresh random generator. */
  public SwitchWithRandomAction() {
    random = new Random();
  }

  /**
   * Performs the switch action by selecting another random player (not the current player) and
   * swapping their positions on the board.
   *
   * @param player the player landing on the tile and triggering the action
   */
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
