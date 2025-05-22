package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnTest {

  private Pawn whitePawn;
  private Pawn blackPawn;

  @BeforeEach
  void setUp() {
    whitePawn = new Pawn(new ChessSquare(1, 2), ChessColor.WHITE);
    blackPawn = new Pawn(new ChessSquare(1, 7), ChessColor.BLACK);
  }

  @Test
  void testIsSingleStraightMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(1, 3);
    ChessSquare blackDestination = new ChessSquare(1, 6);

    //assert
    assertTrue(whitePawn.isMovePossible(whiteDestination));
    assertTrue(blackPawn.isMovePossible(blackDestination));
  }

  @Test
  void testIsStartDoubleMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(1, 4);
    ChessSquare blackDestination = new ChessSquare(1, 5);

    //assert
    assertTrue(whitePawn.isMovePossible(whiteDestination));
    assertTrue(blackPawn.isMovePossible(blackDestination));
  }

  @Test
  void testIsNoneStartDoubleMovePossible() {
    //arrange
    ChessSquare whiteDestination = new ChessSquare(1, 4);
    ChessSquare blackDestination = new ChessSquare(1, 5);
    whitePawn.setFirstMoved(false);
    blackPawn.setFirstMoved(false);

    //assert
    assertTrue(whitePawn.isMovePossible(whiteDestination));
    assertTrue(blackPawn.isMovePossible(blackDestination));
  }
}
