package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.gui.common.controller.StartMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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

  private VBox createStartChessBtn() {
    VBox btn = new VBox();
    btn.setMaxSize(500, 500);
    btn.setSpacing(10);
    btn.setAlignment(Pos.CENTER);
    btn.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(5))));

    Text header = new Text("CHESS");
    header.setFont(new Font("Helvetica", 50));
    header.setFill(Color.WHITESMOKE);

    Image sNlImage = new Image(getClass().getResource("/chessResources/chessLogo.png").toExternalForm());
    ImageView sNlImageView = new ImageView(sNlImage);
    sNlImageView.setFitWidth(100);
    sNlImageView.setFitHeight(100);

    btn.getChildren().addAll(header, sNlImageView);
    btn.setOnMouseClicked(e -> controller.openChessMenu());

    return btn;
  }

  private VBox createStartSnakesAndLAddersBtn() {
    VBox btn = new VBox();
    btn.setMaxSize(500, 500);
    btn.setSpacing(10);
    btn.setAlignment(Pos.CENTER);
    btn.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(5))));

    Text header = new Text("SNAKES & LADDERS");
    header.setFont(new Font("Helvetica", 50));
    header.setFill(Color.WHITESMOKE);

    Image sNlImage = new Image(getClass().getResource("/ladderGameResources/images/snakesAndLaddersLogo.png").toExternalForm());
    ImageView sNlImageView = new ImageView(sNlImage);
    sNlImageView.setFitWidth(100);
    sNlImageView.setFitHeight(100);

    btn.getChildren().addAll(header, sNlImageView);
    btn.setOnMouseClicked(e -> controller.openSnakesAndLaddersMenu());

    return btn;
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
    setSpacing(50);

    getChildren().addAll(createTitle(150), createSelectGamePane());
  }

  public void show() {
    mainPane.getChildren().clear();
    mainPane.getChildren().add(this);
  }
}
