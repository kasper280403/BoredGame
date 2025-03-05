import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;

class BoardTest {

  Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testAddTile() {
    //arrange
    Tile tile1 = new Tile(1);
    //act
    board.addTile(tile1);
    //assert
    Assertions.assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testGetTile() {
    //arrange
    Tile tile1 = new Tile(1);
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
    Tile tile1 = board.getTile(1);
    Tile tile10 = board.getTile(10);
    Tile tile24 = board.getTile(24);
    //assert
    Assertions.assertEquals(10, tile10.getTileId());
    Assertions.assertEquals(1, tile1.getTileId());
    Assertions.assertEquals(24, tile24.getTileId());
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
    HashMap<Integer, Tile> tiles = board.getTiles();
    //assert
    assertEquals(4, tiles.size());
  }

  @Test
  void testGetLastTile() {
    //arrange
    board.createBoard(3, 3);
    //act
    Tile lastTile = board.getLastTile();
    //assert
    assertEquals(9, lastTile.getTileId());
  }
}
