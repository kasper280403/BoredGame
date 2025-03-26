package edu.ntnu.idi.idattx2002.Modules.Games;

import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.SnakesAndLadderWindow;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import edu.ntnu.idi.idattx2002.view.WinWindow;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.HashMap;

public class SnakesAndLadders {

  private final Board board;
  public static HashMap<Integer, Player> players;
  private final Dice dice;
  private int playerToMoveID;
  private DiceWindow diceView;
  private TilesWindow tilesView;

  public SnakesAndLadders() {
    this.board = new Board();
    players = new HashMap<>();
    this.dice = new Dice();
    playerToMoveID = 1;

    //Should be moved
    diceView = new DiceWindow();
    tilesView = new TilesWindow();

  }

  //Should be moved
  public DiceWindow getDiceView() {
    return diceView;
  }
  public TilesWindow getTilesView() {
    return tilesView;
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
  }


  public void playTurn() {
    Player player = getPlayerToMove();
    int diceA = dice.throwDice();
    int diceB = dice.throwDice();
    int steps = diceA + diceB;
    diceView.throwDice(diceA, diceB);

    player.movePlayerBySteps(steps);

    if (checkForWin(player)) {
      System.out.println("Win hit!");
      player.movePlayerToTile(board.getLastTile().getTileId());
      winSequence(player);
      updatePlayerPositions();
    }

    board.getTile(player.getCurrentTile()).landPlayer(player);

    board.getTile(player.getCurrentTile()).landPlayer(player);
    updatePlayerToMove();

    updatePlayerPositions();
  }

  private void updatePlayerPositions(){
    for (Player player : players.values()){
      tilesView.displayPieceAtTile(player.getCurrentTile(), player.getPieceID());
    }
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

  public void addPlayer(String playerName, int pieceID) {
    int playerID = players.size() + 1;
    players.put(playerID, new Player(playerName, playerID, pieceID));
  }

  public void setLandActions() {
    setLadderActions();
    setSwitchWithRandomActions();
    //deBugActions();
  }

  public void setLadderActions() {
    board.getTile(24).setLandAction(new LadderAction(5));
    board.getTile(33).setLandAction(new LadderAction(3));
    board.getTile(36).setLandAction(new LadderAction(52));
    board.getTile(43).setLandAction(new LadderAction(62));
    board.getTile(49).setLandAction(new LadderAction(79));
    board.getTile(56).setLandAction(new LadderAction(37));
    board.getTile(64).setLandAction(new LadderAction(27));
    board.getTile(68).setLandAction(new LadderAction(85));
    board.getTile(74).setLandAction(new LadderAction(12));
    board.getTile(87).setLandAction(new LadderAction(70));
  }
  
  public void deBugActions(){

    board.getTile(2).setLandAction(new LadderAction(80));
    board.getTile(3).setLandAction(new LadderAction(80));
    board.getTile(4).setLandAction(new LadderAction(80));
    board.getTile(5).setLandAction(new LadderAction(80));
    board.getTile(6).setLandAction(new LadderAction(80));
    board.getTile(7).setLandAction(new LadderAction(80));
    board.getTile(8).setLandAction(new LadderAction(80));
    board.getTile(9).setLandAction(new LadderAction(80));
    board.getTile(10).setLandAction(new LadderAction(80));
    board.getTile(11).setLandAction(new LadderAction(80));
    board.getTile(12).setLandAction(new LadderAction(80));
    board.getTile(13).setLandAction(new LadderAction(80));
  }

  public void setSwitchWithRandomActions() {
    board.getTile(88).setLandAction(new SwitchWithRandomAction());
    board.getTile(9).setLandAction(new SwitchWithRandomAction());
    board.getTile(66).setLandAction(new SwitchWithRandomAction());
    board.getTile(87).setLandAction(new SwitchWithRandomAction());


  }

  public static void winSequence(Player player) {
    PauseTransition pause = new PauseTransition(Duration.millis(5000));
    pause.setOnFinished(event -> {
      SnakesAndLadderWindow.closeStage();
      WinWindow.createStage(player.getPieceID(), player.getPlayerName());
    });

    pause.play();

  }
}
