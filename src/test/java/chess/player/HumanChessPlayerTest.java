package chess.player;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.King;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Pawn;
import edu.ntnu.idi.idattx2002.module.chess.player.HumanChessPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HumanChessPlayerTest {

  private HumanChessPlayer whitePlayer;
  private HumanChessPlayer blackPlayer;

  @BeforeEach
  void setUp() {
    whitePlayer = new HumanChessPlayer("white", 1, ChessColor.WHITE);
    blackPlayer = new HumanChessPlayer("black", 1, ChessColor.BLACK);
  }

  @Test
  void testGetName() {
    //act
    String whiteName = whitePlayer.getName();
    String blackName = blackPlayer.getName();

    //assert
    assertEquals(whiteName, "white");
    assertEquals(blackName, "black");
  }

  @Test
  void testGetColor() {
    //act
    ChessColor white = whitePlayer.getColor();
    ChessColor black = blackPlayer.getColor();
    //assert
    assertEquals(white, ChessColor.WHITE);
    assertEquals(black, ChessColor.BLACK);
  }

  @Test
  void testAddPiece() {
    //arrange
    Pawn piece = new Pawn(new ChessSquare(1, 1), ChessColor.WHITE);
    //act
    whitePlayer.addPiece(piece);
    //assert
    assertEquals(1, whitePlayer.getAlivePieces().size());
  }

  @Test
  void testAddWrongColorPiece() {
    //arrange
    Pawn piece = new Pawn(new ChessSquare(1, 1), ChessColor.WHITE);
    //assert
    assertThrows(IllegalArgumentException.class, () -> {
      blackPlayer.addPiece(piece);
    });
  }

  @Test
  void testGetKing() {
    //arrange
    King king = new King(new ChessSquare(1, 1), ChessColor.WHITE);
    whitePlayer.addPiece(king);
    //assert
    assertEquals(whitePlayer.getKing(), king);
  }

  @Test
  void testGetKingNotFound() {
    //assert
    assertThrows(NullPointerException.class, () -> {
      blackPlayer.getKing();
    });
  }

  @Test
  void testRemovePiece() {
    //arrange
    Pawn blackPiece = new Pawn(new ChessSquare(1, 1), ChessColor.BLACK);
    Pawn whitePiece = new Pawn(new ChessSquare(2, 1), ChessColor.WHITE);

    whitePlayer.addPiece(whitePiece);
    blackPlayer.addPiece(blackPiece);

    //act
    whitePlayer.removePiece(whitePiece);

    //assert
    assertEquals(whitePlayer.getAlivePieces().size(), 0);
    assertEquals(blackPlayer.getAlivePieces().size(), 1);
  }

  @Test
  void testRemoveNonExistingPiece() {
    //arrange
    Pawn piece = new Pawn(new ChessSquare(1, 1), ChessColor.BLACK);
    //assert
    assertThrows(NullPointerException.class, () -> {
      blackPlayer.removePiece(piece);
    });
  }


}
