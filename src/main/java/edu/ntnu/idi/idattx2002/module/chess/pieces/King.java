package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the King piece in chess.
 *
 * <p>The king can move one square in any direction and is the most important piece in the game.
 * This class includes logic for checking, checkmate, and castling.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class King extends Piece {

  private boolean hasMoved;

  /**
   * Constructs a {@code King} with a starting square and color.
   *
   * @param startSquare the square the king starts on
   * @param chessColor the color of the king
   */
  public King(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    hasMoved = false;
  }

  /**
   * Returns a list of enemy pieces currently putting this king in check.
   *
   * @param chess the current game state
   * @return list of pieces threatening the king
   */
  public List<Piece> getPutsInCheck(Chess chess) {
    List<Piece> putsInCheck = new ArrayList<>();

    ChessColor enemyChessColor =
        chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    List<Piece> enemyPieces = chess.getPlayer(enemyChessColor).getAlivePieces();

    for (Piece piece : enemyPieces) {
      if (piece.threatens(currentSquare, chess)) {
        putsInCheck.add(piece);
      }
    }
    return putsInCheck;
  }

  /**
   * Checks whether this king is currently in check.
   *
   * @param chess the current game state
   * @return {@code true} if in check, {@code false} otherwise
   */
  public boolean isInCheck(Chess chess) {
    return !getPutsInCheck(chess).isEmpty();
  }

  /**
   * Checks whether this king is in checkmate.
   *
   * @param chess the current game state
   * @return {@code true} if in checkmate, {@code false} otherwise
   */
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

  /**
   * Determines whether castling to the given square is legal for this king.
   *
   * @param square the destination square
   * @param chess the current game state
   * @return {@code true} if castling is legal, {@code false} otherwise
   */
  private boolean isCastleLegal(ChessSquare square, Chess chess) {
    if (isInCheck(chess)
        || hasMoved
        || Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate()) < 2) {
      return false;
    }

    boolean canCastle = false;

    List<Rook> rooks = new ArrayList<>();
    for (Piece piece : chess.getPlayer(chessColor).getAlivePieces()) {
      if (piece instanceof Rook) {
        rooks.add((Rook) piece);
      }
    }

    for (Rook rook : rooks) {
      if (canCastleWithRook(square, rook, chess)) {
        canCastle = true;
      }
    }
    return canCastle;
  }

  private boolean canCastleWithRook(ChessSquare square, Rook rook, Chess chess) {
    boolean canCastle = false;

    ChessColor enemyChessColor =
        chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
    if (!rook.hasMoved()) {
      ChessSquare rookSquare = rook.getCurrentSquare();
      List<ChessSquare> castlePath = chess.getBoard().getPath(getCurrentSquare(), rookSquare);

      for (ChessSquare castleSquare : castlePath) {
        if (square == castleSquare || square == rookSquare) {
          canCastle = true;
        }
      }

      for (ChessSquare castleSquare : castlePath) {
        for (Piece piece : chess.getPlayer(enemyChessColor).getAlivePieces()) {
          int squareDistanceFromKing =
              Math.abs(currentSquare.getXCoordinate() - castleSquare.getXCoordinate());
          if (squareDistanceFromKing <= 2 && piece.isMoveLegal(castleSquare, chess)) {
            canCastle = false;
          }
        }
      }
    }
    return canCastle;
  }

  private void castle(ChessSquare square, Chess chess) {
    List<Rook> rooks = new ArrayList<>();
    for (Piece piece : chess.getPlayer(chessColor).getAlivePieces()) {
      if (piece instanceof Rook) {
        rooks.add((Rook) piece);
      }
    }

    boolean performed = false;
    for (Rook rook : rooks) {
      if (canCastleWithRook(square, rook, chess) && !performed) {
        ChessSquare kingSquare = currentSquare;
        ChessSquare rookSquare = rook.getCurrentSquare();

        int kingXCoordinate = currentSquare.getXCoordinate();
        int kingYCoordinate = currentSquare.getYCoordinate();
        int rookXCoordinate = rookSquare.getXCoordinate();
        int directionFactor = kingXCoordinate - rookXCoordinate > 0 ? 1 : -1;

        ChessSquare newKingSquare =
            chess
                .getBoard()
                .getSquareByCords(kingXCoordinate - 2 * directionFactor, kingYCoordinate);
        ChessSquare newRookSquare =
            chess
                .getBoard()
                .getSquareByCords(kingXCoordinate - 1 * directionFactor, kingYCoordinate);

        kingSquare.removePiece();
        rookSquare.removePiece();

        newKingSquare.placePiece(this);
        newRookSquare.placePiece(rook);

        setCurrentSquare(newKingSquare);
        rook.setCurrentSquare(newRookSquare);

        rook.setMoved(true);
        hasMoved = true;

        performed = true;
      }
    }
  }

  /**
   * Checks whether a basic move (non-castling) is valid for the king. The king can move one square
   * in any direction.
   *
   * @param square the target square
   * @return {@code true} if the move is possible, {@code false} otherwise
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    int xDiff = Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate());
    int yDiff = Math.abs(currentSquare.getYCoordinate() - square.getYCoordinate());
    return xDiff <= 1 && yDiff <= 1;
  }

  /**
   * Determines whether moving to a given square prevents check.
   *
   * @param square the square to move to
   * @param chess the current game state
   * @return {@code true} if the move prevents check, {@code false} otherwise
   */
  @Override
  public boolean stopsCheck(ChessSquare square, Chess chess) {
    ChessColor enemyChessColor =
        chessColor == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;

    for (Piece enemyPiece : chess.getPlayer(enemyChessColor).getAlivePieces()) {
      if (enemyPiece.threatens(square, chess) && !square.hasPiece()) {
        return false;
      } else if (enemyPiece.threatens(square, chess) && square.hasPiece()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines whether this king threatens the given square.
   *
   * @param square the square to check
   * @param chess the current game state
   * @return {@code true} if the king threatens the square, {@code false} otherwise
   */
  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.threatens(square, chess);
  }

  /**
   * Checks whether a move is legal for the king, including checks for threats and friendly pieces.
   *
   * @param square the destination square
   * @param chess the current game
   * @return {@code true} if the move is legal, {@code false} otherwise
   */
  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  /**
   * Moves the king to the specified square if the move is legal. If castling is legal, performs
   * castling instead.
   *
   * @param square the destination square
   * @param chess the current game state
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if (isMoveLegal(square, chess)) {
      super.move(square, chess);
      hasMoved = true;
    } else if (isCastleLegal(square, chess)) {
      castle(square, chess);
    }
  }
}
