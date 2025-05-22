package edu.ntnu.idi.idattx2002.view.ladderGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LadderView {

  private String standardPath;

  public LadderView() {
    standardPath = "ladderGameResources/images/tiles/";
  }

  public ImageView getPortalEntrance(double tileSize) {
    return createPortalImage(standardPath + "portalEntrance.png", tileSize);
  }

  public ImageView getPortalExit(double tileSize) {
    return createPortalImage(standardPath + "portalExit.png", tileSize);
  }

  public ImageView getSwitchWithRandom(double tileSize) {
    return createPortalImage(standardPath + "switchWithRandom.png", tileSize);
  }

  private ImageView createPortalImage(String imageLocation, double tileSize) {
    Image portalImage = new Image(imageLocation);
    ImageView portalView = new ImageView(portalImage);
    portalView.setFitHeight(tileSize);
    portalView.setFitWidth(tileSize);
    return portalView;
  }
}
