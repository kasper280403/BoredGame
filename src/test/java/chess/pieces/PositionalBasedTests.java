package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.exception.IllegalMoveException;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Move;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.King;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Queen;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionalBasedTests {

  private Chess chess;
  public ChessBoard board;

  @BeforeEach
  void setUp() {
    chess = new Chess();

    chess.addPlayer(new HumanChessPlayer("Si", 1, ChessColor.WHITE));
    chess.addPlayer(new HumanChessPlayer("Ka", 1, ChessColor.BLACK));

    chess.initPosition("startPositions/standardPosition.txt");
    board = chess.getBoard();
  }

  @Test
  void testCastle() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 3), chess));
    chess.playMove(new Move(board.getSquareByCords(5, 7), board.getSquareByCords(5, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(6, 1), board.getSquareByCords(4, 3), chess));
    chess.playMove(new Move(board.getSquareByCords(1, 7), board.getSquareByCords(1, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(7, 1), board.getSquareByCords(6, 3), chess));
    chess.playMove(new Move(board.getSquareByCords(2, 7), board.getSquareByCords(2, 6), chess));

    King whiteKing = chess.getPlayer(ChessColor.WHITE).getKing();

    //act
    chess.playMove(new Move(board.getSquareByCords(5, 1), board.getSquareByCords(7, 1), chess));

    //assert
    assertEquals(whiteKing.getCurrentSquare(), board.getSquareByCords(7, 1));
  }

  @Test
  void testMoveIntoFriendlyPiece() {
    //assert
    assertThrows(IllegalMoveException.class, () -> {
      chess.playMove(new Move(board.getSquareByCords(5, 1), board.getSquareByCords(4, 1), chess));;
    });
  }

  @Test
  void testAnPassant() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(1, 7), board.getSquareByCords(1, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(5, 4), board.getSquareByCords(5, 5), chess));
    chess.playMove(new Move(board.getSquareByCords(6, 7), board.getSquareByCords(6, 5), chess));

    Pawn pawnToPerform = (Pawn) board.getSquareByCords(5, 5).getPiece();
    Pawn pawnToBeCaptured = (Pawn) board.getSquareByCords(6, 5).getPiece();


    //act
    chess.playMove(new Move(board.getSquareByCords(5, 5), board.getSquareByCords(6, 6), chess));

    //assert
    assertEquals(pawnToPerform.getCurrentSquare(), board.getSquareByCords(6, 6));
    assertFalse(pawnToBeCaptured.isAlive());
  }

  @Test
  void testAnPassantNotPossible() {
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(1, 7), board.getSquareByCords(1, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(5, 4), board.getSquareByCords(5, 5), chess));
    chess.playMove(new Move(board.getSquareByCords(6, 7), board.getSquareByCords(6, 5), chess));

    chess.playMove(new Move(board.getSquareByCords(4, 2), board.getSquareByCords(4, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(2, 7), board.getSquareByCords(2, 6), chess));

    //assert
    assertThrows(IllegalMoveException.class, () -> {
      chess.playMove(new Move(board.getSquareByCords(5, 5), board.getSquareByCords(6, 6), chess));
    });
  }

  @Test
  void testPromotion() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(1, 7), board.getSquareByCords(1, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(5, 4), board.getSquareByCords(5, 5), chess));
    chess.playMove(new Move(board.getSquareByCords(6, 7), board.getSquareByCords(6, 5), chess));

    chess.playMove(new Move(board.getSquareByCords(5, 5), board.getSquareByCords(5, 6), chess));
    chess.playMove(new Move(board.getSquareByCords(2, 7), board.getSquareByCords(2, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(5, 6), board.getSquareByCords(4, 7), chess));
    chess.playMove(new Move(board.getSquareByCords(5, 8), board.getSquareByCords(6, 7), chess));

    //act
    chess.playMove(new Move(board.getSquareByCords(4, 7), board.getSquareByCords(3, 8), chess));

    //assert
    assertInstanceOf(Queen.class, board.getSquareByCords(3, 8).getPiece());
  }

  @Test
  void testCanNotMoveDueToBeingInCheck() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(6, 7), board.getSquareByCords(6, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(4, 1), board.getSquareByCords(8, 5), chess));

    //assert
    assertThrows(IllegalMoveException.class, () -> {
      chess.playMove(new Move(board.getSquareByCords(1, 7), board.getSquareByCords(1, 6), chess));
    });
    assertTrue(chess.getPlayer(ChessColor.BLACK).getKing().isInCheck(chess));
  }

  @Test
  void testCanBlockOutOfCheck() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(5, 2), board.getSquareByCords(5, 4), chess));
    chess.playMove(new Move(board.getSquareByCords(6, 7), board.getSquareByCords(6, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(4, 1), board.getSquareByCords(8, 5), chess));

    //act
    chess.playMove(new Move(board.getSquareByCords(7, 7), board.getSquareByCords(7, 6), chess));

    //assert
    assertFalse(chess.getPlayer(ChessColor.BLACK).getKing().isInCheck(chess));
  }

  @Test
  void testCheckMate() {
    //arrange
    chess.playMove(new Move(board.getSquareByCords(6, 2), board.getSquareByCords(6, 3), chess));
    chess.playMove(new Move(board.getSquareByCords(5, 7), board.getSquareByCords(5, 6), chess));

    chess.playMove(new Move(board.getSquareByCords(7, 2), board.getSquareByCords(7, 4), chess));

    //act
    chess.playMove(new Move(board.getSquareByCords(4, 8), board.getSquareByCords(8, 4), chess));

    //assert
    assertTrue(chess.getPlayer(ChessColor.WHITE).getKing().isInCheckMate(chess));
    assertFalse(chess.getPlayer(ChessColor.BLACK).getKing().isInCheckMate(chess));
  }
}
