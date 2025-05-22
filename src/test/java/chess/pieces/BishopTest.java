package chess.pieces;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Move;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Bishop;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import edu.ntnu.idi.idattx2002.logic.common.Board.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {

  private Chess chess;
  private ChessBoard board;

  private Bishop whiteBishop;
  private Bishop blackBishop;

  @BeforeEach
  void setUp() {
    chess = new Chess();

    chess.addPlayer(new HumanChessPlayer("Si", 1, ChessColor.WHITE));
    chess.addPlayer(new HumanChessPlayer("Ka", 1, ChessColor.BLACK));

    chess.initPosition("startPositions/standardPosition.txt");
    board = chess.getBoard();

    //Opens 2 bishop paths
    chess.playMove(new Move(board.getSquareByCords(2, 2), board.getSquareByCords(2, 3), chess));
    chess.playMove(new Move(board.getSquareByCords(2, 7), board.getSquareByCords(2, 3), chess));

    whiteBishop = (Bishop) board.getSquareByCords(3, 1).getPiece();
    blackBishop = (Bishop) board.getSquareByCords(3, 8).getPiece();
  }

  @Test
  void testLegalMove() {
    //arrange
    ChessSquare whiteBishopDestination = board.getSquareByCords(1, 3);
    ChessSquare blackBishopDestination = board.getSquareByCords(2, 2);

    //act
    whiteBishop.move(whiteBishopDestination, chess);
    blackBishop.move(blackBishopDestination, chess);

    //assert
    assertEquals(whiteBishop.getCurrentSquare(), whiteBishopDestination);
    assertEquals(blackBishop.getCurrentSquare(), blackBishopDestination);
  }
}
