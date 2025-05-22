package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

import edu.ntnu.idi.idattx2002.module.chess.pieces.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingTest {

  private King whiteKing;
  private King blackKing;

  @BeforeEach
  void setUp() {
    whiteKing = new King(new ChessSquare(5, 1), ChessColor.WHITE);
    blackKing = new King(new ChessSquare(5, 8), ChessColor.BLACK);
  }

  @Test
  void testIsStraightMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(4, 1);
    ChessSquare blackDestination = new ChessSquare(4, 8);

    //assert
    assertTrue(whiteKing.isMovePossible(whiteDestination));
    assertTrue(blackKing.isMovePossible(blackDestination));
  }

  @Test
  void testIsDiagMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(4, 2);
    ChessSquare blackDestination = new ChessSquare(4, 7);

    //assert
    assertTrue(whiteKing.isMovePossible(whiteDestination));
    assertTrue(blackKing.isMovePossible(blackDestination));
  }

  @Test
  void testIsMovePossibleNegative() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(1, 2);
    ChessSquare blackDestination = new ChessSquare(1, 7);

    //assert
    assertFalse(whiteKing.isMovePossible(whiteDestination));
    assertFalse(blackKing.isMovePossible(blackDestination));
  }
}
