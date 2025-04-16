import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersSquare;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions.LadderAction;

public class SnakesAndLaddersSquareTest {

  @Test
  void testValidTileId() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    //assert
    Assertions.assertEquals(1, tile.getSquareId());
  }

  @Test
  void testInvalidTileId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new SnakesAndLaddersSquare(-1);
    });
  }

  @Test
  void testSetLandAction() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    LadderAction ladderAction = new LadderAction(20);
    //act
    tile.setLandAction(ladderAction);
    //assert
    Assertions.assertTrue(tile.hasLandAction());
  }

  @Test
  void testNoLandAction() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    //assert
    Assertions.assertFalse(tile.hasLandAction());
  }

  @Test
  void testLandPlayerOnTileWithLandAction() {
    //arrange
    Player player = new Player("TestPlayer", 1, 1);
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    LadderAction ladderAction = new LadderAction(20);
    tile.setLandAction(ladderAction);
    //act
    tile.landPlayer(player);
    //assert
    Assertions.assertEquals(20, player.getCurrentTile());
  }

  @Test
  void testLandPlayerOnTileWithoutLandAction() {
    //arrange
    Player player = new Player("TestPlayer", 1, 1);
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    //act
    tile.landPlayer(player);
    //assert
    Assertions.assertEquals(1, player.getCurrentTile());
  }
}
