package edu.ntnu.idi.idattx2002.controller.common;

import edu.ntnu.idi.idattx2002.controller.chess.ChessMenuController;
import edu.ntnu.idi.idattx2002.controller.ladderGame.LadderGameMenuController;
import edu.ntnu.idi.idattx2002.view.common.MainView;
import edu.ntnu.idi.idattx2002.view.common.StartMenuView;

/**
 * Controller for the start menu of the application.
 *
 * <p>Initializes the main view, music, and navigation to game-specific menus such as Chess and
 * Snakes & Ladders.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
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
    LadderGameMenuController mainController =
        new LadderGameMenuController(mainPane.getContentPane());
    mainController.getView().show();
  }
}
