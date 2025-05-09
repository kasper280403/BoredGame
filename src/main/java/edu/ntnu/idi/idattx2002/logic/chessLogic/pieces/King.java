package edu.ntnu.idi.idattx2002.logic.chessLogic.pieces;

import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;

public class King extends Piece {

  private boolean hasMoved;

  public King(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    hasMoved = false;
  }

  public List<Piece> getPutsInCheck(Chess chess) {
    List<Piece> putsInCheck = new ArrayList<>();

    ChessColor enemyChessColor = chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    List<Piece> enemyPieces = chess.getPlayer(enemyChessColor).getAlivePieces();

    for (Piece piece : enemyPieces) {
      if (piece.threatens(currentSquare, chess)) {
        putsInCheck.add(piece);
      }
    }
    return putsInCheck;
  }

  public boolean isInCheck(Chess chess) {
    return !getPutsInCheck(chess).isEmpty();
  }

  //TODO refactor by implementing stopsCheck() ?
  public boolean isInCheckMate(Chess chess) {
    if (!isInCheck(chess)) {
      return false;
    }
    for (ChessSquare square : chess.getBoard().getSquareMap().values()) {
      for (Piece friendlyPiece : chess.getPlayer(chessColor).getAlivePieces()) {
        if (friendlyPiece.isMoveLegal(square, chess)) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean isCastleLegal(ChessSquare square, Chess chess) {
    if(isInCheck(chess) || hasMoved || Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate()) < 2) {
      return false;
    }

    boolean canCastle = false;

    List<Rook> rooks = new ArrayList<>();
    for (Piece piece : chess.getPlayer(chessColor).getAlivePieces()) {
      if(piece instanceof Rook) {
        rooks.add((Rook) piece);
      }
    }

    for(Rook rook : rooks) {
      if (canCastleWithRook(square, rook, chess)){
        canCastle = true;
      }
    }
    return canCastle;
  }

  public boolean canCastleWithRook(ChessSquare square, Rook rook, Chess chess) {
    boolean canCastle = false;

    ChessColor enemyChessColor = chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    if (!rook.hasMoved) {
      ChessSquare rookSquare = rook.getCurrentSquare();
      List<ChessSquare> castlePath = chess.getBoard().getPath(getCurrentSquare(), rookSquare);

      for (ChessSquare castleSquare : castlePath) {
        if (square == castleSquare || square == rookSquare) {
         canCastle = true;
        }
      }

      for (ChessSquare castleSquare : castlePath) {
        for (Piece piece : chess.getPlayer(enemyChessColor).getAlivePieces()) {
          int squareDistanceFromKing = Math.abs(
              currentSquare.getXCoordinate() - castleSquare.getXCoordinate());
          if (squareDistanceFromKing <= 2 && piece.isMoveLegal(castleSquare, chess)) {
            canCastle = false;
          }
        }
      }
    }
    return canCastle;
  }

  public void castle(ChessSquare square, Chess chess) {
    List<Rook> rooks = new ArrayList<>();
    for (Piece piece : chess.getPlayer(chessColor).getAlivePieces()) {
      if(piece instanceof Rook) {
        rooks.add((Rook) piece);
      }
    }

    boolean performed = false;
    for (Rook rook : rooks) {
      if(canCastleWithRook(square, rook, chess) && !performed) {
        ChessSquare kingSquare = currentSquare;
        ChessSquare rookSquare = rook.getCurrentSquare();

        int kingXCoordinate = currentSquare.getXCoordinate();
        int kingYCoordinate = currentSquare.getYCoordinate();
        int rookXCoordinate = rookSquare.getXCoordinate();
        int directionFactor = kingXCoordinate - rookXCoordinate > 0 ? 1 : -1;

        ChessSquare newKingSquare = chess.getBoard().getSquareByCords(kingXCoordinate - 2 * directionFactor, kingYCoordinate);
        ChessSquare newRookSquare = chess.getBoard().getSquareByCords(kingXCoordinate - 1 * directionFactor, kingYCoordinate);

        kingSquare.removePiece();
        rookSquare.removePiece();

        newKingSquare.placePiece(this);
        newRookSquare.placePiece(rook);

        setCurrentSquare(newKingSquare);
        rook.setCurrentSquare(newRookSquare);

        rook.hasMoved = true;
        hasMoved = true;

        performed = true;
      }
    }

  }

  @Override
  public boolean isMovePossible(ChessSquare square) {
    int xDiff = Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate());
    int yDiff = Math.abs(currentSquare.getYCoordinate() - square.getYCoordinate());
    return xDiff <= 1 && yDiff <= 1;
  }

  //TODO simplify by removing second if statement
  @Override
  public boolean stopsCheck(ChessSquare square, Chess chess) {
    ChessColor enemyChessColor = chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;

    for (Piece enemyPiece : chess.getPlayer(enemyChessColor).getAlivePieces()) {
      if (enemyPiece.threatens(square, chess) && !square.hasPiece()) {
        return false;
      }
      else if(enemyPiece.threatens(square, chess) && square.hasPiece()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  @Override
  public void move(ChessSquare square, Chess chess) {
    if(isMoveLegal(square, chess)) {
      super.move(square, chess);
      hasMoved = true;
    }
    else if(isCastleLegal(square, chess)) {
      castle(square, chess);
    }
  }
}
