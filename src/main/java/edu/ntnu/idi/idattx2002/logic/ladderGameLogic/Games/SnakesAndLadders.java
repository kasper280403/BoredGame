package edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games;

import edu.ntnu.idi.idattx2002.io.ladderGameIO.BoardIO;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.DiceView;
import edu.ntnu.idi.idattx2002.gui.common.view.PlayerIconWindow;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersBoard;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersBoardView;
import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;

public class SnakesAndLadders {

  private final SnakesAndLaddersBoard board;
  public static HashMap<Integer, SnakesAndLaddersPlayer> players;
  private Dice dice1;
  private Dice dice2;
  private List<Dice> dices;

  private int playerToMoveID;
  private DiceView diceView;
  private PlayerIconWindow playerIconWindow;

  public SnakesAndLadders(DiceView diceView) {
    this.board = new SnakesAndLaddersBoard();
    players = new HashMap<>();
    playerToMoveID = 1;

    initDices();
    //this.diceWindow = diceWindow;

    //Move into init
    createBoard();
  }

  public List<Dice> getDices() {
    return dices;
  }

  public HashMap<Integer, SnakesAndLaddersPlayer> getPlayers() {
    return players;
  }

  public SnakesAndLaddersBoard getBoard() {
    return board;
  }

  private SnakesAndLaddersPlayer getPlayerToMove() {
    return players.get(playerToMoveID);
  }

  public void createBoard() {
    board.createBoard(9, 10);

    //gameID has to be choosen from the available games in the BoardSetUp.csv
    String gameID = "RegularSnakesAndLadders";
    setActions(gameID);
  }


  public void playTurn() {
    System.out.println("PlayTurn");
    SnakesAndLaddersPlayer player = getPlayerToMove();

    int steps = 0;
    for (Dice dice : dices) {
      steps += dice.throwDice();
    }

    player.movePlayerBySteps(steps);

    if (checkForWin(player)) {
      System.out.println("Win hit!");
      player.movePlayerToTile(board.getLastTile().getSquareId());
      //winSequence(player);
    }

    board.getTile(player.getCurrentTileId()).landPlayer(player);

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

  public boolean checkForWin(SnakesAndLaddersPlayer player) {
    return player.getCurrentTileId() >= board.getSquareMap().size();
  }

  //TODO refactor so that Player object is created and observer added in controller
  public void addPlayer(List<String> player, SnakesAndLaddersBoardView tilesView) {
    int playerID = players.size() + 1;
    SnakesAndLaddersPlayer newPlayer = new SnakesAndLaddersPlayer(player.getFirst(), playerID, Integer.parseInt(player.getLast()));

    newPlayer.addObserver(tilesView);
    players.put(playerID, newPlayer);
  }

  public void setActions(String gameID) {
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


  /*
  public void winSequence(Player player) {
    PauseTransition pause = new PauseTransition(Duration.millis(5000));
    pause.setOnFinished(event -> {
      SnakesAndLadderWindow.closeStage();
      winView.createStage(player.getPieceID(), player.getPlayerName());
    });

    pause.play();

  }
   */
}
