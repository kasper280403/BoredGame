package edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller;

import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.DiceWindow;
import edu.ntnu.idi.idattx2002.gui.common.view.PlayerIconWindow;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLadderWindow;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersTilesWindow;
import edu.ntnu.idi.idattx2002.gui.common.view.WinWindow;
import java.util.List;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersController {

  private SnakesAndLadders game;

  //Maybe move
  private SnakesAndLadderWindow snakesAndLadderWindow;
  private PlayerIconWindow pieceView;
  private DiceWindow diceView;
  private SnakesAndLaddersTilesWindow tilesView;
  private WinWindow winView;


  public SnakesAndLaddersController(Pane mainPane, List<List<String>> players) {
    //Maybe move
    snakesAndLadderWindow = new SnakesAndLadderWindow(mainPane, this);
    diceView = new DiceWindow(snakesAndLadderWindow);
    game = new SnakesAndLadders(diceView);

    diceView = new DiceWindow(snakesAndLadderWindow);
    pieceView = new PlayerIconWindow(50);
    tilesView = new SnakesAndLaddersTilesWindow(10, 9, 50, game, snakesAndLadderWindow);
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
    double size = 50;

    pieceView.createPieces(size);
    diceView.show();
    tilesView.show();
  }

  public void playTurn() {
    game.playTurn();
  }

}
