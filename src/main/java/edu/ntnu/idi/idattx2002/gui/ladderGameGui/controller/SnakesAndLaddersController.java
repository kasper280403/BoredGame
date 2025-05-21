package edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller;

import edu.ntnu.idi.idattx2002.gui.common.view.WinView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersMenuView;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.DiceView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersBoardView;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import java.util.List;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersController {

  private final SnakesAndLadders game;

  private final SnakesAndLaddersView snakesAndLadderView;
  private final DiceView diceView;
  private final SnakesAndLaddersBoardView tilesView;
  private final WinView winView;


  public SnakesAndLaddersController(Pane mainPane, List<List<String>> players, String selectedGamemode) {
    snakesAndLadderView = new SnakesAndLaddersView(mainPane, this);
    game = new SnakesAndLadders();
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
      SnakesAndLaddersPlayer newPlayer = new SnakesAndLaddersPlayer(playerString.getFirst(), playerID, Integer.parseInt(playerString.getLast()));
      game.addPlayer(newPlayer);
      newPlayer.addObserver(tilesView);

      playerID ++;
    }

    for (Dice dice : game.getDices()) {
      dice.addObserver(diceView);
      diceView.initDice(dice);
    }
  }

  private void startGame() {
    snakesAndLadderView.show();
    diceView.show();
    tilesView.show();
  }

  public void playTurn() {
    game.playTurn();
  }
}
