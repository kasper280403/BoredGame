package edu.ntnu.idi.idattx2002.view.common;

import edu.ntnu.idi.idattx2002.controller.common.StartMenuController;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
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

/**
 * View for the application's start menu.
 *
 * <p>Displays the game selection screen where users can choose between Chess and Snakes & Ladders.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class StartMenuView extends VBox {

  private final StartMenuController controller;
  private final Pane mainPane;

  public StartMenuView(StartMenuController controller, Pane mainPane) {
    this.controller = controller;
    this.mainPane = mainPane;

    init();
  }

  private StackPane createTitle(int size) {
    StackPane titlePane = new StackPane();

    Text mainText = new Text("BORED GAMES");
    mainText.setFont(new Font("Helvetica", size));
    mainText.setFill(Color.web("#F4C400"));
    mainText.setEffect(new Glow(0.5));

    Text accentText = new Text("BORED GAMES");
    accentText.setFont(new Font("Helvetica", size));
    accentText.setFill(Color.web("#A83232"));
    accentText.setEffect(new GaussianBlur(5));

    titlePane.getChildren().addAll(accentText, mainText);
    accentText.setTranslateY(size / 20);

    return titlePane;
  }

  private VBox createStartChessBtn() {
    VBox btn = new VBox();
    btn.setMaxSize(340, 370);
    btn.setMinSize(340, 370);
    btn.setSpacing(15);
    btn.setAlignment(Pos.CENTER);
    Color backgroundColor = Color.WHITE.deriveColor(0, 1, 1, 0.2);
    btn.setBackground(
        new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

    Text header = new Text("Chess");
    header.setFont(new Font("Helvetica", 35));
    header.setFill(Color.WHITESMOKE);

    Image sNlImage =
        new Image(getClass().getResource("/chessResources/chessLogo.png").toExternalForm());
    ImageView sNlImageView = new ImageView(sNlImage);

    sNlImageView.setFitWidth(290);
    sNlImageView.setFitHeight(290);

    btn.getChildren().addAll(header, sNlImageView);
    btn.setOnMouseClicked(e -> controller.openChessMenu());

    return btn;
  }

  private VBox createStartSnakesAndLAddersBtn() {
    VBox btn = new VBox();
    btn.setMaxSize(340, 370);
    btn.setMinSize(340, 370);
    btn.setSpacing(15);
    btn.setAlignment(Pos.CENTER);
    Color backgroundColor = Color.WHITE.deriveColor(0, 1, 1, 0.2);
    btn.setBackground(
        new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

    Text header = new Text("Snakes & Ladders");
    header.setFont(new Font("Helvetica", 35));
    header.setFill(Color.WHITESMOKE);

    Image sNlImage =
        new Image(
            getClass()
                .getResource("/ladderGameResources/images/snakesAndLaddersLogo.png")
                .toExternalForm());
    ImageView sNlImageView = new ImageView(sNlImage);

    sNlImageView.setFitWidth(290);
    sNlImageView.setFitHeight(290);

    btn.getChildren().addAll(header, sNlImageView);
    btn.setOnMouseClicked(e -> controller.openSnakesAndLaddersMenu());

    return btn;
  }

  private HBox createSelectGamePane() {
    HBox selectGamePane = new HBox();
    selectGamePane.setSpacing(50);
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
