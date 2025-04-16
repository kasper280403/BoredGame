import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {
    Player player;

    @BeforeEach
    //arrange
    void setUp(){
        player =  new Player("Kasper", 1, 1);
    }

    @Test
    void testMovePlayerBySteps(){
        //act
        player.movePlayerBySteps(10);
        //assert
        assertEquals(11, player.getCurrentTile());
        assertNotEquals(5, player.getCurrentTile());
    }

    @Test
    void testMovePlayerToTile(){
        //act
        player.movePlayerToTile(30);
        //assert
        assertEquals(30, player.getCurrentTile());
        assertNotEquals(32, player.getCurrentTile());
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
    void testGetPieceID() {
        //act
        int pieceID = player.getPieceID();
        //assert
        assertEquals(1, pieceID);
    }
}