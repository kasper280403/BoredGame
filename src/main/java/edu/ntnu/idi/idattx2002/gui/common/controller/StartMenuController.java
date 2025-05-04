package edu.ntnu.idi.idattx2002.gui.common.controller;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessMenuController;
import edu.ntnu.idi.idattx2002.gui.common.view.MainView;
import edu.ntnu.idi.idattx2002.gui.common.view.StartMenuView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersMenuController;

public final class StartMenuController {

  private final StartMenuView view;
  private final MainView mainPane;

  public StartMenuController() {
    mainPane = new MainView(this);
    view = new StartMenuView(this, mainPane.getContentPane());

    MusicController musicController = new MusicController(mainPane);
    musicController.getView().show();

    view.show();
  }

  public StartMenuView getStartMenuView() {
    return view;
  }

  public MainView getMainPane() {
    return mainPane;
  }

  public void openChessMenu() {
    try {
      ChessMenuController mainController = new ChessMenuController(mainPane.getContentPane());
      mainController.getView().show();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void openSnakesAndLaddersMenu() {
    try {
      SnakesAndLaddersMenuController mainController = new SnakesAndLaddersMenuController(mainPane.getContentPane());
      mainController.getView().show();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

}
