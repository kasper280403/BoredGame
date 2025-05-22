package chess.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Move;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Bishop;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class PieceTestSetUp {

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
    chess.playMove(new Move(board.getSquareByCords(2, 7), board.getSquareByCords(2, 6), chess));

    whiteBishop = (Bishop) board.getSquareByCords(3, 1).getPiece();
    blackBishop = (Bishop) board.getSquareByCords(3, 8).getPiece();
  }

}
