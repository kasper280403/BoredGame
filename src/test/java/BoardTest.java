import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
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
    board.createBoard(10);
    //act
    Tile tile1 = board.getTile(1);
    Tile tile10 = board.getTile(10);
    //assert
    Assertions.assertEquals(10, tile10.getTileId());
    Assertions.assertEquals(1, tile1.getTileId());
  }
}
