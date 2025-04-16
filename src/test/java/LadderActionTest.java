import static org.junit.Assert.assertEquals;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LadderActionTest {

  @Test
  void testPerform() {
    //Arrange
    LadderAction ladderTo10 = new LadderAction(10);
    Player player = new Player("Kasper", 1, 1);
    //act
    ladderTo10.perform(player);
    //Assert
    Assertions.assertEquals(10, player.getCurrentTile());
  }

  @Test
  void testInvalidDestinationTileId() {
    //Assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> new LadderAction(-1));
  }
}
