package edu.ntnu.idi.idattx2002.logic.chessLogic.player;

import edu.ntnu.idi.idattx2002.logic.common.Player.Player;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;

public abstract class ChessPlayer extends Player {

  private ChessColor chessColor;

  private List<Piece> pieces;

  public ChessPlayer(String name, int iconId, ChessColor chessColor) {
    super(name, iconId);
    this.chessColor = chessColor;
  }

  public List<Piece> getAlivePieces() {
    List<Piece> alivePieces = new ArrayList<>();
    for (Piece piece : pieces) {
      if(piece.isAlive()) {
        alivePieces.add(piece);
      }
    }
    return alivePieces;
  }

  public King getKing() {
    for(Piece piece : pieces) {
      if(piece instanceof King) {
        return (King) piece;
      }
    }
    throw new NullPointerException("King not found");
  }

  public void initPieces(ChessBoard board) {
    pieces = new ArrayList<>();

    Piece piece;
    for (ChessSquare square : board.getSquareMap().values()) {
      if(square.hasPiece()) {
        piece = square.getPiece();
        if(piece.getColor() == chessColor) {
          pieces.add(piece);
        }
      }
    }
  }

  public void addPiece(Piece piece) {
    pieces.add(piece);
  }

  public void removePiece(Piece piece) {
    pieces.remove(piece);
  }

  public ChessColor getColor() {
    return chessColor;
  }

}
