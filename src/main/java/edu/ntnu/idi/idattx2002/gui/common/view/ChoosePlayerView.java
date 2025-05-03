package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.gui.common.controller.ChoosePlayerController;
import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChoosePlayerView extends HBox {

  private Pane mainPane;
  private ChoosePlayerController controller;
  private VBox choosePlayerPane;

  public ChoosePlayerView(Pane mainPane, ChoosePlayerController controller) throws IOException {
    this.controller = controller;
    this.mainPane = mainPane;

    init();
  }

  private Pane createMakePlayerBtn() {
    VBox btn = new VBox();
    btn.setMaxSize(320, 160);
    btn.setMinSize(320, 160);
    btn.setSpacing(15);
    btn.setAlignment(Pos.CENTER);
    Color backgroundColor = Color.NAVAJOWHITE.deriveColor(0, 1, 1, 0.2);
    btn.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(10), null)));

    Text header = new Text("Make A New Player");
    header.setFont(new Font("Helvetica", 20));
    header.setFill(Color.LIGHTGREY);

    PlayerIconWindow playerIconView = new PlayerIconWindow(60);
    HBox playerIconImages = new HBox();
    playerIconImages.setSpacing(3);
    playerIconImages.setAlignment(Pos.CENTER);

    playerIconImages.getChildren().addAll(playerIconView.getImageView(1),
        playerIconView.getImageView(2),
        playerIconView.getImageView(3),
        playerIconView.getImageView(4)
    );

    btn.getChildren().addAll(header, playerIconImages);
    btn.setOnMouseClicked(e -> controller.openCreatePlayerWindow());

    return btn;
  }

  public Pane createHeading() {
    VBox headingBox = new VBox();
    headingBox.setAlignment(Pos.CENTER);

    Text minText = new Text("Min Players: " + controller.getMinPlayers());
    Text maxText = new Text("Max Players:" + controller.getMaxPlayers());

    minText.setFont(new Font("Helvetica", 20));
    minText.setFill(Color.LIGHTGREY);
    maxText.setFont(new Font("Helvetica", 20));
    maxText.setFill(Color.LIGHTGREY);

    headingBox.getChildren().addAll(minText, maxText);
    return headingBox;
  }

  public Pane createPlayerList() throws IOException {
    VBox playerList = new VBox();
    playerList.setSpacing(15);

    for (ArrayList<String> player: controller.getPlayerList()) {
      playerList.getChildren().add(createPlayerBox(player));
    }

    return playerList;
  }

  public Pane createPlayerBox(ArrayList<String> player) {
    HBox playerBox = new HBox();
    playerBox.setSpacing(15);

    Text playerInfo = new Text(playerToString(player));
    playerInfo.setFont(new Font("Helvetica", 17));
    playerInfo.setFill(Color.WHITESMOKE);

    playerBox.getChildren().addAll(playerInfo, createSelectPlayerBtn(player));

    return playerBox;
  }

  public Button createSelectPlayerBtn(ArrayList<String> player) {
    Button button = new Button("Unselected");
    button.setBackground(Background.fill(Color.RED));
    button.setTextFill(Color.WHITESMOKE);

    button.setOnAction(e -> controller.handlePlayerBtnClick(player, button));

    return button;
  }

  //TODO should this be in controller maybe?
  public String playerToString(ArrayList<String> player) {
    return player.get(0) + " (Piece " + player.get(1) + ")";
  }

  public void show() {
    mainPane.getChildren().add(this);
  }

  public void init() throws IOException {
    choosePlayerPane = new VBox();

    double opacity = 0.2;
    Color backgroundColor = Color.NAVAJOWHITE.deriveColor(0, 1, 1, opacity);
    choosePlayerPane.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));

    choosePlayerPane.setPadding(new Insets(20));
    choosePlayerPane.setAlignment(Pos.CENTER);
    choosePlayerPane.setSpacing(25);

    choosePlayerPane.getChildren().addAll(createHeading(), createPlayerList());

    setAlignment(Pos.CENTER);
    setSpacing(50);
    getChildren().addAll(choosePlayerPane, createMakePlayerBtn());
  }
}
