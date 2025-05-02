package edu.ntnu.idi.idattx2002.gui.chessGui.view;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessMenuController;
import edu.ntnu.idi.idattx2002.gui.common.view.GameMenuView;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ChessMenuView extends GameMenuView {

  private ChessMenuController controller;

  public ChessMenuView(ChessMenuController chessMenuController, Pane mainPane) {
    super(mainPane);
    controller = chessMenuController;
    init();
  }

  private void createStartButton() {
    Button startButton = new Button("START");
    startButton.setMinSize(80, 40);
    startButton.setMaxSize(80, 40);
    startButton.setOnAction(e -> {
      try {
        controller.startGame(mainPane);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    bottomBox.getChildren().add(startButton);
  }

  @Override
  public void init() {
    super.init();
    createTitle("CHESS", 150);
    createGameModeSelection(controller.getStartPositions());
    createStartButton();
  }

}
