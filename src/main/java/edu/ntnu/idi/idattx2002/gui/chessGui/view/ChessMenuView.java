package edu.ntnu.idi.idattx2002.gui.chessGui.view;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.MainChessController;
import edu.ntnu.idi.idattx2002.gui.common.view.GameMenuView;
import java.io.IOException;
import javafx.scene.control.Button;

public class ChessMenuView extends GameMenuView {

  private MainChessController controller;

  public ChessMenuView(MainChessController mainChessController) {
    controller = mainChessController;
    init();
  }

  private void createStartButton() {
    Button startButton = new Button("START");
    startButton.setMinSize(80, 40);
    startButton.setMaxSize(80, 40);
    startButton.setOnAction(e -> {
      try {
        controller.startGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    bottomBox.getChildren().add(startButton);
  }

  @Override
  public void init() {
    super.init();
    createTitle("CHESS");
    createGameModeSelection(controller.getStartPositions());
    createStartButton();
  }

}
