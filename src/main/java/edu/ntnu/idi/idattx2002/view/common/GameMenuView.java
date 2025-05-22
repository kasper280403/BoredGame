package edu.ntnu.idi.idattx2002.view.common;

import edu.ntnu.idi.idattx2002.exception.IlliegalGameArgumentException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Abstract base view for game setup menus.
 *
 * <p>Provides common layout structure and UI components for selecting game modes, displaying
 * feedback, and organizing subviews (top, middle, bottom).
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public abstract class GameMenuView extends VBox {

  public final Pane mainPane;

  public ComboBox<String> gameModeSelection;

  public HBox topBox;
  public HBox middleBox;
  public VBox bottomBox;

  public Text userFeedback;

  public GameMenuView(Pane mainPane) {
    this.mainPane = mainPane;
  }

  public Pane getMainPane() {
    return mainPane;
  }

  public Pane getTopBox() {
    return topBox;
  }

  public Pane getMiddleBox() {
    return middleBox;
  }

  public Pane getBottomBox() {
    return bottomBox;
  }

  public void setUserFeedback(String message) {
    userFeedback.setText(message);
  }

  public ComboBox<String> getGameModeSelection() {
    if (gameModeSelection.getValue() == null) {
      throw new IlliegalGameArgumentException("No Gamemode is chosen");
    }
    return gameModeSelection;
  }

  public void createTitle(String title, double size) {
    StackPane titlePane = new StackPane();

    Text mainText = new Text(title);
    mainText.setFont(new Font("Helvetica", size));
    mainText.setFill(Color.web("#F4C400"));
    mainText.setEffect(new Glow(0.5));

    Text accentText = new Text(title);
    accentText.setFont(new Font("Helvetica", size));
    accentText.setFill(Color.web("#A83232"));
    accentText.setEffect(new GaussianBlur(5));

    titlePane.getChildren().addAll(accentText, mainText);
    accentText.setTranslateY(size / 20);

    topBox.getChildren().add(titlePane);
  }

  public void createGameModeSelectionPane(List<String> gameModes) {
    VBox gameModePane = new VBox();
    gameModePane.setSpacing(10);
    gameModePane.setAlignment(Pos.CENTER);
    gameModePane.setPadding(new Insets(20));
    gameModePane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    Color backgroundColor = Color.NAVAJOWHITE.deriveColor(0, 1, 1, 0.2);
    gameModePane.setBackground(
        new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

    initGameModeSelection(gameModes);

    gameModePane.getChildren().addAll(createGameModeText(), gameModeSelection);

    middleBox.getChildren().addAll(gameModePane);
  }

  private Text createGameModeText() {
    Text gameModeText = new Text("Select Gamemode:");
    gameModeText.setFont(new Font("Helvetica", 20));
    gameModeText.setFill(Color.LIGHTGREY);

    return gameModeText;
  }

  private void initGameModeSelection(List<String> gameModes) {
    gameModeSelection = new ComboBox<>();

    for (String gameMode : gameModes) {
      gameModeSelection.getItems().add(gameMode);
    }
  }

  private void initUserFeedbackText() {
    userFeedback = new Text();
    userFeedback.setFont(new Font("Helvetica", 20));
    userFeedback.setFill(Color.WHITE);
  }

  public void init() {
    setAlignment(Pos.CENTER);
    setSpacing(20);

    initUserFeedbackText();

    topBox = new HBox();
    middleBox = new HBox();
    bottomBox = new VBox();

    topBox.setAlignment(Pos.CENTER);
    middleBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);

    middleBox.setSpacing(60);
    bottomBox.setSpacing(20);

    bottomBox.getChildren().add(userFeedback);

    getChildren().addAll(topBox, middleBox, bottomBox);
  }

  public void show() {
    mainPane.getChildren().clear();
    mainPane.getChildren().add(this);
  }
}
