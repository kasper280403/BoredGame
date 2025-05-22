package ladderGame.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.LadderGame;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;
import org.junit.jupiter.api.Test;

public class TestSwitchWithRandomAction {

  @Test
  void testPerform() {
    //arrange
    SwitchWithRandomAction switchWithRandomAction = new SwitchWithRandomAction();
    LadderGamePlayer player1 = new LadderGamePlayer("Sindre", 1, 1);
    LadderGamePlayer player2 = new LadderGamePlayer("Kasper", 2, 2);
    LadderGame ladderGame = new LadderGame();

    player1.movePlayerToTile(4);
    player2.movePlayerToTile(20);

    ladderGame.addPlayer(player1);
    ladderGame.addPlayer(player2);

    //act
    switchWithRandomAction.perform(player1);

    //assert
    assertEquals(20, player1.getCurrentTileId());
    assertEquals(4, player2.getCurrentTileId());
  }
}
