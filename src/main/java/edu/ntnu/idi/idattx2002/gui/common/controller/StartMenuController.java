package edu.ntnu.idi.idattx2002.gui.common.controller;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessMenuController;
import edu.ntnu.idi.idattx2002.gui.common.view.StartMenuView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersMenuController;
import java.io.IOException;
import javafx.scene.layout.Pane;

public final class StartMenuController {

  private final StartMenuView view;
  private final Pane mainPane;

  public StartMenuController(Pane mainPane) {
    this.mainPane = mainPane;
    view = new StartMenuView(this, mainPane);
  }

  public StartMenuView getView() {
    return view;
  }

  public void openChessMenu() {
    try {
      ChessMenuController mainController = new ChessMenuController(mainPane);
      mainController.getView().show();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void openSnakesAndLaddersMenu() {
    try {
      SnakesAndLaddersMenuController mainController = new SnakesAndLaddersMenuController(mainPane);
      mainController.getView().show();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

}
