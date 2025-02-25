import static org.junit.Assert.assertEquals;

import edu.ntnu.idi.idattx2002.Modules.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
import static edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders.players;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSwitchWithRandomAction {

  @Test
  void testPerform() {
    //arrange
    SwitchWithRandomAction switchWithRandomAction = new SwitchWithRandomAction();
    Player player1 = new Player("Sindre", 1, 1);
    Player player2 = new Player("Kasper", 2, 2);
    SnakesAndLadders snakesAndLadders = new SnakesAndLadders();

    player1.movePlayerToTile(4);
    player2.movePlayerToTile(20);

    players.put(1, player1);
    players.put(2, player2);

    //act
    switchWithRandomAction.perform(player1);

    //assert
    Assertions.assertEquals(20, player1.getCurrentTile());
    Assertions.assertEquals(4, player2.getCurrentTile());
  }


}
