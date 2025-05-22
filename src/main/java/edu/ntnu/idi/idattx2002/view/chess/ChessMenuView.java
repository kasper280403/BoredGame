package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.controller.chess.ChessMenuController;
import edu.ntnu.idi.idattx2002.view.common.GameMenuView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ChessMenuView extends GameMenuView {

  private final ChessMenuController controller;

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
        controller.startGame(mainPane);
    });

    bottomBox.getChildren().add(startButton);
  }

  @Override
  public void init() {
    super.init();
    createTitle("CHESS", 150);
    createGameModeSelectionPane(controller.getStartPositions());
    createStartButton();
  }

}
