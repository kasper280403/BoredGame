package ladderGame.player;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.player.LadderGamePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LadderGamePlayerTest {
  LadderGamePlayer player;

  @BeforeEach
  // arrange
  void setUp() {
    player = new LadderGamePlayer("Kasper", 1, 1);
  }

  @Test
  void testGetCurrentTileId() {
    // arrange
    player.movePlayerToTile(14);
    // assert
    assertEquals(player.getCurrentTileId(), 14);
  }

  @Test
  void testMovePlayerBySteps() {
    // act
    player.movePlayerBySteps(10);
    // assert
    assertEquals(10, player.getCurrentTileId());
    assertNotEquals(5, player.getCurrentTileId());
  }

  @Test
  void testMovePlayerToTile() {
    // act
    player.movePlayerToTile(30);
    // assert
    assertEquals(30, player.getCurrentTileId());
    assertNotEquals(32, player.getCurrentTileId());
  }

  @Test
  void testGetPlayerName() {
    // act
    String name = player.getName();
    // assert
    assertEquals("Kasper", name);
  }

  @Test
  void testGetPlayerID() {
    // act
    int playerID = player.getPlayerID();
    // assert
    assertEquals(1, playerID);
  }

  @Test
  void testGetIconId() {
    // act
    int pieceID = player.getIconId();
    // assert
    assertEquals(1, pieceID);
  }
}
