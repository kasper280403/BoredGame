package edu.ntnu.idi.idattx2002.gui1.chessGui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.WinObserver;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.player.HumanPlayer;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.player.Player;

public class WinView implements WinObserver {

  private Stage stage;
  private Text winnerText;

  public WinView() {
    init();
  }

  private void init() {
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Win Window");

    Text header = new Text("Winner");
    header.setFont(new Font("Helvetica", 50));
    header.setFill(Color.WHITESMOKE);

    winnerText = new Text("xxx");
    winnerText.setFont(new Font("Helvetica", 70));
    winnerText.setFill(Color.ORANGERED);

    VBox root = new VBox();
    root.setAlignment(Pos.CENTER);
    root.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN.darker().darker(), null, null)));

    root.getChildren().addAll(header, winnerText);

    Scene scene = new Scene(root, 100, 100);

    stage.setScene(scene);
  }

  public void update(Player player) {
    if (player instanceof HumanPlayer) {
      winnerText.setText(((HumanPlayer) player).getName());
    }
    else {
      winnerText.setText("BOT WINS");
    }

    stage.showAndWait();
  }

}
