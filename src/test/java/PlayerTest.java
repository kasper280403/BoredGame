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
    void testMovePlayer(){
        player.movePlayer(10);
        assertEquals(10, player.currentTile);
        assertNotEquals(5, player.currentTile);
    }

}