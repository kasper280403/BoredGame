package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.exception.IllegalMoveException;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Move;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.King;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdvancedPieceTests {

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

}
