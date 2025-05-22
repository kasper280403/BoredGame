package ladderGame.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.SnakesAndLaddersPlayer;
import org.junit.jupiter.api.Test;

public class LadderActionTest {

  @Test
  void testPerform() {
    //Arrange
    LadderAction ladderTo10 = new LadderAction(10);
    SnakesAndLaddersPlayer player = new SnakesAndLaddersPlayer("Kasper", 1, 1);
    //act
    ladderTo10.perform(player);
    //Assert
    assertEquals(10, player.getCurrentTileId());
  }

  @Test
  void testInvalidDestinationTileId() {
    //Assert
    assertThrows(IllegalArgumentException.class, () -> new LadderAction(-1));
  }
}
