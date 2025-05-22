package edu.ntnu.idi.idattx2002.view.ladderGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility class for loading and creating visual representations of special tiles in Snakes and Ladders.
 * <p>
 * Provides {@code ImageView} objects for elements like portals and switch tiles, scaled to fit tile size.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
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
