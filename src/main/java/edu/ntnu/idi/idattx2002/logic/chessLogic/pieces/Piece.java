package edu.ntnu.idi.idattx2002.logic.chessLogic.pieces;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

public abstract class Piece{

  public ChessSquare currentSquare;
  public ChessColor chessColor;
  public boolean alive;

  public Piece(ChessSquare startSquare, ChessColor chessColor) {
    initStartSquare(startSquare);
    this.chessColor = chessColor;
    alive = true;
  }

  private void initStartSquare(ChessSquare square) {
    square.placePiece(this);
    currentSquare = square;
  }

  public ChessColor getColor() {
    return chessColor;
  }

  public ChessSquare getCurrentSquare() {
    return currentSquare;
  }

  public void setCurrentSquare(ChessSquare square) {
    currentSquare = square;
  }

  public boolean isAlive() {
    return alive;
  }

  public void die() {
    alive = false;
  }

  public boolean stopsCheck(ChessSquare square, Chess chess) {
    King friendlyKing = chess.getPlayer(chessColor).getKing();
    List<Piece> putsInCheck = friendlyKing.getPutsInCheck(chess);

    for (Piece enemyPiece : friendlyKing.getPutsInCheck(chess)) {
      if (square == enemyPiece.getCurrentSquare()) {
        putsInCheck.remove(enemyPiece);
      } else {
        for (ChessSquare checkPathSquare : chess.getBoard()
            .getPath(friendlyKing.getCurrentSquare(), enemyPiece.currentSquare)) {
          if (square == checkPathSquare) {
            putsInCheck.remove(enemyPiece);
          }
        }
      }
    }
    return putsInCheck.isEmpty();
  }

  public boolean threatens(ChessSquare square, Chess chess) {
    return !square.equals(currentSquare) && chess.getBoard().isPathClear(currentSquare, square);
  }

  //TODO refactor / simplify
  public boolean discoversCheck(ChessSquare square, Chess chess) {
    ChessColor enemyColor = chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    ChessSquare friendlyKingSquare = chess.getPlayer(chessColor).getKing().getCurrentSquare();

    for (Piece enemyPiece : chess.getPlayer(enemyColor).getAlivePieces()) {
      if(enemyPiece.isMovePossible(friendlyKingSquare) && square != enemyPiece.getCurrentSquare()) {

        boolean thisPieceInPath = false;
        boolean otherPieceInPath = false;
        boolean friendlyPieceStaysInPath = false;
        for(ChessSquare pathSquare : chess.getBoard().getPath(enemyPiece.getCurrentSquare(), friendlyKingSquare)) {

            if (square == pathSquare) {
              friendlyPieceStaysInPath = true;
            }
            if (currentSquare == pathSquare) {
              thisPieceInPath = true;
            }
            else if (pathSquare.hasPiece()) {
              otherPieceInPath = true;
            }

        }
        if(thisPieceInPath && !otherPieceInPath && !friendlyPieceStaysInPath) {
          return true;
        }
      }
    }
    return false;
  }

  //Meant to be overridden
  public boolean isMovePossible(ChessSquare square) {
    return false;
  }

  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    if (!stopsCheck(square, chess)) {
      return false;
    }
    if(discoversCheck(square, chess)) {
      return false;
    }
    if(square.getPiece() == null) {
      return !square.equals(currentSquare) && chess.getBoard().isPathClear(currentSquare, square);
    }
    boolean friendOnSquare = chessColor == square.getPiece().getColor();
    return !friendOnSquare && !square.equals(currentSquare) && chess.getBoard().isPathClear(currentSquare, square);
  }

  public void move(ChessSquare square, Chess chess) {
    currentSquare.removePiece();
    square.placePiece(this);
    setCurrentSquare(square);
  }
}