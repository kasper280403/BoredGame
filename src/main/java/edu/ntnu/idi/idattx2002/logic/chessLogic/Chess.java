package edu.ntnu.idi.idattx2002.logic.chessLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.board.Board;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.King;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Pawn;
import edu.ntnu.idi.idattx2002.logic.chessLogic.pieces.Piece;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.HumanPlayer;
import edu.ntnu.idi.idattx2002.logic.chessLogic.player.Player;

public class Chess {

  private Board board;
  private List<Player> players;
  private PositionIO positionIO;

  private List<String> positionHistory;

  private Player playerToMove;

  private WinObserver observer;

  //Debugg
  King whiteKing;
  King blackKing;

  public Chess() throws IOException {
    positionIO = new PositionIO();

    players = new ArrayList<>();
    add2HumanPlayers();

    board = new Board();

    positionHistory = new ArrayList<>();
  }

  public void addObserver(WinObserver observer) {
    this.observer = observer;
  }

  public void addPlayer(String name, ChessColor chessColor) {
    Player player = new HumanPlayer(name, chessColor);
    players.add(player);

    playerToMove = getPlayer(ChessColor.WHITE);
  }

  public void initPosition(String pathToPosition) throws IOException {
    positionIO.loadPosition(this, pathToPosition);
    for(Player player : players) {
      player.initPieces(board);
    }
    whiteKing = getPlayer(ChessColor.WHITE).getKing();
    blackKing = getPlayer(ChessColor.BLACK).getKing();

    positionHistory.add(positionIO.getPositionString(this));
  }

  public void revertMove() throws IOException {
    //positionIO.loadPositionFromString(this, positionHistory.get(4));
    positionIO.loadPosition(this, "startPositions/standardPosition.txt");

    for(Player player : players) {
      player.initPieces(board);
    }
    whiteKing = getPlayer(ChessColor.WHITE).getKing();
    blackKing = getPlayer(ChessColor.BLACK).getKing();

    //positionHistory.removeFirst();

  }

  public void playMove(Move move) {
    move.execute();
    if(move.successful()) {
      checkForWin();
      updatePlayerToMove();
      updatePieceStatuses();
      positionHistory.addFirst(positionIO.getPositionString(this));
      System.out.println("__________________ \n" + positionIO.getPositionString(this));
    }
  }

  private void checkForWin() {
    if(whiteKing.isInCheck(this) || blackKing.isInCheck(this)){
      if(whiteKing.isInCheckMate(this) || blackKing.isInCheckMate(this)) {
        notifyObserver(playerToMove);
      }
    }
  }

  public Board getBoard() {
    return board;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Player getPlayer(ChessColor chessColor) {
    for (Player player : players) {
      if(player.getColor() == chessColor) {
        return player;
      }
    }
    throw new NullPointerException("Player not found");

  }

  public Player getPlayerToMove() {
    return playerToMove;
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

  private void notifyObserver(Player player) {
    if (observer != null) {
      observer.update(player);
    }
  }

  //debug
  public void add2HumanPlayers() {
    addPlayer("Player1", ChessColor.WHITE);
    addPlayer("Player2", ChessColor.BLACK);
  }

}
