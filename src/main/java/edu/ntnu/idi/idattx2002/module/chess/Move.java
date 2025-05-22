package edu.ntnu.idi.idattx2002.module.chess;

import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Piece;

public class Move {

  private final ChessSquare startSquare;
  private final ChessSquare endSquare;
  private final Chess chess;

  private final Piece pieceToMove;

  public Move(ChessSquare startSquare, ChessSquare endSquare, Chess chess) {
    this.startSquare = startSquare;
    this.endSquare = endSquare;
    this.chess = chess;

    pieceToMove = startSquare.getPiece();
  }

  public void execute() {
    pieceToMove.move(endSquare, chess);
  }

  public boolean successful() {
    return pieceToMove != null && startSquare.getPiece() != pieceToMove;
  }
}

