package edu.ntnu.idi.idattx2002.gui.common.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.iq80.snappy.Main;

public class MainView extends StackPane {

  private StackPane contentPane;

  public MainView() {
    init();
  }

  public Pane getContentPane() {
    return contentPane;
  }

  private void setBackground() {
    setBackground(new Background(new BackgroundFill(Color.web("#4C82A8"), null, null)));

    Image backgroundImage = new Image("background4.jpg");
    ImageView backgroundImageView = new ImageView(backgroundImage);

    backgroundImageView.setFitWidth(getWidth());
    backgroundImageView.setFitHeight(getHeight());
    backgroundImageView.setPreserveRatio(false);

    backgroundImageView.fitWidthProperty().bind(widthProperty());
    backgroundImageView.fitHeightProperty().bind(heightProperty());

    backgroundImageView.setOpacity(0.4);

    getChildren().add(backgroundImageView);
  }

  private void initContentPane() {
    contentPane = new StackPane();
    getChildren().add(contentPane);
  }

  private void init() {
    setBackground();
    initContentPane();
  }
}
