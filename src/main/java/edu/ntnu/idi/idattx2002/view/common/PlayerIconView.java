package edu.ntnu.idi.idattx2002.view.common;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility class for loading and managing player icon images.
 *
 * <p>Provides a mapping from icon IDs to {@code ImageView} objects, used for displaying player
 * pieces.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class PlayerIconView {

  public Map<Integer, ImageView> imageViewMap;

  public PlayerIconView(double imageSize) {
    imageViewMap = new HashMap<>();
    createPieces(imageSize);
  }

  public ImageView getImageView(int pieceID) {
    return imageViewMap.get(pieceID);
  }

  private void createPieces(double imageViewSize) {
    String standardPath = "ladderGameResources/images/pieces/";
    Image pieceImage1 = new Image(standardPath + "frogPiece.png");
    Image pieceImage2 = new Image(standardPath + "catPiece.png");
    Image pieceImage3 = new Image(standardPath + "punkPiece.png");
    Image pieceImage4 = new Image(standardPath + "filipPiece.png");

    ImageView imageView1 = new ImageView(pieceImage1);
    ImageView imageView2 = new ImageView(pieceImage2);
    ImageView imageView3 = new ImageView(pieceImage3);
    ImageView imageView4 = new ImageView(pieceImage4);

    setImageSize(imageViewSize, imageView1);
    setImageSize(imageViewSize, imageView2);
    setImageSize(imageViewSize, imageView3);
    setImageSize(imageViewSize, imageView4);

    imageViewMap.put(1, imageView1);
    imageViewMap.put(2, imageView2);
    imageViewMap.put(3, imageView3);
    imageViewMap.put(4, imageView4);
  }

  private void setImageSize(double imageViewSize, ImageView imageView) {
    imageView.setFitHeight(imageViewSize);
    imageView.setFitWidth(imageViewSize);
  }
}
