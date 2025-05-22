package edu.ntnu.idi.idattx2002.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameException extends RuntimeException {

  public GameException(String massage) {
    super(massage);

    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Game Error");
    alert.setHeaderText(null);
    alert.setContentText(this.getMessage());
    alert.showAndWait();
  }
}
