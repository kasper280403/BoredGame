package edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Games;

import edu.ntnu.idi.idattx2002.io1.ladderGameIO.BoardIO;
import edu.ntnu.idi.idattx2002.gui1.ladderGameGui.view.DiceWindow;
import edu.ntnu.idi.idattx2002.gui1.ladderGameGui.view.PieceWindow;
import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Board.Board;
import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic1.ladderGameLogic.Player.Player;
import edu.ntnu.idi.idattx2002.gui1.ladderGameGui.view.TilesWindow;
import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;

public class SnakesAndLadders {

  private final Board board;
  public static HashMap<Integer, Player> players;
  private Dice dice1;
  private Dice dice2;
  private List<Dice> dices;

  private int playerToMoveID;
  private DiceWindow diceWindow;
  private PieceWindow pieceWindow;

  public SnakesAndLadders(DiceWindow diceWindow) {
    this.board = new Board();
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

  public HashMap<Integer, Player> getPlayers() {
    return players;
  }

  public Board getBoard() {
    return board;
  }

  private Player getPlayerToMove() {
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
    Player player = getPlayerToMove();

    int steps = 0;
    for (Dice dice : dices) {
      steps += dice.throwDice();
    }

    player.movePlayerBySteps(steps);

    if (checkForWin(player)) {
      System.out.println("Win hit!");
      player.movePlayerToTile(board.getLastTile().getTileId());
      //winSequence(player);
    }

    board.getTile(player.getCurrentTile()).landPlayer(player);

    board.getTile(player.getCurrentTile()).landPlayer(player);
    updatePlayerToMove();
  }

  private void updatePlayerToMove() {
    if (playerToMoveID >= players.size()) {
      playerToMoveID = 1;
    } else {
      playerToMoveID += 1;
    }
  }

  public boolean checkForWin(Player player) {
    return player.getCurrentTile() >= board.getTiles().size();
  }

  //TODO refactor so that a List of Players is stored instead of name and pieceID
  public void addPlayer(String playerName, int pieceID, TilesWindow tilesView) {
    int playerID = players.size() + 1;
    Player player = new Player(playerName, playerID, pieceID);

    player.addObserver(tilesView);
    players.put(playerID, player);
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
