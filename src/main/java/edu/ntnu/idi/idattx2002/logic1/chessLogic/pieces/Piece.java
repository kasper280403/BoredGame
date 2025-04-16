package edu.ntnu.idi.idattx2002.logic1.chessLogic.pieces;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.Square;

public abstract class Piece{

  public Square currentSquare;
  public ChessColor chessColor;
  public boolean alive;

  public Piece(Square startSquare, ChessColor chessColor) {
    initStartSquare(startSquare);
    this.chessColor = chessColor;
    alive = true;
  }

  private void initStartSquare(Square square) {
    square.placePiece(this);
    currentSquare = square;
  }

  public ChessColor getColor() {
    return chessColor;
  }

  public Square getCurrentSquare() {
    return currentSquare;
  }

  public void setCurrentSquare(Square square) {
    currentSquare = square;
  }

  public boolean isAlive() {
    return alive;
  }

  public void die() {
    alive = false;
  }

  public boolean stopsCheck(Square square, Chess chess) {
    King friendlyKing = chess.getPlayer(chessColor).getKing();
    List<Piece> putsInCheck = friendlyKing.getPutsInCheck(chess);

    for (Piece enemyPiece : friendlyKing.getPutsInCheck(chess)) {
      if (square == enemyPiece.getCurrentSquare()) {
        putsInCheck.remove(enemyPiece);
      } else {
        for (Square checkPathSquare : chess.getBoard()
            .getPath(friendlyKing.getCurrentSquare(), enemyPiece.currentSquare)) {
          if (square == checkPathSquare) {
            putsInCheck.remove(enemyPiece);
          }
        }
      }
    }
    return putsInCheck.isEmpty();
  }

  public boolean threatens(Square square, Chess chess) {
    return !square.equals(currentSquare) && chess.getBoard().isPathClear(currentSquare, square);
  }

  //TODO refactor / simplify
  public boolean discoversCheck(Square square, Chess chess) {
    ChessColor enemyColor = chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    Square friendlyKingSquare = chess.getPlayer(chessColor).getKing().getCurrentSquare();

    for (Piece enemyPiece : chess.getPlayer(enemyColor).getAlivePieces()) {
      if(enemyPiece.isMovePossible(friendlyKingSquare) && square != enemyPiece.getCurrentSquare()) {

        boolean thisPieceInPath = false;
        boolean otherPieceInPath = false;
        boolean friendlyPieceStaysInPath = false;
        for(Square pathSquare : chess.getBoard().getPath(enemyPiece.getCurrentSquare(), friendlyKingSquare)) {

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
  public boolean isMovePossible(Square square) {
    return false;
  }

  public boolean isMoveLegal(Square square, Chess chess) {
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

  public void move(Square square, Chess chess) {
    currentSquare.removePiece();
    square.placePiece(this);
    setCurrentSquare(square);
  }
}