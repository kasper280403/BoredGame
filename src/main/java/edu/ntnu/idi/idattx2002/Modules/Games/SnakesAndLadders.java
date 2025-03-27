package edu.ntnu.idi.idattx2002.Modules.Games;

import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.Modules.DiceObserver;
import edu.ntnu.idi.idattx2002.Modules.PlayerObserver;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.PieceWindow;
import edu.ntnu.idi.idattx2002.view.SnakesAndLadderWindow;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import edu.ntnu.idi.idattx2002.view.WinWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.HashMap;

public class SnakesAndLadders {

  private final Board board;
  public static HashMap<Integer, Player> players;
  private final Dice dice1;
  private final Dice dice2;
  private List<Dice> dices;

  private int playerToMoveID;
  private DiceWindow diceWindow;
  private PieceWindow pieceWindow;

  public SnakesAndLadders(DiceWindow diceWindow) {
    this.board = new Board();
    players = new HashMap<>();
    this.dice1 = new Dice();
    this.dice2 = new Dice();
    playerToMoveID = 1;

    this.diceWindow = diceWindow;

    dices = new ArrayList<>();
    dices.add(dice1);
    dices.add(dice2);

    //Move into init
    createBoard();
  }

  public List<Dice> getDice() {
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
    setLandActions();
  }


  public void playTurn() {
    System.out.println("PlayTurn");
    Player player = getPlayerToMove();
    int diceA = dice1.throwDice();
    int diceB = dice2.throwDice();
    int steps = diceA + diceB;
    diceWindow.throwDice(diceA, diceB);

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

  //Add observer instead of tilesView variable?
  public void addPlayer(String playerName, int pieceID, TilesWindow tilesView) {
    int playerID = players.size() + 1;
    Player player = new Player(playerName, playerID, pieceID);

    player.addObserver(tilesView);
    players.put(playerID, player);
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
