package edu.ntnu.idi.idattx2002.gui.chessGui.view;


import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessController;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChessView extends HBox {

  private ChessController chessController;
  private Pane parent;

  public ChessView(Pane parent) throws IOException {
    this.parent = parent;
    init();
  }

  public void showView() {
    parent.getChildren().clear();
    parent.getChildren().add(this);
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(40);

    HBox spacer = new HBox();
    spacer.setMinSize(120, 800);
    spacer.setMaxSize(120, 800);
    getChildren().add(spacer);
  }
}
