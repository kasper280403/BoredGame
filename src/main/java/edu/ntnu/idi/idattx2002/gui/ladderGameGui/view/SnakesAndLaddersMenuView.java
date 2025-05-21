package edu.ntnu.idi.idattx2002.gui.ladderGameGui.view;

import edu.ntnu.idi.idattx2002.gui.common.view.GameMenuView;
import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersMenuController;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SnakesAndLaddersMenuView extends GameMenuView {

  private SnakesAndLaddersMenuController controller;

  public SnakesAndLaddersMenuView(SnakesAndLaddersMenuController controller, Pane mainPane) {
    super(mainPane);
    this.controller = controller;
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
    createTitle("SNAKES & LADDERS", 120);
    createGameModeSelectionPane(controller.getStartPositions());
    createStartButton();
  }

  public static String getGameMode(){
    return gameModeSelection.getValue();
  }

}
