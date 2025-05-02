package edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller;

import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.gui.common.view.GameMenuView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.view.SnakesAndLaddersMenuView;
import java.io.IOException;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersMenuController {

  private SnakesAndLaddersMenuView menuView;
  private ChoosePlayerController choosePlayerController;

  public SnakesAndLaddersMenuController(Pane mainPane) throws IOException {
    menuView = new SnakesAndLaddersMenuView(this, mainPane);
    choosePlayerController = new ChoosePlayerController(menuView.getMiddleBox(), 2, 6);
    choosePlayerController.showView();
  }

  public GameMenuView getView() {
    return menuView;
  }

  public void startGame(Pane mainPane) throws IOException {
    int amountOfPlayers = choosePlayerController.getChosenPlayers().size();

    if(amountOfPlayers >= choosePlayerController.getMinPlayers() && amountOfPlayers <= choosePlayerController.getMaxPlayers()) {
      new SnakesAndLaddersController(mainPane, choosePlayerController.getChosenPlayers());
    }
  }

}
