package chess.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.King;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumanChessPlayerTest {

  private HumanChessPlayer whitePlayer;
  private HumanChessPlayer blackPlayer;

  @BeforeEach
  public void setUp() {

    whitePlayer = new HumanChessPlayer("white", 1, ChessColor.WHITE);
    blackPlayer = new HumanChessPlayer("Black", 2, ChessColor.BLACK);

    whitePlayer.addPiece(new King(new ChessSquare(5, 1), ChessColor.WHITE));
    blackPlayer.addPiece(new King(new ChessSquare(5, 8), ChessColor.BLACK));
  }

  @Test
  public void testGetKing() {
    //act
    Piece whiteKing = whitePlayer.getKing();
    Piece blackKing = blackPlayer.getKing();

    //assert
    assertEquals(whiteKing.getColor(), ChessColor.WHITE);
    assertEquals(blackKing.getColor(), ChessColor.BLACK);

    assertInstanceOf(King.class, whiteKing);
    assertInstanceOf(King.class, blackKing);
  }

  @Test
  public void testAddPiece() {
    //act
    whitePlayer.addPiece(new Pawn(new ChessSquare(1, 2), ChessColor.WHITE));
    blackPlayer.addPiece(new Pawn(new ChessSquare(1, 7), ChessColor.BLACK));

    int nrOfWhitePieces = whitePlayer.getAlivePieces().size();
    int nrOfBlackPieces = blackPlayer.getAlivePieces().size();

    //assert
    assertEquals(nrOfWhitePieces, 2);
    assertEquals(nrOfBlackPieces, 2);
  }

  @Test
  public void testRemovePiece() {
    Pawn whitePieceToRemove = new Pawn(new ChessSquare(1, 2), ChessColor.WHITE);
    Pawn blackPieceToRemove = new Pawn(new ChessSquare(1, 7), ChessColor.BLACK);

    whitePlayer.addPiece(whitePieceToRemove);
    blackPlayer.addPiece(blackPieceToRemove);
    whitePlayer.addPiece(new Pawn(new ChessSquare(2, 2), ChessColor.WHITE));
    blackPlayer.addPiece(new Pawn(new ChessSquare(2, 7), ChessColor.BLACK));

    whitePlayer.removePiece(whitePieceToRemove);
    blackPlayer.removePiece(blackPieceToRemove);

    int nrOfWhitePieces = whitePlayer.getAlivePieces().size();
    int nrOfBlackPieces = blackPlayer.getAlivePieces().size();

    //assert
    assertEquals(nrOfWhitePieces, 2);
    assertEquals(nrOfBlackPieces, 2);
  }

  @Test
  public void testGetColor() {
    //act
    ChessColor white = whitePlayer.getColor();
    ChessColor black = blackPlayer.getColor();

    //assert
    assertEquals(white, ChessColor.WHITE);
    assertEquals(black, ChessColor.BLACK);
  }
}
