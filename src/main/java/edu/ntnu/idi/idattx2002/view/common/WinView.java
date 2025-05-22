package edu.ntnu.idi.idattx2002.view.common;

import edu.ntnu.idi.idattx2002.module.common.WinObserver;
import edu.ntnu.idi.idattx2002.module.common.player.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * View for displaying the game winner.
 *
 * <p>Implements {@code WinObserver} to respond to win events and show a victory message with the
 * player's name.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class WinView extends VBox implements WinObserver {

  private final Pane mainPane;

  public WinView(Pane mainPane) {
    this.mainPane = mainPane;
    init();
  }

  private Text createPlayerText(Player player) {
    Text username = new Text(player.getName());
    username.setFont(new Font("Helvetica", 80));
    username.setFill(Color.YELLOW);

    return username;
  }

  private Text createWinText() {
    Text winText = new Text("THE WINNER IS:");
    winText.setFont(new Font("Helvetica", 80));
    winText.setFill(Color.PURPLE);

    return winText;
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(50);

    getChildren().add(createWinText());
  }

  private void show() {
    mainPane.getChildren().add(this);
    setOnMouseClicked(e -> mainPane.getChildren().remove(this));
  }

  @Override
  public void update(Player player) {
    getChildren().add(createPlayerText(player));
    show();
  }
}
