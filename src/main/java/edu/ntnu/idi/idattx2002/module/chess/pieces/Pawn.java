package edu.ntnu.idi.idattx2002.module.chess.pieces;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a Pawn piece in chess.
 *
 * <p>Pawns can move forward, capture diagonally, perform en passant captures, and promote upon
 * reaching the final rank. This class implements all relevant pawn behavior including move
 * validation, state tracking, and promotion.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Pawn extends Piece {

  private boolean hasMoved;
  private boolean justMoved;

  /**
   * Constructs a {@code Pawn} with a starting square and color.
   *
   * @param startSquare the square the pawn starts on
   * @param chessColor the color of the pawn
   */
  public Pawn(ChessSquare startSquare, ChessColor chessColor) {
    super(startSquare, chessColor);
    justMoved = false;
    hasMoved = false;
  }

  /**
   * Returns whether this pawn has just moved two squares forward (used for en passant).
   *
   * @return {@code true} if just moved two squares; otherwise {@code false}
   */
  public boolean getFirstMoved() {
    return justMoved;
  }

  /**
   * Sets the "just moved" flag for this pawn.
   *
   * @param has {@code true} if it just moved; otherwise {@code false}
   */
  public void setFirstMoved(boolean has) {
    justMoved = has;
  }

  /** Updates the pawn's movement status after moving. Used to track eligibility for en passant. */
  public void updateStatus() {
    if (!hasMoved) {
      justMoved = true;
      hasMoved = true;
    } else {
      justMoved = false;
    }
  }

  private boolean squareForEnPassant() {
    return ((currentSquare.getYCoordinate() == 4 && chessColor == ChessColor.BLACK)
        || (currentSquare.getYCoordinate() == 5 && chessColor == ChessColor.WHITE));
  }

  private boolean checkForEnPassant(Chess chess, ChessSquare square) {
    if (!squareForEnPassant()) {
      return false;
    }

    int yOffsett = square.getXCoordinate() == 4 ? 1 : -1;
    ChessSquare squareToCapture =
        chess
            .getBoard()
            .getSquareByCords(square.getXCoordinate(), square.getYCoordinate() + yOffsett);
    if (!squareToCapture.hasPiece()) {
      return false;
    }

    Piece pieceToCapture = squareToCapture.getPiece();
    if (!(pieceToCapture instanceof Pawn)) {
      return false;
    }
    return ((Pawn) pieceToCapture).getFirstMoved();
  }

  private void performEnPassant(ChessSquare square, Chess chess) {
    int yOffsett = square.getXCoordinate() == 4 ? 1 : -1;
    ChessSquare enemySquare =
        chess
            .getBoard()
            .getSquareByCords(square.getXCoordinate(), square.getYCoordinate() + yOffsett);
    Piece enemyPiece = enemySquare.getPiece();

    enemySquare.removePiece();
    enemyPiece.die();

    currentSquare.removePiece();
    square.placePiece(this);
    setCurrentSquare(square);
  }

  private boolean isDiagMovePossible(ChessSquare square) {
    if (square.hasPiece()) {
      return square.getPiece().getColor() != chessColor;
    }
    return false;
  }

  private boolean isStraightMovePossible(ChessSquare square) {
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();
    int xDiff = currentSquare.getXCoordinate() - square.getXCoordinate();
    if (chessColor == ChessColor.WHITE) {
      if (currentSquare.getYCoordinate() == 2 && square.getYCoordinate() == 4) {
        yDiff += 1;
      }
      return yDiff == -1 && xDiff == 0 && !square.hasPiece();
    } else {
      if (currentSquare.getYCoordinate() == 7 && square.getYCoordinate() == 5) {
        yDiff -= 1;
      }
      return yDiff == 1 && xDiff == 0 && !square.hasPiece();
    }
  }

  private void promote(Chess chess, String pieceType) {
    if (currentSquare.getYCoordinate() != 1 && currentSquare.getYCoordinate() != 8) {
      throw new IllegalArgumentException("Pawn which isnt on the final rank shouldnt be promoted");
    }

    currentSquare.removePiece();
    chess.getPlayer(chessColor).removePiece(this);

    Piece promotedPiece;
    switch (pieceType) {
      case ("QUEEN") -> promotedPiece = new Queen(currentSquare, chessColor);
      case ("ROOK") -> promotedPiece = new Rook(currentSquare, chessColor);
      case ("BISHOP") -> promotedPiece = new Bishop(currentSquare, chessColor);
      case ("HORSE") -> promotedPiece = new Horse(currentSquare, chessColor);
      default -> throw new IllegalArgumentException("Invalid piece type");
    }

    chess.getPlayer(chessColor).addPiece(promotedPiece);
    currentSquare.placePiece(promotedPiece);
  }

  /**
   * Determines whether a move to the specified square is possible according to pawn rules.
   *
   * @param square the target square
   * @return {@code true} if the move is valid for a pawn; otherwise {@code false}
   */
  @Override
  public boolean isMovePossible(ChessSquare square) {
    boolean legal = false;
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();
    int xDiff = currentSquare.getXCoordinate() - square.getXCoordinate();

    if (chessColor == ChessColor.WHITE) {
      yDiff *= -1;
    }

    if (xDiff == 0) {
      legal = isStraightMovePossible(square);
    } else if (Math.abs(xDiff) == yDiff && yDiff == 1) {
      legal = isDiagMovePossible(square);
    }
    return legal;
  }

  /**
   * Determines if this pawn threatens a given square.
   *
   * @param square the square to check
   * @param chess the current game state
   * @return {@code true} if the square is threatened; otherwise {@code false}
   */
  @Override
  public boolean threatens(ChessSquare square, Chess chess) {
    int xDiff = Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate());
    int yDiff = currentSquare.getYCoordinate() - square.getYCoordinate();

    if (chessColor == ChessColor.WHITE) {
      yDiff *= -1;
    }

    return (xDiff == 1 && yDiff == 1) && super.threatens(square, chess);
  }

  /**
   * Checks whether a move is legal for the pawn, including standard and diagonal captures.
   *
   * @param square the destination square
   * @param chess the current game
   * @return {@code true} if the move is legal; otherwise {@code false}
   */
  @Override
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    return isMovePossible(square) && super.isMoveLegal(square, chess);
  }

  /**
   * Moves the pawn to the specified square, handling promotion and en passant if applicable.
   *
   * @param square the destination square
   * @param chess the current game state
   */
  @Override
  public void move(ChessSquare square, Chess chess) {
    if (isMoveLegal(square, chess)) {
      super.move(square, chess);
      updateStatus();

      if (currentSquare.getYCoordinate() == 1 || currentSquare.getYCoordinate() == 8) {
        promote(chess, "QUEEN");
      }
    } else if (Math.abs(currentSquare.getXCoordinate() - square.getXCoordinate()) == 1
        && Math.abs(currentSquare.getYCoordinate() - square.getYCoordinate()) == 1) {
      if (checkForEnPassant(chess, square)) {
        performEnPassant(square, chess);
        updateStatus();
      }
    }
  }
}
