package ladderGame.board;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.ladderGame.board.LadderGameBoard;
import edu.ntnu.idi.idattx2002.module.ladderGame.board.LadderGameSquare;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LadderGameBoardTest {

  LadderGameBoard board;

  @BeforeEach
  void setUp() {
    board = new LadderGameBoard();
  }

  @Test
  void testAddTile() {
    // arrange
    LadderGameSquare tile1 = new LadderGameSquare(1);
    // act
    board.addTile(tile1);
    // assert
    assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testGetTile() {
    // arrange
    LadderGameSquare tile1 = new LadderGameSquare(1);
    // act
    board.addTile(tile1);
    // assert
    assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testGetInvalidTile() {
    // Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          board.getTile(17);
        });
  }

  @Test
  void testCreateBoard() {
    // arrange
    board.createBoard(9, 10);
    // act
    LadderGameSquare tile1 = board.getTile(1);
    LadderGameSquare tile10 = board.getTile(10);
    LadderGameSquare tile24 = board.getTile(24);
    // assert
    assertEquals(10, tile10.getSquareId());
    assertEquals(1, tile1.getSquareId());
    assertEquals(24, tile24.getSquareId());
  }

  @Test
  void testValidateRowsAndColumns() {
    // assert
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          board.createBoard(0, 0);
        });
  }

  @Test
  void testGetTileException() {
    // assert
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          board.getTile(1);
        });
  }

  @Test
  void testGetBoard() {
    // arrange
    board.createBoard(2, 2);
    // act
    Map<Integer, LadderGameSquare> tiles = board.getSquareMap();
    // assert
    assertEquals(4, tiles.size());
  }

  @Test
  void testGetLastTile() {
    // arrange
    board.createBoard(3, 3);
    // act
    LadderGameSquare lastTile = board.getLastTile();
    // assert
    assertEquals(9, lastTile.getSquareId());
  }
}
