package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookTest {

  private Rook whiteRook;
  private Rook blackRook;

  @BeforeEach
  void setUp() {
    whiteRook = new Rook(new ChessSquare(1, 1), ChessColor.WHITE);
    blackRook = new Rook(new ChessSquare(1, 8), ChessColor.BLACK);
  }

  @Test
  void testIsMovePossible() {
    // arrange
    ChessSquare whiteRookDestination = new ChessSquare(4, 1);
    ChessSquare blackRookDestination = new ChessSquare(7, 8);

    // assert
    assertTrue(whiteRook.isMovePossible(whiteRookDestination));
    assertTrue(blackRook.isMovePossible(blackRookDestination));

    assertFalse(whiteRook.isMovePossible(blackRookDestination));
    assertFalse(blackRook.isMovePossible(whiteRookDestination));
  }
}
