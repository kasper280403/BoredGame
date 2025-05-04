package edu.ntnu.idi.idattx2002.gui.common.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.iq80.snappy.Main;

public class MainView extends StackPane {

  public MainView() {
    init();
  }

  private void init() {
    setBackground(new Background(new BackgroundFill(Color.web("#3A4C77"), null, null)));
  }
}
