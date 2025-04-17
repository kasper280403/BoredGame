package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.gui.common.controller.GameMenuController;
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

  private ComboBox<String> gameModeSelection;
  private Pane contentBox;

  public GameMenuView() {
    init();
  }

  public ComboBox<String> getGameModeSelection() {
    return gameModeSelection;
  }

  public void createTitle(String title) {
    StackPane titlePane = new StackPane();

    Text mainText = new Text(title);
    mainText.setFont(new Font("Helvetica", 150));
    mainText.setFill(Color.INDIANRED);

    Text accentText = new Text(title);
    accentText.setFont(new Font("Helvetica", 150));
    accentText.setFill(Color.WHITESMOKE);

    titlePane.getChildren().addAll(mainText, accentText);
    accentText.setTranslateY(-10);

    getChildren().add(titlePane);
  }

  public void createGameModeSelection(List<String> gameModes) {
    gameModeSelection = new ComboBox<>();

    for (String gameMode : gameModes) {
      gameModeSelection.getItems().add(gameMode);
    }
    contentBox.getChildren().add(gameModeSelection);
  }

  private void init() {
    contentBox = new HBox();
    setBackground(new Background(new BackgroundFill(Color.ROSYBROWN.darker().darker(), null, null)));
    setAlignment(Pos.CENTER);
    setSpacing(20);
  }

}
