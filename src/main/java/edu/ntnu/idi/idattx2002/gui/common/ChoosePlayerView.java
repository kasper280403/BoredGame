package edu.ntnu.idi.idattx2002.gui.common;

import edu.ntnu.idi.idattx2002.gui.ladderGameGui.controller.SnakesAndLaddersController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ChoosePlayerView extends VBox {

  private Pane mainPane;
  private ChoosePlayerController controller;
  private Button startBtn;

  public ChoosePlayerView(Pane mainPane, ChoosePlayerController controller) throws IOException {
    this.controller = controller;
    this.mainPane = mainPane;

    init();
    show();
  }

  public Button getStartBtn() {
    return startBtn;
  }

  public Pane createHeading() {
    Pane headingBox = new VBox();

    Text minText = new Text("Min Players: " + controller.getMinPlayers());
    Text maxText = new Text("Max Players:" + controller.getMaxPlayers());

    headingBox.getChildren().addAll(minText, maxText);
    return headingBox;
  }

  public Pane createPlayerList() throws IOException {
    Pane playerList = new VBox();

    for (ArrayList<String> player: controller.getPlayerList()) {
      playerList.getChildren().add(createPlayerBox(player));
    }

    return playerList;
  }

  public Pane createPlayerBox(ArrayList<String> player) {
    Pane playerBox = new HBox();

    Text playerInfo = new Text(playerToString(player));

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

  public String playerToString(ArrayList<String> player) {
    return player.get(0) + " (Piece " + player.get(1) + ")";
  }

  public Button createStartBtn() {
    startBtn = new Button("Start Game");

    startBtn.setOnAction(e -> controller.startGame());

    startBtn.setDisable(true);

    return startBtn;
  }

  public void show() {
    mainPane.getChildren().add(this);
  }

  public void init() throws IOException {
    setAlignment(Pos.CENTER);
    setSpacing(20);

    getChildren().addAll(createHeading(), createPlayerList(), createStartBtn());
  }

}
