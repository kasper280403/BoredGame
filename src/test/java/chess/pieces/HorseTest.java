package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HorseTest {

  private Horse whiteHorse;
  private Horse blackHorse;

  @BeforeEach
  void setup() {
    whiteHorse = new Horse(new ChessSquare(2, 1), ChessColor.WHITE);
    blackHorse= new Horse(new ChessSquare(2, 8), ChessColor.BLACK);
  }

  @Test
  void testIsMovePossible() {
    //arrange
    ChessSquare whiteHorseDestination = new ChessSquare(3, 3);
    ChessSquare blackHorseDestination = new ChessSquare(3, 6);

    //assert
    assertTrue(whiteHorse.isMovePossible(whiteHorseDestination));
    assertTrue(blackHorse.isMovePossible(blackHorseDestination));

    assertFalse(whiteHorse.isMovePossible(blackHorseDestination));
    assertFalse(blackHorse.isMovePossible(whiteHorseDestination));
  }

}
