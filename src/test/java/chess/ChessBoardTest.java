package chess;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

  private ChessBoard board;

  @BeforeEach
  void setUp() {
    board = new ChessBoard();
  }

  @Test
  void testGetSquareMap() {
    //assert
    assertEquals(board.getSquareMap().size(), 64);
  }

  @Test
  void testGetSquare() {
    //act
    ChessSquare squareA1 = board.getSquare(1);
    ChessSquare squareB3 = board.getSquareByCords(2, 3);
    //assert
    assertEquals(squareA1.getXCoordinate(), 1);
    assertEquals(squareA1.getYCoordinate(), 1);

    assertEquals(squareB3.getXCoordinate(), 2);
    assertEquals(squareB3.getYCoordinate(), 3);
  }

  @Test
  void testIsPathClearPositive() {
    //arrange
    ChessSquare squareA1 = board.getSquareByCords(1, 1);
    ChessSquare squareA4 = board.getSquareByCords(1, 4);
    ChessSquare squareC3 = board.getSquareByCords(3, 3);

    //assert
    assertTrue(board.isPathClear(squareA1, squareA4));
    assertTrue(board.isPathClear(squareA1, squareC3));
  }

  @Test
  void testIsPathClearNegative() {
    //arrange
    ChessSquare squareA1 = board.getSquareByCords(1, 1);
    ChessSquare squareA4 = board.getSquareByCords(1, 4);
    ChessSquare squareC3 = board.getSquareByCords(3, 3);

    Pawn piece1 = new Pawn(board.getSquareByCords(1, 2), ChessColor.WHITE);
    Pawn piece2 = new Pawn(board.getSquareByCords(2, 2), ChessColor.WHITE);

    board.getSquareByCords(1, 2).placePiece(piece1);
    board.getSquareByCords(2, 2).placePiece(piece2);

    //assert
    assertFalse(board.isPathClear(squareA1, squareA4));
    assertFalse(board.isPathClear(squareA1, squareC3));
  }

  @Test
  void testGetPath() {
    //arrange
    ChessSquare squareA1 = board.getSquareByCords(1, 1);
    ChessSquare squareA4 = board.getSquareByCords(1, 4);
    ChessSquare squareC3 = board.getSquareByCords(3, 3);
    //act
    List<ChessSquare> path1 = board.getPath(squareA1, squareA4);
    List<ChessSquare> path2 = board.getPath(squareA1, squareC3);
    //assert
    assertEquals(path1.size(), 2);
    assertEquals(path2.size(), 1);
  }

}
