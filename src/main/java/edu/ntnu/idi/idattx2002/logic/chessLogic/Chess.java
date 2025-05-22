package edu.ntnu.idi.idattx2002.logic.chessLogic;

import edu.ntnu.idi.idattx2002.exception.IlliegalMoveException;
import edu.ntnu.idi.idattx2002.logic.common.WinObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.ChessBoard;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.King;
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

  private void addPlayer(String name, int iconId, ChessColor chessColor) {
    ChessPlayer player = new HumanChessPlayer(name, iconId, chessColor);
    players.add(player);

    playerToMove = getPlayer(ChessColor.WHITE);
  }

  public void initPosition(String pathToPosition) {
    positionIO.loadPosition(this, pathToPosition);
    for(ChessPlayer player : players) {
      player.initPieces(board);
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
      throw new IlliegalMoveException("Move is not legal");
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

  public void addHumanPlayers(List<List<String>> players) {
    List<String> player1 = players.get(0);
    List<String> player2 = players.get(1);


    addPlayer(player1.getFirst(), Integer.parseInt(player1.getLast()), ChessColor.WHITE);
    addPlayer(player2.getFirst(), Integer.parseInt(player2.getLast()), ChessColor.BLACK);
  }
}
