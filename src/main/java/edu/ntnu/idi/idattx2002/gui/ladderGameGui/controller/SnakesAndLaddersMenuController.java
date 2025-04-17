package edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessController;
import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersMenuView;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import java.io.IOException;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersMenuController {

  private SnakesAndLaddersMenuView mainPane;
  private ChoosePlayerController choosePlayerController;

  public SnakesAndLaddersMenuController() throws IOException {
    mainPane = new SnakesAndLaddersMenuView(this);
    choosePlayerController = new ChoosePlayerController(mainPane.getMiddleBox(), 2, 6);
    choosePlayerController.showView();
  }

  public Pane getView() {
    return mainPane;
  }

  public void startGame() throws IOException {
    int amountOfPlayers = choosePlayerController.getChosenPlayers().size();

    if(amountOfPlayers >= choosePlayerController.getMinPlayers() && amountOfPlayers <= choosePlayerController.getMaxPlayers()) {
      new SnakesAndLaddersController(mainPane, choosePlayerController.getChosenPlayers());
    }
  }

}
