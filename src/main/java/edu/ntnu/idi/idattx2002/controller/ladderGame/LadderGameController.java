package edu.ntnu.idi.idattx2002.controller.ladderGame;

import edu.ntnu.idi.idattx2002.view.common.WinView;
import edu.ntnu.idi.idattx2002.module.ladderGame.Dice.Dice;
import edu.ntnu.idi.idattx2002.module.ladderGame.LadderGame;
import edu.ntnu.idi.idattx2002.view.ladderGame.DiceView;
import edu.ntnu.idi.idattx2002.view.ladderGame.SnakesAndLaddersView;
import edu.ntnu.idi.idattx2002.view.ladderGame.SnakesAndLaddersBoardView;
import edu.ntnu.idi.idattx2002.module.ladderGame.Player.LadderGamePlayer;
import java.util.List;
import javafx.scene.layout.Pane;

/**
 * Controller for the Snakes and Ladders game module.
 * <p>
 * Manages game initialization, player setup, view coordination, and game turn execution.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class LadderGameController {

  private final LadderGame game;

  private final SnakesAndLaddersView snakesAndLadderView;
  private final DiceView diceView;
  private final SnakesAndLaddersBoardView tilesView;
  private final WinView winView;


  public LadderGameController(Pane mainPane, List<List<String>> players, String selectedGamemode) {
    snakesAndLadderView = new SnakesAndLaddersView(mainPane, this);
    game = new LadderGame();
    game.createBoard(selectedGamemode);

    diceView = new DiceView(snakesAndLadderView.getLeftPane());
    tilesView = new SnakesAndLaddersBoardView(10, 9, 75, game, snakesAndLadderView.getCenterPane());
    tilesView.displayLandActionsAtTile(game);

    winView = new WinView(mainPane);
    game.addObserver(winView);

    setUpGame(players);
    startGame();
  }

  private void setUpGame(List<List<String>> players) {
    int playerID = 1;
    for (List<String> playerString : players) {
      LadderGamePlayer newPlayer = new LadderGamePlayer(playerString.getFirst(), playerID, Integer.parseInt(playerString.getLast()));
      game.addPlayer(newPlayer);
      newPlayer.addObserver(tilesView);

      playerID ++;
    }

    for (Dice dice : game.getDices()) {
      dice.addObserver(diceView);
      diceView.initDice(dice);
    }
  }

  private void updateGameInfo() {
    snakesAndLadderView.setMoveInfo("Player To Move: " + game.getPlayerToMove().getName());
  }

  private void startGame() {
    snakesAndLadderView.show();
    diceView.show();
    tilesView.show();
  }

  public void playTurn() {
    game.playTurn();
    updateGameInfo();
  }
}
