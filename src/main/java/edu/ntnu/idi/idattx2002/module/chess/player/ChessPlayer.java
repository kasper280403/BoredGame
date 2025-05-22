package edu.ntnu.idi.idattx2002.module.chess.player;

import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.pieces.*;
import edu.ntnu.idi.idattx2002.module.common.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a chess game.
 *
 * <p>Each player has a name, icon, color, and a list of pieces under their control. Provides
 * methods to manage and retrieve the player's active pieces, including their king.
 *
 * <p>This class is abstract and intended to be extended by specific player types (e.g. human, AI).
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public abstract class ChessPlayer extends Player {

  private final ChessColor chessColor;

  private final List<Piece> pieces;

  /**
   * Constructs a new {@code ChessPlayer} with the given name, icon ID, and color.
   *
   * @param name the name of the player
   * @param iconId the icon ID representing the player
   * @param chessColor the color assigned to this player (WHITE or BLACK)
   */
  public ChessPlayer(String name, int iconId, ChessColor chessColor) {
    super(name, iconId);
    this.chessColor = chessColor;
    pieces = new ArrayList<>();
  }

  /**
   * Returns a list of all currently alive pieces under the control of this player.
   *
   * @return list of alive {@code Piece} objects
   */
  public List<Piece> getAlivePieces() {
    List<Piece> alivePieces = new ArrayList<>();
    for (Piece piece : pieces) {
      if (piece.isAlive()) {
        alivePieces.add(piece);
      }
    }
    return alivePieces;
  }

  /**
   * Retrieves the king controlled by this player.
   *
   * @return the {@code King} piece
   * @throws NullPointerException if no king is found
   */
  public King getKing() {
    for (Piece piece : pieces) {
      if (piece instanceof King) {
        return (King) piece;
      }
    }
    throw new NullPointerException("King not found");
  }

  /**
   * Adds a piece to the player's control.
   *
   * @param piece the piece to add
   * @throws IllegalArgumentException if the piece's color doesn't match the player's color
   */
  public void addPiece(Piece piece) {
    if (piece.getColor() != chessColor) {
      throw new IllegalArgumentException("Piece color has to match player color");
    }
    pieces.add(piece);
  }

  /**
   * Removes a piece from the player's control.
   *
   * @param piece the piece to remove
   * @throws NullPointerException if the piece is not found in the list
   */
  public void removePiece(Piece piece) {
    if (!pieces.contains(piece)) {
      throw new NullPointerException("Cannot remove Piece wich isnt a part of pieces");
    }
    pieces.remove(piece);
  }

  /**
   * Returns the color assigned to this player.
   *
   * @return the {@code ChessColor} of the player
   */
  public ChessColor getColor() {
    return chessColor;
  }
}
