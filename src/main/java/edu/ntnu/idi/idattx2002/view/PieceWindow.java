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

    imageView1.setFitHeight(imageViewSize);
    imageView1.setFitWidth(imageViewSize);
    imageView2.setFitHeight(imageViewSize);
    imageView2.setFitWidth(imageViewSize);

    imageViewMap.put(1, imageView1);
    imageViewMap.put(2, imageView2);
  }

  public static ImageView getImageView(int pieceID) {
    return imageViewMap.get(pieceID);
  }
}

