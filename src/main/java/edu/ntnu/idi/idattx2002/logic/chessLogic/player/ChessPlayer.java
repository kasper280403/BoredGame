package edu.ntnu.idi.idattx2002.logic.chessLogic.player;

import edu.ntnu.idi.idattx2002.logic.common.Player.Player;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.*;

public abstract class ChessPlayer extends Player {

  private final ChessColor chessColor;

  private final List<Piece> pieces;

  public ChessPlayer(String name, int iconId, ChessColor chessColor) {
    super(name, iconId);
    this.chessColor = chessColor;
    pieces = new ArrayList<>();
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
    if(piece.getColor() != chessColor) {
      throw new IllegalArgumentException("Piece color has to match player color");
    }
    pieces.add(piece);
  }

  public void removePiece(Piece piece) {
    if(!pieces.contains(piece)) {
      throw new NullPointerException("Cannot remove Piece wich isnt a part of pieces");
    }
    pieces.remove(piece);
  }

  public ChessColor getColor() {
    return chessColor;
  }

}
