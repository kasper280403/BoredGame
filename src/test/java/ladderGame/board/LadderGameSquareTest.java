package ladderGame.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.Board.LadderGameSquare;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;
import org.junit.jupiter.api.Test;
import edu.ntnu.idi.idattx2002.module.ladderGame.Board.Actions.LadderAction;

public class LadderGameSquareTest {

  @Test
  void testGetSquareid() {
    //arrange
    LadderGameSquare tile = new LadderGameSquare(17);
    //assert
    assertEquals(17, tile.getSquareId());
  }

  @Test
  void testValidTileId() {
    //arrange
    LadderGameSquare tile = new LadderGameSquare(1);
    //assert
    assertEquals(1, tile.getSquareId());
  }

  @Test
  void testInvalidTileId() {
    assertThrows(IllegalArgumentException.class, () -> {
      new LadderGameSquare(-1);
    });
  }

  @Test
  void testSetLandAction() {
    //arrange
    LadderGameSquare tile = new LadderGameSquare(1);
    LadderAction ladderAction = new LadderAction(20);
    //act
    tile.setLandAction(ladderAction);
    //assert
    assertTrue(tile.hasLandAction());
  }

  @Test
  void testNoLandAction() {
    //arrange
    LadderGameSquare tile = new LadderGameSquare(1);
    //assert
    assertFalse(tile.hasLandAction());
  }

  @Test
  void testLandPlayerOnTileWithLandAction() {
    //arrange
    LadderGamePlayer player = new LadderGamePlayer("TestPlayer", 1, 1);
    LadderGameSquare tile = new LadderGameSquare(1);
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
    LadderGamePlayer player = new LadderGamePlayer("TestPlayer", 1, 1);
    LadderGameSquare tile = new LadderGameSquare(5);
    //act
    tile.landPlayer(player);
    //assert
    assertEquals(0, player.getCurrentTileId());
  }
}
