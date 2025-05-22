package edu.ntnu.idi.idattx2002.controller.ladderGame;

import edu.ntnu.idi.idattx2002.controller.common.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.exception.GameException;
import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.io.ladderGame.BoardIO;
import edu.ntnu.idi.idattx2002.view.common.GameMenuView;
import edu.ntnu.idi.idattx2002.view.ladderGame.SnakesAndLaddersMenuView;
import java.util.List;
import javafx.scene.layout.Pane;

/**
 * Controller for the Snakes and Ladders game setup menu.
 *
 * <p>Handles player selection, game mode loading, and game initialization.
 *
 * @author Sindre Mjøs and Kasper Karlsen
 * @version 1.0
 */
public class LadderGameMenuController {

  private final SnakesAndLaddersMenuView menuView;
  private final ChoosePlayerController choosePlayerController;

  public LadderGameMenuController(Pane mainPane) {
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
        new LadderGameController(
            mainPane,
            choosePlayerController.getChosenPlayers(),
            menuView.getGameModeSelection().getValue());
      } else {
        throw new IlliegalGameArgumentException("Wrong number of chosen players");
      }
    } catch (GameException e) {
      menuView.setUserFeedback(e.getMessage());
    }
  }
}
