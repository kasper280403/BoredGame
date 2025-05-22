package edu.ntnu.idi.idattx2002.gui.chessGui.controller;

import edu.ntnu.idi.idattx2002.exception.GameException;
import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import edu.ntnu.idi.idattx2002.exception.IlliegalMoveException;
import edu.ntnu.idi.idattx2002.gui.chessGui.view.ChessMenuView;
import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import edu.ntnu.idi.idattx2002.gui.common.view.GameMenuView;
import java.io.IOException;
import java.util.List;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;

public class ChessMenuController {

  private final ChessMenuView chessMenuView;
  private final PositionIO positionIO;
  private final ChoosePlayerController choosePlayerController;

  public ChessMenuController(Pane mainPane) {
    positionIO = new PositionIO();
    this.chessMenuView = new ChessMenuView(this, mainPane);
    choosePlayerController = new ChoosePlayerController(chessMenuView.getMiddleBox(), 2, 2);
    choosePlayerController.showView();
  }

  public ChessMenuView getView() {
    return chessMenuView;
  }

  public List<String> getStartPositions() {
    return positionIO.getAllStartPositionEndPaths();
  }

  public void startGame(Pane mainPane) {
    int amountOfPlayers = choosePlayerController.getChosenPlayers().size();

    try {
      if (amountOfPlayers >= choosePlayerController.getMinPlayers()
          && amountOfPlayers <= choosePlayerController.getMaxPlayers()) {
        Chess chess = new Chess();
        chess.addHumanPlayers(choosePlayerController.getChosenPlayers());

        String positionPath = chessMenuView.getGameModeSelection().getValue();
        chess.initPosition(positionPath);

        ChessController chessController = new ChessController(mainPane, chess);

        chessController.showView();
      }
      else {
        throw new IlliegalGameArgumentException("Wrong number of chosen players");
      }
    } catch(GameException e) {
      chessMenuView.setUserFeedback(e.getMessage());
    }
  }
}
