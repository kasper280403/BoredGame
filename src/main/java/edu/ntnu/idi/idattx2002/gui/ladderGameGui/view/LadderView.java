package edu.ntnu.idi.idattx2002.gui.ladderGameGui.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LadderView {

  public LadderView() {
  }

  public ImageView getPortalEntrance(double tileSize) {
    return createPortalImage("images/tiles/portalEntrance.png", tileSize);
  }

  public ImageView getPortalExit(double tileSize) {
    return createPortalImage("images/tiles/portalExit.png", tileSize);
  }

  public ImageView getSwitchWithRandom(double tileSize) {
    return createPortalImage("images/tiles/switchWithRandom.png", tileSize);
  }

  private ImageView createPortalImage(String imageLocation, double tileSize) {
    Image portalImage = new Image(imageLocation);
    ImageView portalView = new ImageView(portalImage);
    portalView.setFitHeight(tileSize);
    portalView.setFitWidth(tileSize);
    return portalView;
  }
}
