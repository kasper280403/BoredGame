package edu.ntnu.idi.idattx2002.logic.chessLogic.pieces;

import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

public class Pawn extends Piece{

  private boolean hasMoved;
  private boolean justMoved;

  public Pawn(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    justMoved = false;
    hasMoved = false;
  }

  public boolean getFirstMoved() {
    return justMoved;
  }

  public void setFirstMoved(boolean has) {
    justMoved = has;
  }

  public void updateStatus() {
    if(!hasMoved) {
      justMoved = true;
      hasMoved = true;
    }
    else{
      justMoved = false;
    }
  }

  private boolean squareForEnPassant() {
    return ((currentSquare.getYCoordinate() == 4 && chessColor == ChessColor.BLACK) ||
            (currentSquare.getYCoordinate() == 5 && chessColor == ChessColor.WHITE));
  }

  private boolean checkForEnPassant(Chess chess, ChessSquare square) {
    if(!squareForEnPassant()) {
      return false;
    }

    int yOffsett = square.getXCoordinate() == 4 ? 1 : -1;
    ChessSquare squareToCapture = chess.getBoard().getSquareByCords(square.getXCoordinate(), square.getYCoordinate() + yOffsett);
    if(!squareToCapture.hasPiece()) {
      return false;
    }

    Piece pieceToCapture = squareToCapture.getPiece();
    if (!(pieceToCapture instanceof Pawn)) {
      return false;
    }
    if(((Pawn) pieceToCapture).getFirstMoved()) {
      return true;
    }
    else {
      return false;
    }
  }

  private void performEnPassant(ChessSquare square, Chess chess) {
    int yOffsett = square.getXCoordinate() == 4 ? 1 : -1;
    ChessSquare enemySquare = chess.getBoard().getSquareByCords(square.getXCoordinate(), square.getYCoordinate() + yOffsett);
    Piece enemyPiece = enemySquare.getPiece();

    enemySquare.removePiece();
    enemyPiece.die();

    currentSquare.removePiece();
    square.placePiece(this);
    setCurrentSquare(square);
  }

  private boolean isDiagMovePossible(ChessSquare square) {
    if(square.hasPiece()) {
      return square.getPiece().getColor() != chessColor;
    }
    return false;
  }

  private boolean isStraightMovePossible(ChessSquare square) {
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();
    int xDiff = currentSquare.getXCoordinate() - square.getXCoordinate();
    if(chessColor == ChessColor.WHITE) {
      if(currentSquare.getYCoordinate() == 2 && square.getYCoordinate() == 4) {
        yDiff += 1;
      }
      return yDiff == -1 && xDiff == 0 && !square.hasPiece();
    }
    else {
      if(currentSquare.getYCoordinate() == 7 && square.getYCoordinate() == 5) {
        yDiff -= 1;
      }
      return yDiff == 1 && xDiff == 0 && !square.hasPiece();
    }
  }

  //TODO Allow for user input somewhere
  private void promote(Chess chess, String pieceType) {
    if(currentSquare.getYCoordinate() != 1 && currentSquare.getYCoordinate() != 8) {
      throw new IllegalArgumentException("Pawn which isnt on the final rank shouldnt be promoted");
    }

    currentSquare.removePiece();
    chess.getPlayer(chessColor).removePiece(this);

    Piece promotedPiece;
    switch(pieceType) {
      case("QUEEN") -> promotedPiece = new Queen(currentSquare, chessColor);
      case("ROOK") -> promotedPiece = new Rook(currentSquare, chessColor);
      case("BISHOP") -> promotedPiece = new Bishop(currentSquare, chessColor);
      case("HORSE") -> promotedPiece = new Horse(currentSquare, chessColor);
      default -> throw new IllegalArgumentException("Invalid piece type");
    }

    chess.getPlayer(chessColor).addPiece(promotedPiece);
    currentSquare.placePiece(promotedPiece);
  }

  //TODO not compatible with discoversCheck maybe.
  @Override
  public boolean isMovePossible(ChessSquare square) {
    boolean legal = false;
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();
    int xDiff = currentSquare.getXCoordinate() - square.getXCoordinate();

    if(chessColor == ChessColor.WHITE) {
      yDiff *= -1;
    }

    if(xDiff == 0) {
      legal = isStraightMovePossible(square);
    }
    else if(Math.abs(xDiff) == yDiff && yDiff == 1) {
      legal = isDiagMovePossible(square);
    }
    return legal;
  }

  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    int xDiff = Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate());
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();

    if (chessColor == ChessColor.WHITE) {
      yDiff *= -1;
    }

    return (xDiff == 1 && yDiff == 1) && super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
      updateStatus();

      if(currentSquare.getYCoordinate() == 1 || currentSquare.getYCoordinate() == 8) {
        promote(chess, "QUEEN");
      }
    }
    else if (Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate()) == 1 &&
            Math.abs(currentSquare.getYCoordinate() - square.getYCoordinate()) == 1) {
      if(checkForEnPassant(chess, square)) {
        performEnPassant(square, chess);
        updateStatus();
      }
    }
  }

}
