package edu.ntnu.idi.idattx2002.module.chess.pieces;
import java.util.List;
import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;

/**
 * Represents a generic chess piece with core behavior such as movement, capturing, and legality checks.
 * <p>
 * Specific piece types (e.g. Rook, Bishop) must extend this class and implement {@code isMovePossible()}.
 * </p>
 *
 * <p>
 * This class handles:
 * <ul>
 *   <li>Tracking the piece's position and state (alive/dead)</li>
 *   <li>Determining whether a move is legal</li>
 *   <li>Checking if a move stops or discovers check</li>
 *   <li>Executing movement and updating board state</li>
 * </ul>
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public abstract class Piece{

  public ChessSquare currentSquare;
  public ChessColor chessColor;
  public boolean alive;

  /**
   * Constructs a piece at the specified square and with the specified color.
   *
   * @param startSquare the starting square of the piece
   * @param chessColor the color of the piece
   */
  public Piece(ChessSquare startSquare, ChessColor chessColor) {
    initStartSquare(startSquare);
    this.chessColor = chessColor;
    alive = true;
  }

  private void initStartSquare(ChessSquare square) {
    square.placePiece(this);
    currentSquare = square;
  }

  /**
   * Returns the color of the piece.
   *
   * @return the chess color
   */
  public ChessColor getColor() {
    return chessColor;
  }

  /**
   * Returns the square where this piece is currently located.
   *
   * @return the current square
   */
  public ChessSquare getCurrentSquare() {
    return currentSquare;
  }

  /**
   * Sets the current square of the piece.
   *
   * @param square the new square
   */
  public void setCurrentSquare(ChessSquare square) {
    currentSquare = square;
  }

  /**
   * Checks if the piece is still active on the board.
   *
   * @return {@code true} if alive, {@code false} if captured
   */
  public boolean isAlive() {
    return alive;
  }


  /**
   * Marks the piece as captured.
   */
  public void die() {
    alive = false;
  }


  /**
   * Determines whether moving to the specified square stops a check on the friendly king.
   *
   * @param square the destination square
   * @param chess the chess game state
   * @return {@code true} if the move stops the check, {@code false} otherwise
   */
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

  /**
   * Checks whether this piece threatens a given square.
   *
   * @param square the target square
   * @param chess the chess game state
   * @return {@code true} if the square is threatened, {@code false} otherwise
   */
  public boolean threatens(ChessSquare square, Chess chess) {
    return !square.equals(currentSquare) && chess.getBoard().isPathClear(currentSquare, square);
  }


  /**
   * Determines whether moving this piece would expose the king to a discovered check.
   *
   * @param square the destination square
   * @param chess the chess game state
   * @return {@code true} if it causes discovered check, {@code false} otherwise
   */
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

  /**
   * Determines whether the piece is allowed to move to the specified square.
   * <p>
   * This method must be implemented by all specific piece types.
   * </p>
   *
   * @param square the target square
   * @return {@code true} if the move is possible by rules of the piece
   */
  public abstract boolean isMovePossible(ChessSquare square);

  /**
   * Checks if moving to a given square is legal in the current game state.
   * Considers move rules, check avoidance, and board constraints.
   *
   * @param square the target square
   * @param chess the current game
   * @return {@code true} if the move is legal, {@code false} otherwise
   */
  public boolean isMoveLegal(ChessSquare square, Chess chess) {
    if(chess.getPlayerToMove().getColor() != chessColor) {
      return false;
    }
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

  /**
   * Moves the piece to a new square and updates its state on the board.
   *
   * @param square the destination square
   * @param chess the current game state
   */
  public void move(ChessSquare square, Chess chess) {
    currentSquare.removePiece();
    square.placePiece(this);
    setCurrentSquare(square);
  }
}