package edu.ntnu.idi.idattx2002.gui.chessGui.controller;

import java.io.IOException;
import java.util.List;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui.chessGui.view.MainView;
import edu.ntnu.idi.idattx2002.io.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic.chessLogic.Chess;

public class MainController {

  private MainView mainPane;
  private PositionIO positionIO;

  public MainController() throws IOException {
    positionIO = new PositionIO();
    this.mainPane = new MainView(this);
  }

  public Pane getView() {
    return mainPane;
  }

  public List<String> getStartPositions() {
    return positionIO.getAllStartPositionEndPaths();
  }

  public void startGame() throws IOException {
    Chess chess = new Chess();

    String positionPath = mainPane.getGameModeSelection().getValue();
    chess.initPosition(positionPath);

    ChessController chessController = new ChessController(mainPane, chess);

    mainPane.getChildren().clear();
    chessController.showView();
  }
}
