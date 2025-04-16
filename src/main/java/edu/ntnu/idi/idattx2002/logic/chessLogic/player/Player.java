package edu.ntnu.idi.idattx2002.logic.chessLogic.player;

import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.Board;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.Square;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;

public abstract class Player {

  private ChessColor chessColor;

  private List<Piece> pieces;

  public Player(ChessColor chessColor) {
    this.chessColor = chessColor;
  }

  public void initPieces(Board board) {
    pieces = new ArrayList<>();

    Piece piece;
    for (Square square : board.getSquareMap().values()) {
      if(square.hasPiece()) {
        piece = square.getPiece();
        if(piece.getColor() == chessColor) {
          pieces.add(piece);
        }
      }
    }
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
