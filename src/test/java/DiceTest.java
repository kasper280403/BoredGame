import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
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
        //Tests dice 1000 times
        for (int i = 0; i < 1000; i++) {
            //act
            int result = dice.throwDice();
            //assert
            assertTrue(result >= 1 && result <= 6, "Dice shows invalid int: " + result);
        }
    }

}
