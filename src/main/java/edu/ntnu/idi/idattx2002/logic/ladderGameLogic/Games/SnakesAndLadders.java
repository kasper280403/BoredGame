package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games;

import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.io.ladderGameIO.BoardIO;
import edu.ntnu.idi.idattx2002.logic.common.Player.Player;
import edu.ntnu.idi.idattx2002.logic.common.WinObserver;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersBoard;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;
import java.util.Map;

public class SnakesAndLadders {

  public static Map<Integer, SnakesAndLaddersPlayer> players;

  private final SnakesAndLaddersBoard board;

  private Dice dice1;
  private Dice dice2;
  private List<Dice> dices;

  private int playerToMoveID;

  private WinObserver observer;

  public SnakesAndLadders() {
    this.board = new SnakesAndLaddersBoard();
    players = new HashMap<>();
    playerToMoveID = 1;

    init();
  }

  public List<Dice> getDices() {
    return dices;
  }

  public SnakesAndLaddersBoard getBoard() {
    return board;
  }

  public SnakesAndLaddersPlayer getPlayerToMove() {
    return players.get(playerToMoveID);
  }

  public void addObserver(WinObserver observer) {
    this.observer = observer;
  }

  public void createBoard(String gamemode) {
    board.createBoard(9, 10);
    setActions(gamemode);
  }


  public void playTurn() {
    SnakesAndLaddersPlayer player = getPlayerToMove();

    int steps = 0;
    for (Dice dice : dices) {
      steps += dice.throwDice();
    }

    player.movePlayerBySteps(steps);
    checkForWin(player);

    board.getTile(player.getCurrentTileId()).landPlayer(player);
    updatePlayerToMove();
  }

  private void updatePlayerToMove() {
    if (playerToMoveID >= players.size()) {
      playerToMoveID = 1;
    } else {
      playerToMoveID += 1;
    }
  }

  public void checkForWin(SnakesAndLaddersPlayer player) {
    if (player.getCurrentTileId() >= board.getSquareMap().size()) {
      observer.update(player);
    }
  }

  public void addPlayer(SnakesAndLaddersPlayer player) {
    validatePlayer(player);
    players.put(player.getPlayerID(), player);
  }

  private void validatePlayer(SnakesAndLaddersPlayer player) {
    for (SnakesAndLaddersPlayer existingPlayer : players.values()) {
      if(player.getName() == existingPlayer.getName()) {
        throw new IlliegalGameArgumentException("Two players can not have the same name");
      }
      if(player.getIconId() == existingPlayer.getIconId()) {
        throw new IlliegalGameArgumentException("Two players can not have the same icon");
      }
    }
  }

  private void setActions(String gameID) {
    BoardIO boardIO = new BoardIO(board);
    boardIO.setActions(gameID);
  }

  private void initDices() {
    this.dice1 = new Dice();
    this.dice2 = new Dice();

    dices = new ArrayList<>();
    dices.add(dice1);
    dices.add(dice2);
  }

  private void init() {
    initDices();
  }
}
