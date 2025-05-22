package edu.ntnu.idi.idattx2002.view.chess;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Layout container for the main Chess game view.
 * <p>
 * Extends {@code HBox} to structure the board and sidebar components side by side, with spacing and alignment.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class ChessView extends HBox {

  private final Pane parent;

  public ChessView(Pane parent) {
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
