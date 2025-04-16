import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersSquare;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Board;

class BoardTest {

  Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testAddTile() {
    //arrange
    SnakesAndLaddersSquare tile1 = new SnakesAndLaddersSquare(1);
    //act
    board.addTile(tile1);
    //assert
    Assertions.assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testGetTile() {
    //arrange
    SnakesAndLaddersSquare tile1 = new SnakesAndLaddersSquare(1);
    //act
    board.addTile(tile1);
    //assert
    Assertions.assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testCreateBoard() {
    //arrange
    board.createBoard(9, 10);
    //act
    SnakesAndLaddersSquare tile1 = board.getTile(1);
    SnakesAndLaddersSquare tile10 = board.getTile(10);
    SnakesAndLaddersSquare tile24 = board.getTile(24);
    //assert
    Assertions.assertEquals(10, tile10.getSquareId());
    Assertions.assertEquals(1, tile1.getSquareId());
    Assertions.assertEquals(24, tile24.getSquareId());
  }

  @Test
  void testValidateRowsAndColumns() {
    //assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      board.validateRowsAndColumns(0, 0);
    });
  }

  @Test
  void testGetTileException() {
    //assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      board.getTile(1);
    });
  }

  @Test
  void testGetBoard() {
    //arrange
    board.createBoard(2, 2);
    //act
    HashMap<Integer, SnakesAndLaddersSquare> tiles = board.getTiles();
    //assert
    assertEquals(4, tiles.size());
  }

  @Test
  void testGetLastTile() {
    //arrange
    board.createBoard(3, 3);
    //act
    SnakesAndLaddersSquare lastTile = board.getLastTile();
    //assert
    assertEquals(9, lastTile.getSquareId());
  }
}
