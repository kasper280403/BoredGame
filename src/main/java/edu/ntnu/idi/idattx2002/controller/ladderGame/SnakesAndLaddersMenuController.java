package edu.ntnu.idi.idattx2002.controller.ladderGame;

import edu.ntnu.idi.idattx2002.exception.GameException;
import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.controller.common.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.view.common.GameMenuView;
import edu.ntnu.idi.idattx2002.view.ladderGame.SnakesAndLaddersMenuView;
import java.util.List;

import edu.ntnu.idi.idattx2002.io.ladderGame.BoardIO;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersMenuController {

  private final SnakesAndLaddersMenuView menuView;
  private final ChoosePlayerController choosePlayerController;

  public SnakesAndLaddersMenuController(Pane mainPane) {
    menuView = new SnakesAndLaddersMenuView(this, mainPane);
    choosePlayerController = new ChoosePlayerController(menuView.getMiddleBox(), 2, 6);
    choosePlayerController.showView();
  }

  public List<String> getStartPositions() {
    BoardIO boardIO = new BoardIO(null);
    return boardIO.getBoards();
  }

  public GameMenuView getView() {
    return menuView;
  }

  public void startGame(Pane mainPane) {
    int amountOfPlayers = choosePlayerController.getChosenPlayers().size();

    try {
      if (amountOfPlayers >= choosePlayerController.getMinPlayers()
          && amountOfPlayers <= choosePlayerController.getMaxPlayers()) {
        new SnakesAndLaddersController(mainPane, choosePlayerController.getChosenPlayers(),
            menuView.getGameModeSelection().getValue());
      }
      else {
        throw new IlliegalGameArgumentException("Wrong number of chosen players");
      }
    } catch (GameException e) {
      menuView.setUserFeedback(e.getMessage());
    }
  }

}
