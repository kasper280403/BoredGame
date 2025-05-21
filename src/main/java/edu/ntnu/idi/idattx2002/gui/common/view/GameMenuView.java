package edu.ntnu.idi.idattx2002.gui.common.view;

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

public abstract class GameMenuView extends VBox{

  private final Pane mainPane;

  private ComboBox<String> gameModeSelection;

  private HBox topBox;
  private HBox middleBox;
  private HBox bottomBox;

  public GameMenuView(Pane mainPane) {
    this.mainPane = mainPane;
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

  public ComboBox<String> getGameModeSelection() {
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
    accentText.setTranslateY(size/20);

    topBox.getChildren().add(titlePane);
  }

  public void createGameModeSelectionPane(List<String> gameModes) {
    VBox gameModePane = new VBox();
    gameModePane.setSpacing(10);
    gameModePane.setAlignment(Pos.CENTER);
    gameModePane.setPadding(new Insets(20));
    gameModePane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    Color backgroundColor = Color.NAVAJOWHITE.deriveColor(0, 1, 1, 0.2);
    gameModePane.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

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

  public void init() {
    setAlignment(Pos.CENTER);
    setSpacing(20);

    topBox = new HBox();
    middleBox = new HBox();
    bottomBox = new HBox();

    topBox.setAlignment(Pos.CENTER);
    middleBox.setAlignment(Pos.CENTER);
    bottomBox.setAlignment(Pos.CENTER);

    middleBox.setSpacing(60);

    getChildren().addAll(topBox, middleBox, bottomBox);
  }

  public void show() {
    mainPane.getChildren().clear();
    mainPane.getChildren().add(this);
  }


}
