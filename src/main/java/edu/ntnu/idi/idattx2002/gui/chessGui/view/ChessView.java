package edu.ntnu.idi.idattx2002.gui.chessGui.view;


import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui.chessGui.controller.ChessController;

public class ChessView extends HBox {

  private ChessController chessController;
  private Pane parent;

  public ChessView(Pane parent) throws IOException {
    this.parent = parent;
    init();
  }

  public void showView() {
    HBox spacer = new HBox();
    spacer.setMinSize(120, 800);
    spacer.setMaxSize(120, 800);
    getChildren().add(spacer);

    parent.getChildren().add(this);
  }

  private void init() {
    setAlignment(Pos.CENTER);
    setSpacing(40);
  }
}
