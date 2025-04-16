package edu.ntnu.idi.idattx2002.logic.chessLogic;

import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;

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

  //TODO move into Piece class
  private boolean isMoveLegal() {
    return chess.getPlayerToMove().getColor() == pieceToMove.getColor();
  }

  public void execute() {
    if (isMoveLegal()) {
      if(pieceToMove.getCurrentSquare() == null) {
        System.out.println("DEN SUGER");
      }
      else {
        System.out.println(pieceToMove.getCurrentSquare().getSquareId());
      }
      pieceToMove.move(endSquare, chess);
    }
  }

  public boolean successful() {
    return pieceToMove != null && startSquare.getPiece() != pieceToMove;
  }
}

