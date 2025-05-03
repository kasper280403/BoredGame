package edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.DiceView;
import edu.ntnu.idi.idattx2002.gui.common.view.PlayerIconWindow;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersBoardView;
import edu.ntnu.idi.idattx2002.gui.common.view.WinWindow;
import java.util.List;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersController {

  private SnakesAndLadders game;

  //Maybe move
  private SnakesAndLaddersView snakesAndLadderView;
  private PlayerIconWindow pieceView;
  private DiceView diceView;
  private SnakesAndLaddersBoardView tilesView;
  private WinWindow winView;


  public SnakesAndLaddersController(Pane mainPane, List<List<String>> players) {
    //Maybe move
    snakesAndLadderView = new SnakesAndLaddersView(mainPane, this);
    diceView = new DiceView(snakesAndLadderView);
    game = new SnakesAndLadders(diceView);

    diceView = new DiceView(snakesAndLadderView);
    tilesView = new SnakesAndLaddersBoardView(10, 9, 75, game, snakesAndLadderView);
    winView = new WinWindow();

    setUpGame(players);
    startGame();
  }

  public void setUpGame(List<List<String>> players) {
    for (List<String> player : players) {
      game.addPlayer(player, tilesView);

    }
    for (Dice dice : game.getDices()) {
      dice.addObserver(diceView);
      diceView.initDice(dice);
    }

  }

  public void startGame() {
    snakesAndLadderView.show();
    diceView.show();
    tilesView.show();
  }

  public void playTurn() {
    game.playTurn();
  }
}
