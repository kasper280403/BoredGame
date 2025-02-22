package edu.ntnu.idi.idattx2002.view;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceWindow {

  public static HashMap<Integer, ImageView> imageViewMap = new HashMap<>();

  public static void createPieces(double imageViewSize) {
    Image pieceImage1 = new Image("/images/pieces/frogPiece.png");
    Image pieceImage2 = new Image("/images/pieces/catPiece.png");

    ImageView imageView1 = new ImageView(pieceImage1);
    ImageView imageView2 = new ImageView(pieceImage2);

    setImageSize(imageViewSize, imageView1);
    setImageSize(imageViewSize, imageView2);

    imageViewMap.put(1, imageView1);
    imageViewMap.put(2, imageView2);
  }

  public static void setImageSize(double imageViewSize, ImageView imageView) {
    imageView.setFitHeight(imageViewSize);
    imageView.setFitWidth(imageViewSize);
  }

  public static ImageView getImageView(int pieceID) {
    return imageViewMap.get(pieceID);
  }
}

