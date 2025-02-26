import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    Dice dice;

    @BeforeEach
    void setUp(){
        dice =  new Dice();
    }

    @Test
    void testThrowDice(){
        for (int i = 0; i < 1000; i++) { // Tester terningen 1000 ganger
            int result = dice.throwDice();
            assertTrue(result >= 1 && result <= 6, "Dice shows invalid int: " + result);
        }
    }

}
