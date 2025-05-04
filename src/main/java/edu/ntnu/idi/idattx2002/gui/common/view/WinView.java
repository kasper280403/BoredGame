package edu.ntnu.idi.idattx2002.gui.common.view;

import edu.ntnu.idi.idattx2002.logic.common.WinObserver;
import edu.ntnu.idi.idattx2002.logic.common.Player.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WinView extends VBox implements WinObserver {

  private Pane mainPane;

  public WinView(Pane mainPane) {
    this.mainPane = mainPane;
    init();
  }


  private Text createPlayerText(Player player) {
    Text username = new Text(player.getName());
    username.setFont(new Font ("Helvetica", 80));

    return username;
  }

  private Text createWinText() {
    Text winText = new Text("THE WINNER IS:");
    winText.setFont(new Font("Helvetica", 80));

    return winText;
  }

  private void init() {
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
