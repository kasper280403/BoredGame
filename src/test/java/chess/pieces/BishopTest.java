package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {

  private Bishop whiteBishop;
  private Bishop blackBishop;

  @BeforeEach
  void bishopSetUp() {
    whiteBishop = new Bishop(new ChessSquare(3, 1), ChessColor.WHITE);
    blackBishop = new Bishop(new ChessSquare(3, 8), ChessColor.BLACK);
  }

  @Test
  void testIsMovePossible() {
    //arrange
    ChessSquare whiteBishopDestination = new ChessSquare(8, 6);
    ChessSquare blackBishopDestination = new ChessSquare(5, 6);

    //assert
    assertTrue(whiteBishop.isMovePossible(whiteBishopDestination));
    assertTrue(blackBishop.isMovePossible(blackBishopDestination));

    assertFalse(whiteBishop.isMovePossible(blackBishopDestination));
    assertFalse(blackBishop.isMovePossible(whiteBishopDestination));
  }
}
