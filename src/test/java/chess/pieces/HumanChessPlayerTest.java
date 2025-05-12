package chess.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import edu.ntnu.idi.idattx2002.logic.common.Board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HumanChessPlayerTest {

  private HumanChessPlayer whitePlayer;
  private HumanChessPlayer blackPlayer;

  @BeforeEach
  public void setUp() {
    Chess chess = new Chess();
    ChessBoard board = new ChessBoard();

    whitePlayer = new HumanChessPlayer("white", 1, ChessColor.WHITE);
    blackPlayer = new HumanChessPlayer("Black", 2, ChessColor.BLACK);

    //chess.initPosition();

    whitePlayer.initPieces(board);
    blackPlayer.initPieces(board);
  }

  @Test
  public void testInitPieces() {

  }


}
