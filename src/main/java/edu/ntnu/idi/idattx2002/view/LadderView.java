package edu.ntnu.idi.idattx2002.view;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LadderView {



  public static ImageView getPortalEntrance(double tileSize) {
    return createPortalImage("images/tiles/portalEntrance.png", tileSize);
  }

  public static ImageView getPortalExit(double tileSize) {
    return createPortalImage("images/tiles/portalExit.png", tileSize);
  }

  public static ImageView getSwitchWithRandom(double tileSize) {
    return createPortalImage("images/tiles/switchWithRandom.png", tileSize);
  }

  private static ImageView createPortalImage(String imageLocation, double tileSize) {
    Image portalImage = new Image(imageLocation);
    ImageView portalView = new ImageView(portalImage);
    portalView.setFitHeight(tileSize);
    portalView.setFitWidth(tileSize);
    return portalView;
  }
}
