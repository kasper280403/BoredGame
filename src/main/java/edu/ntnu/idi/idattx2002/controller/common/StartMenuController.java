package edu.ntnu.idi.idattx2002.controller.common;

import edu.ntnu.idi.idattx2002.controller.chess.ChessMenuController;
import edu.ntnu.idi.idattx2002.view.common.MainView;
import edu.ntnu.idi.idattx2002.view.common.StartMenuView;
import edu.ntnu.idi.idattx2002.controller.ladderGame.SnakesAndLaddersMenuController;

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
    ChessMenuController mainController = new ChessMenuController(mainPane.getContentPane());
    mainController.getView().show();
  }

  public void openSnakesAndLaddersMenu() {
    SnakesAndLaddersMenuController mainController = new SnakesAndLaddersMenuController(mainPane.getContentPane());
    mainController.getView().show();
  }

}
