package edu.ntnu.idi.idattx2002.gui.controller;

import edu.ntnu.idi.idattx2002.logic.Dice.Dice;
import edu.ntnu.idi.idattx2002.logic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.gui.view.DiceWindow;
import edu.ntnu.idi.idattx2002.gui.view.PieceWindow;
import edu.ntnu.idi.idattx2002.gui.view.SnakesAndLadderWindow;
import edu.ntnu.idi.idattx2002.gui.view.TilesWindow;
import edu.ntnu.idi.idattx2002.gui.view.Win.WinWindow;
import java.util.List;
import javafx.stage.Stage;

public class SnakesAndLaddersController {

  private SnakesAndLadders game;

  //Maybe move
  private SnakesAndLadderWindow snakesAndLadderWindow;
  private PieceWindow pieceView;
  private DiceWindow diceView;
  private TilesWindow tilesView;
  private WinWindow winView;


  public SnakesAndLaddersController(Stage mainStage, List<String> players, List<Integer> pieces) {
    //Maybe move
    snakesAndLadderWindow = new SnakesAndLadderWindow(mainStage, this);
    diceView = new DiceWindow(snakesAndLadderWindow);
    game = new SnakesAndLadders(diceView);

    diceView = new DiceWindow(snakesAndLadderWindow);
    pieceView = new PieceWindow();
    tilesView = new TilesWindow(10, 9, 50, game, snakesAndLadderWindow);
    winView = new WinWindow();

    setUpGame(players, pieces);
    startGame();
  }

  public void setUpGame(List<String> players, List<Integer> pieces) {
    for (int i = 0; i < players.size(); i++) {
      game.addPlayer(players.get(i), pieces.get(i), tilesView);

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
