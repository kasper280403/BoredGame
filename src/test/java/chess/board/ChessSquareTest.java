package chess.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessSquareTest {

  private ChessSquare square;
  private ChessSquare square2;

  @BeforeEach
  void setUp() {
    square = new ChessSquare(1, 1);
    square2 = new ChessSquare(1,2);
  }

  @Test
  void tesGetCoordinates() {
    //act
    int x = square.getXCoordinate();
    int y = square.getYCoordinate();
    //assert
    assertEquals(x, 1);
    assertEquals(y, 1);
  }

  @Test
  void testInvalidCoordinates() {
    //assert
    assertThrows(IllegalArgumentException.class, () -> {
      new ChessSquare(9, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ChessSquare(0, 0);
    });
  }

  @Test
  void testGetPiece() {
    //arrange
    Pawn piece = new Pawn(square, ChessColor.WHITE);
    square.placePiece(piece);
    //assert
    assertEquals(square.getPiece(), piece);
    assertNull(square2.getPiece());
  }

  @Test
  void testRemovePiece() {
    //arrange
    Pawn whitePiece = new Pawn(square, ChessColor.WHITE);
    Pawn blackPiece = new Pawn(square, ChessColor.BLACK);

    square.placePiece(whitePiece);
    square2.placePiece(blackPiece);
    //act
    square.removePiece();

    //assert
    assertTrue(square2.hasPiece());
    assertFalse(square.hasPiece());
  }

  @Test
  void testHasPiece() {
    //arrange
    Pawn piece = new Pawn(square, ChessColor.WHITE);
    square.placePiece(piece);
    //assert
    assertTrue(square.hasPiece());
    assertFalse(square2.hasPiece());
  }

}
