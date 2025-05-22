package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenTest {

  private Queen whiteQueen;
  private Queen blackQueen;

  @BeforeEach
  void setUp() {
    whiteQueen = new Queen(new ChessSquare(4, 1), ChessColor.WHITE);
    blackQueen = new Queen(new ChessSquare(4, 8), ChessColor.BLACK);
  }

  @Test
  void testIsStraightMovePossible() {
    //arrange
    ChessSquare destination = new ChessSquare(4, 5);

    //assert
    assertTrue(whiteQueen.isMovePossible(destination));
    assertTrue(blackQueen.isMovePossible(destination));
  }

  @Test
  void testIsDiagMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(8, 5);
    ChessSquare blackDestination = new ChessSquare(2, 6);

    //assert
    assertTrue(whiteQueen.isMovePossible(whiteDestination));
    assertTrue(blackQueen.isMovePossible(blackDestination));
  }

  @Test
  void testIsMovePossibleNegative() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(1, 6);
    ChessSquare blackDestination = new ChessSquare(6, 5);

    //assert
    assertFalse(whiteQueen.isMovePossible(whiteDestination));
    assertFalse(blackQueen.isMovePossible(blackDestination));
  }

}
