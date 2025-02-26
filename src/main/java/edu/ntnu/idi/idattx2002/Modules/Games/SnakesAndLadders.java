package edu.ntnu.idi.idattx2002.Modules.Games;

import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.GameWindow;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.view.TilesWindow;

import java.util.HashMap;

public class SnakesAndLadders {

  private final Board board;
  public static HashMap<Integer, Player> players;
  private final Dice dice;
  private GameWindow gameWindow;
  private int playerToMoveID;

  public SnakesAndLadders() {
    this.board = new Board();
    players = new HashMap<>();
    this.dice = new Dice();
    playerToMoveID = 1;
  }

  public Dice getDice() {
    return dice;
  }

  public HashMap<Integer, Player> getPlayers() {
    return players;
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
    DiceWindow.throwDice(diceA, diceB);

    player.movePlayerBySteps(steps);
    TilesWindow.displayPieceAtTile(player.getCurrentTile(), player.getPieceID());
    if (checkForWin(player)){
      //player has won
      player.movePlayerToTile(board.getLastTile().getTileId());
      // winSequence(player)
    }else {
      updatePlayerToMove();
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
  }

  public void setLadderActions() {
    board.getTile(1).setLandAction(new LadderAction(40));
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

  public void setSwitchWithRandomActions() {
    board.getTile(8).setLandAction(new SwitchWithRandomAction());
    board.getTile(42).setLandAction(new SwitchWithRandomAction());
    board.getTile(82).setLandAction(new SwitchWithRandomAction());
  }
}
