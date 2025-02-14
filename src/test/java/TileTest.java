import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;

public class TileTest {

  @Test
  void testValidTileId() {
    //arrange
    Tile tile = new Tile(1);
    //assert
    Assertions.assertEquals(1, tile.getTileId());
  }

  @Test
  void testInvalidTileId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Tile(-1);
    });
  }

  @Test
  void testSetLandAction() {
    //arrange
    Tile tile = new Tile(1);
    LadderAction ladderAction = new LadderAction(20);
    //act
    tile.setLandAction(ladderAction);
    //assert
    Assertions.assertTrue(tile.hasLandAction());
  }

  @Test
  void testNoLandAction() {
    //arrange
    Tile tile = new Tile(1);
    //assert
    Assertions.assertFalse(tile.hasLandAction());
  }

  @Test
  void testLandPlayerOnTileWithLandAction() {
    //arrange
    Player player = new Player("TestPlayer", 1, 1);
    Tile tile = new Tile(1);
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
    Tile tile = new Tile(1);
    //act
    tile.landPlayer(player);
    //assert
    Assertions.assertEquals(0, player.getCurrentTile());
  }
}
