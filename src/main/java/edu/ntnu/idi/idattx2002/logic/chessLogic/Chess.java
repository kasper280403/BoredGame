package edu.ntnu.idi.idattx2002.logic.chessLogic;

import edu.ntnu.idi.idattx2002.exception.IllegalMoveException;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessSquare;
import edu.ntnu.idi.idattx2002.logic.common.WinObserver;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanChessPlayer;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.ChessPlayer;

public class Chess {

  private final ChessBoard board;
  private final List<ChessPlayer> players;
  private final PositionIO positionIO;

  private ChessPlayer playerToMove;

  private WinObserver observer;

  public Chess() {
    positionIO = new PositionIO();
    players = new ArrayList<>();
    board = new ChessBoard();

  }

  public ChessPlayer getPlayer(ChessColor chessColor) {
    for (ChessPlayer player : players) {
      if(player.getColor() == chessColor) {
        return player;
      }
    }
    throw new NullPointerException("Player not found");
  }

  public ChessPlayer getPlayerToMove() {
    return playerToMove;
  }

  public ChessBoard getBoard() {
    return board;
  }

  public List<ChessPlayer> getPlayers() {
    return players;
  }

  public void addObserver(WinObserver observer) {
    this.observer = observer;
  }

  public void addPlayer(HumanChessPlayer player) {
    players.add(player);
    playerToMove = getPlayer(ChessColor.WHITE);
  }

  public void initPosition(String pathToPosition) {
    positionIO.loadPosition(this, pathToPosition);
    for(ChessPlayer player : players) {
      Piece piece;
      for (ChessSquare square : board.getSquareMap().values()) {
        if(square.hasPiece()) {
          piece = square.getPiece();
          if(piece.getColor() == player.getColor()) {
            player.addPiece(piece);
          }
        }
      }
    }
  }

  public void playMove(Move move) {
    move.execute();
    if(move.successful()) {
      checkForWin();
      updatePlayerToMove();
      updatePieceStatuses();
    }
    else {
      throw new IllegalMoveException("Move is not legal");
    }
  }

  private void checkForWin() {
    if(players.getFirst().getKing().isInCheckMate(this) || players.getLast().getKing().isInCheckMate(this)) {
      notifyObserver(playerToMove);
    }
  }

  public void updatePlayerToMove() {
    if (playerToMove.getColor() == ChessColor.WHITE) {
      playerToMove = getPlayer(ChessColor.BLACK);
    }
    else{
      playerToMove = getPlayer(ChessColor.WHITE);
    }
  }

  private void updatePieceStatuses() {
    for(Piece piece : getPlayerToMove().getAlivePieces()) {
      if(piece instanceof Pawn) {
        if(((Pawn) piece).getFirstMoved()) {
          ((Pawn) piece).setFirstMoved(false);
        }
      }
    }
  }

  private void notifyObserver(ChessPlayer player) {
    if (observer != null) {
      observer.update(player);
    }
  }
}
