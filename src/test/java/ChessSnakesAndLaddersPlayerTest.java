import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ChessSnakesAndLaddersPlayerTest {
    SnakesAndLaddersPlayer player;

    @BeforeEach
    //arrange
    void setUp(){
        player =  new SnakesAndLaddersPlayer("Kasper", 1, 1);
    }

    @Test
    void testMovePlayerBySteps(){
        //act
        player.movePlayerBySteps(10);
        //assert
        assertEquals(11, player.getCurrentTileId());
        assertNotEquals(5, player.getCurrentTileId());
    }

    @Test
    void testMovePlayerToTile(){
        //act
        player.movePlayerToTile(30);
        //assert
        assertEquals(30, player.getCurrentTileId());
        assertNotEquals(32, player.getCurrentTileId());
    }


    @Test
    void testGetPlayerName() {
        //act
        String name = player.getPlayerName();
        //assert
        assertEquals("Kasper", name);
    }

    @Test
    void testGetPlayerID() {
        //act
        int playerID = player.getPlayerID();
        //assert
        assertEquals(1, playerID);
    }

    @Test
    void testGetIconId() {
        //act
        int pieceID = player.getIconId();
        //assert
        assertEquals(1, pieceID);
    }
}