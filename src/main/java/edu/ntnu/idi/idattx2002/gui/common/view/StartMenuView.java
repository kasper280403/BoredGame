package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.gui.common.controller.StartMenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartMenuView extends VBox {

  private StartMenuController controller;
  private Pane mainPane;

  public StartMenuView(StartMenuController controller, Pane mainPane) {
    this.controller = controller;
    this.mainPane = mainPane;

    init();
  }

  private StackPane createTitle(int size) {
    StackPane titlePane = new StackPane();

    Text mainText = new Text("BORED GAMES");
    mainText.setFont(new Font("Helvetica", size));
    mainText.setFill(Color.INDIANRED);

    Text accentText = new Text("BORED GAMES");
    accentText.setFont(new Font("Helvetica", size));
    accentText.setFill(Color.WHITESMOKE);

    titlePane.getChildren().addAll(mainText, accentText);
    accentText.setTranslateY(-10);

    return titlePane;
  }

  private Button createStartChessBtn() {
    Button startBtn = new Button("CHESS");
    startBtn.setOnAction(e -> controller.openChessMenu());

    return startBtn;
  }

  private Button createStartSnakesAndLAddersBtn() {
    Button startBtn = new Button("SNAKES & LADDERS");
    startBtn.setOnAction(e -> controller.openSnakesAndLaddersMenu());

    return startBtn;
  }

  private HBox createSelectGamePane() {
    HBox selectGamePane = new HBox();
    selectGamePane.setSpacing(20);
    selectGamePane.setAlignment(Pos.CENTER);

    selectGamePane.getChildren().addAll(createStartChessBtn(), createStartSnakesAndLAddersBtn());
    return selectGamePane;
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(10);

    getChildren().addAll(createTitle(150), createSelectGamePane());
  }

  public void show() {
    mainPane.getChildren().clear();
    mainPane.getChildren().add(this);
  }
}
