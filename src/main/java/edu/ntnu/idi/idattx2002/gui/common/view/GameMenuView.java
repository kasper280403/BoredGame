package edu.ntnu.idi.idattx2002.gui.common.view;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class GameMenuView extends VBox{

  public Pane mainPane;

  private ComboBox<String> gameModeSelection;
  private Pane contentBox;

  public HBox topBox;
  public HBox middleBox;
  public HBox bottomBox;

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
    mainText.setFill(Color.INDIANRED);

    Text accentText = new Text(title);
    accentText.setFont(new Font("Helvetica", size));
    accentText.setFill(Color.WHITESMOKE);

    titlePane.getChildren().addAll(mainText, accentText);
    accentText.setTranslateY(-10);

    topBox.getChildren().add(titlePane);
  }

  public void createGameModeSelection(List<String> gameModes) {
    gameModeSelection = new ComboBox<>();

    for (String gameMode : gameModes) {
      gameModeSelection.getItems().add(gameMode);
    }
    middleBox.getChildren().add(gameModeSelection);
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
