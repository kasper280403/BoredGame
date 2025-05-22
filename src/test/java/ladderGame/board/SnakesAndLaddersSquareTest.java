package ladderGame.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.Board.SnakesAndLaddersSquare;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.SnakesAndLaddersPlayer;
import org.junit.jupiter.api.Test;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.LadderAction;

public class SnakesAndLaddersSquareTest {

  @Test
  void testGetSquareid() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(17);
    //assert
    assertEquals(17, tile.getSquareId());
  }

  @Test
  void testValidTileId() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    //assert
    assertEquals(1, tile.getSquareId());
  }

  @Test
  void testInvalidTileId() {
    assertThrows(IllegalArgumentException.class, () -> {
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
    assertTrue(tile.hasLandAction());
  }

  @Test
  void testNoLandAction() {
    //arrange
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    //assert
    assertFalse(tile.hasLandAction());
  }

  @Test
  void testLandPlayerOnTileWithLandAction() {
    //arrange
    SnakesAndLaddersPlayer player = new SnakesAndLaddersPlayer("TestPlayer", 1, 1);
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(1);
    LadderAction ladderAction = new LadderAction(20);
    tile.setLandAction(ladderAction);
    //act
    tile.landPlayer(player);
    //assert
    assertEquals(20, player.getCurrentTileId());
  }

  @Test
  void testLandPlayerOnTileWithoutLandAction() {
    //arrange
    SnakesAndLaddersPlayer player = new SnakesAndLaddersPlayer("TestPlayer", 1, 1);
    SnakesAndLaddersSquare tile = new SnakesAndLaddersSquare(5);
    //act
    tile.landPlayer(player);
    //assert
    assertEquals(0, player.getCurrentTileId());
  }
}
