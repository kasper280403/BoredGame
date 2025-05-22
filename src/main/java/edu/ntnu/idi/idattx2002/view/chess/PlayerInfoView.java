package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.view.common.PlayerIconWindow;
import edu.ntnu.idi.idattx2002.module.common.Player.Player;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayerInfoView extends HBox {

  private final Player player;

  public PlayerInfoView(Player player) {
    this.player = player;
    init();
  }

  private Pane createIconBox() {
    PlayerIconWindow iconWindow = new PlayerIconWindow(10);
    ImageView icon = iconWindow.getImageView(player.getIconId());

    Rectangle backGround = new Rectangle(10, 10);
    backGround.setFill(Color.WHITESMOKE);

    Pane iconBox = new StackPane();
    iconBox.getChildren().addAll(backGround, icon);

    return iconBox;
  }

  private Text createNameText() {
    Text nameText = new Text(player.getName());
    nameText.setFont(new Font( "Arial", 5));

    return nameText;
  }

  private void init() {
    setAlignment(Pos.CENTER_LEFT);
    setSpacing(20);

    getChildren().addAll(createIconBox(), createNameText());
  }


}
