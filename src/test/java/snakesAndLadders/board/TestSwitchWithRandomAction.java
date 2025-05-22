package snakesAndLadders.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import org.junit.jupiter.api.Test;

public class TestSwitchWithRandomAction {

  @Test
  void testPerform() {
    //arrange
    SwitchWithRandomAction switchWithRandomAction = new SwitchWithRandomAction();
    SnakesAndLaddersPlayer player1 = new SnakesAndLaddersPlayer("Sindre", 1, 1);
    SnakesAndLaddersPlayer player2 = new SnakesAndLaddersPlayer("Kasper", 2, 2);
    SnakesAndLadders snakesAndLadders = new SnakesAndLadders();

    player1.movePlayerToTile(4);
    player2.movePlayerToTile(20);

    snakesAndLadders.addPlayer(player1);
    snakesAndLadders.addPlayer(player2);

    //act
    switchWithRandomAction.perform(player1);

    //assert
    assertEquals(20, player1.getCurrentTileId());
    assertEquals(4, player2.getCurrentTileId());
  }
}
