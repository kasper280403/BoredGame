import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {
    Player player;

    @BeforeEach
    void setUp(){
        player =  new Player("Kasper", 1, 1);
    }

    @Test
    void testMovePlayerBySteps(){
        player.movePlayerBySteps(10);
        assertEquals(10, player.getCurrentTile());
        assertNotEquals(5, player.getCurrentTile());
    }

    @Test
    void testMovePlayerToTile(){
        player.movePlayerToTile(30);
        assertEquals(30, player.getCurrentTile());
        player.movePlayerToTile(2);
        assertEquals(2, player.getCurrentTile());
        assertNotEquals(32, player.getCurrentTile());
    }

}