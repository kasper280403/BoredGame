package edu.ntnu.idi.idattx2002.Modules.Games;

import edu.ntnu.idi.idattx2002.Modules.Board.BoardDAO;
import edu.ntnu.idi.idattx2002.view.DiceWindow;
import edu.ntnu.idi.idattx2002.view.PieceWindow;
import edu.ntnu.idi.idattx2002.view.SnakesAndLadderWindow;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Dice.Dice;
import edu.ntnu.idi.idattx2002.Modules.Player.Player;
import edu.ntnu.idi.idattx2002.view.TilesWindow;
import edu.ntnu.idi.idattx2002.view.Win.WinWindow;
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
  private WinWindow winView;
  private PieceWindow pieceWindow;

  public SnakesAndLadders(SnakesAndLadderWindow window) {
    this.board = new Board();
    players = new HashMap<>();
    this.dice = new Dice();
    playerToMoveID = 1;

    //Move into init
    createBoard();

    //Should be moved
    pieceWindow = new PieceWindow();
    diceView = new DiceWindow(window);
    tilesView = new TilesWindow(10, 9, 50, this, window);
    winView = new WinWindow();

  }

  //Should be moved
  public DiceWindow getDiceView() {
    return diceView;
  }
  public TilesWindow getTilesView() {
    return tilesView;
  }
  public PieceWindow getPieceWindow() {
    return pieceWindow;
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

  public void setActions(String gameID) {
    BoardDAO boardDAO = new BoardDAO(board);
    boardDAO.setActions(gameID);
  }


  public void winSequence(Player player) {
    PauseTransition pause = new PauseTransition(Duration.millis(5000));
    pause.setOnFinished(event -> {
      SnakesAndLadderWindow.closeStage();
      winView.createStage(player.getPieceID(), player.getPlayerName());
    });

    pause.play();

  }
}
