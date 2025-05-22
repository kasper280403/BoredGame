package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.controller.chess.ChessMenuController;
import edu.ntnu.idi.idattx2002.view.common.GameMenuView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * View for the Chess game setup menu.
 * <p>
 * Extends {@code GameMenuView} and initializes UI components for selecting game modes and starting a new game.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
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
