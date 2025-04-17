package edu.ntnu.idi.idattx2002.gui.chessGui.view;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import edu.ntnu.idi.idattx2002.gui.chessGui.controller.MainChessController;

public class MainView extends StackPane {

  private MainChessController mainController;

  private ComboBox<String> gameModeSelection;

  public MainView(MainChessController mainController) {
    this.mainController = mainController;
    init();
  }

  public ComboBox<String> getGameModeSelection() {
    return gameModeSelection;
  }

  private void createGameModeSelection() {
    gameModeSelection = new ComboBox<>();

    for (String gameMode : mainController.getStartPositions()) {
      gameModeSelection.getItems().add(gameMode);
    }
  }

  private StackPane createTitle() {
    StackPane titlePane = new StackPane();

    Text mainText = new Text("CHESS");
    mainText.setFont(new Font("Helvetica", 150));
    mainText.setFill(Color.INDIANRED);

    Text accentText = new Text("CHESS");
    accentText.setFont(new Font("Helvetica", 150));
    accentText.setFill(Color.WHITESMOKE);

    titlePane.getChildren().addAll(mainText, accentText);
    accentText.setTranslateY(-10);

    return titlePane;
  }

  private Button createStartButton() {
    Button startButton = new Button("START");
    startButton.setMinSize(80, 40);
    startButton.setMaxSize(80, 40);
    startButton.setOnAction(e -> {
      try {
        mainController.startGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    return startButton;
  }

  public void init() {
    setBackground(new Background(new BackgroundFill(Color.ROSYBROWN.darker().darker(), null, null)));

    createGameModeSelection();

    VBox mainMenuBox = new VBox();
    mainMenuBox.setAlignment(Pos.CENTER);
    mainMenuBox.setSpacing(20);
    mainMenuBox.getChildren().addAll(createTitle(), gameModeSelection, createStartButton());

    getChildren().add(mainMenuBox);
  }
}
