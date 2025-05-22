package edu.ntnu.idi.idattx2002.view.ladderGame;

import edu.ntnu.idi.idattx2002.controller.ladderGame.LadderGameMenuController;
import edu.ntnu.idi.idattx2002.view.common.GameMenuView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Menu view for setting up a Snakes and Ladders game.
 *
 * <p>Allows players to select a game mode and start the game. Extends {@code GameMenuView}.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class SnakesAndLaddersMenuView extends GameMenuView {

  private final LadderGameMenuController controller;

  public SnakesAndLaddersMenuView(LadderGameMenuController controller, Pane mainPane) {
    super(mainPane);
    this.controller = controller;
    init();
  }

  private void createStartButton() {
    Button startButton = new Button("START");
    startButton.setMinSize(80, 40);
    startButton.setMaxSize(80, 40);
    startButton.setOnAction(
        e -> {
          controller.startGame(mainPane);
        });
    bottomBox.getChildren().add(startButton);
  }

  @Override
  public void init() {
    super.init();
    createTitle("SNAKES & LADDERS", 120);
    createGameModeSelectionPane(controller.getStartPositions());
    createStartButton();
  }
}
