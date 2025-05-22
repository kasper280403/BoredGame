package edu.ntnu.idi.idattx2002.module.chess;

import edu.ntnu.idi.idattx2002.exception.IllegalMoveException;
import edu.ntnu.idi.idattx2002.io.chess.PositionIO;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessBoard;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Pawn;
import edu.ntnu.idi.idattx2002.module.chess.pieces.Piece;
import edu.ntnu.idi.idattx2002.module.chess.player.ChessPlayer;
import edu.ntnu.idi.idattx2002.module.chess.player.HumanChessPlayer;
import edu.ntnu.idi.idattx2002.module.common.WinObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the core chess game logic and state.
 *
 * <p>Manages the board, players, current turn, move execution, position initialization, and win
 * condition.
 *
 * <p>This class also supports an observer to notify when a win condition is met.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class Chess {

  private final ChessBoard board;
  private final List<ChessPlayer> players;
  private final PositionIO positionIO;

  private ChessPlayer playerToMove;

  private WinObserver observer;

  /**
   * Constructs a new {@code Chess} game instance. Initializes the board, player list, and position
   * I/O.
   */
  public Chess() {
    positionIO = new PositionIO();
    players = new ArrayList<>();
    board = new ChessBoard();
  }

  /**
   * Returns the player with the specified chess color.
   *
   * @param chessColor the color of the player to retrieve
   * @return the corresponding {@code ChessPlayer}
   * @throws NullPointerException if no player with the given color exists
   */
  public ChessPlayer getPlayer(ChessColor chessColor) {
    for (ChessPlayer player : players) {
      if (player.getColor() == chessColor) {
        return player;
      }
    }
    throw new NullPointerException("Player not found");
  }

  /**
   * Returns the player whose turn it currently is.
   *
   * @return the current player
   */
  public ChessPlayer getPlayerToMove() {
    return playerToMove;
  }

  /**
   * Returns the current game board.
   *
   * @return the {@code ChessBoard}
   */
  public ChessBoard getBoard() {
    return board;
  }

  /**
   * Returns the list of players participating in the game.
   *
   * @return the list of {@code ChessPlayer} objects
   */
  public List<ChessPlayer> getPlayers() {
    return players;
  }

  /**
   * Registers a win observer to be notified when the game is won.
   *
   * @param observer the observer to register
   */
  public void addObserver(WinObserver observer) {
    this.observer = observer;
  }

  /**
   * Adds a human player to the game. Automatically sets WHITE to move first.
   *
   * @param player the player to add
   */
  public void addPlayer(HumanChessPlayer player) {
    players.add(player);
    playerToMove = getPlayer(ChessColor.WHITE);
  }

  /**
   * Initializes the board position using data from the given file path. Also assigns pieces to
   * their corresponding players.
   *
   * @param pathToPosition path to the position file
   */
  public void initPosition(String pathToPosition) {
    positionIO.loadPosition(this, pathToPosition);
    for (ChessPlayer player : players) {
      Piece piece;
      for (ChessSquare square : board.getSquareMap().values()) {
        if (square.hasPiece()) {
          piece = square.getPiece();
          if (piece.getColor() == player.getColor()) {
            player.addPiece(piece);
          }
        }
      }
    }
  }

  /**
   * Executes a given move and handles game logic including turn change, win check, and pawn status.
   *
   * @param move the move to play
   * @throws IllegalMoveException if the move is not successful
   */
  public void playMove(Move move) {
    move.execute();
    if (move.successful()) {
      checkForWin();
      updatePlayerToMove();
      updatePieceStatuses();
    } else {
      throw new IllegalMoveException("Move is not legal");
    }
  }

  /** Checks for checkmate conditions and notifies the observer if a player has won. */
  private void checkForWin() {
    if (players.getFirst().getKing().isInCheckMate(this)
        || players.getLast().getKing().isInCheckMate(this)) {
      notifyObserver(playerToMove);
    }
  }

  /** Switches the current player to move to the opponent. */
  public void updatePlayerToMove() {
    if (playerToMove.getColor() == ChessColor.WHITE) {
      playerToMove = getPlayer(ChessColor.BLACK);
    } else {
      playerToMove = getPlayer(ChessColor.WHITE);
    }
  }

  /** Updates the status of pawns (e.g. disabling en passant after one turn). */
  private void updatePieceStatuses() {
    for (Piece piece : getPlayerToMove().getAlivePieces()) {
      if (piece instanceof Pawn) {
        if (((Pawn) piece).getFirstMoved()) {
          ((Pawn) piece).setFirstMoved(false);
        }
      }
    }
  }

  /**
   * Notifies the registered observer that a player has won.
   *
   * @param player the winning player
   */
  private void notifyObserver(ChessPlayer player) {
    if (observer != null) {
      observer.update(player);
    }
  }
}
