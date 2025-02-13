import static org.junit.Assert.assertEquals;

import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LadderActionTest {
  Player player;

  @BeforeEach
  void setUp() {
    player = new Player("Kasper", 1, 1);
  }

  @Test
  void testPerform() {
    //Arrange
    LadderAction ladderTo10 = new LadderAction(10);
    //act
    ladderTo10.perform(player);
    //Assert
    Assertions.assertEquals(10, player.getCurrentTile());
  }
}
