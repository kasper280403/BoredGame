package edu.ntnu.idi.idattx2002.gui.chessGui.controller;

import edu.ntnu.idi.idattx2002.gui.chessGui.view.ChessMenuView;
import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import java.io.IOException;
import java.util.List;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;

public class MainChessController {

  private ChessMenuView mainPane;
  private PositionIO positionIO;
  private ChoosePlayerController choosePlayerController;

  public MainChessController() throws IOException {
    positionIO = new PositionIO();
    this.mainPane = new ChessMenuView(this);
    choosePlayerController = new ChoosePlayerController(mainPane, 2, 2);
    choosePlayerController.showView();
  }

  public Pane getView() {
    return mainPane;
  }

  public List<String> getStartPositions() {
    return positionIO.getAllStartPositionEndPaths();
  }

  public void startGame() throws IOException {
    Chess chess = new Chess();
    chess.addHumanPlayers(choosePlayerController.getPlayerList());

    String positionPath = mainPane.getGameModeSelection().getValue();
    chess.initPosition(positionPath);

    ChessController chessController = new ChessController(mainPane, chess);

    mainPane.getChildren().clear();
    chessController.showView();
  }
}
