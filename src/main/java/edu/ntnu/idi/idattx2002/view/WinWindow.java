package edu.ntnu.idi.idattx2002.view;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinWindow {

  public static void createStage() {
    Stage winStage = new Stage();
    winStage.initModality(Modality.APPLICATION_MODAL);
    winStage.setTitle("WIN");

    setSize(500, winStage);
    setPosition(500, 250, winStage);

    winStage.setScene(createScene(3));

    winStage.show();
  }

  private static Scene createScene(int pieceID) {
    return new Scene(createVbox(pieceID), 300, 300);
  }

  private static VBox createVbox(int pieceID) {
    VBox vBox = new VBox(10);

    ImageView winPiece = createPieces(250).get(pieceID);

    vBox.getChildren().addAll(winPiece);

    return vBox;
  }

  private static HashMap<Integer, ImageView> createPieces(int imageViewSize) {
    HashMap<Integer, ImageView> pieceMap = new HashMap<>();
    pieceMap.put(1, createPiece(imageViewSize, "/images/pieces/frogPiece.png"));
    pieceMap.put(2, createPiece(imageViewSize, "/images/pieces/catPiece.png"));
    pieceMap.put(3, createPiece(imageViewSize, "/images/pieces/punkPiece.png"));
    pieceMap.put(4, createPiece(imageViewSize, "/images/pieces/filipPiece.png"));
    return pieceMap;
  }

  private static ImageView createPiece(int imageViewSize, String imageLocation) {
    Image pieceImage = new Image(imageLocation, imageViewSize, imageViewSize, true, false);
    ImageView imageView = new ImageView(pieceImage);

    return imageView;
  }

  private static void setSize(int size, Stage stage){
    stage.setWidth(size);
    stage.setHeight(size);
  }

  private static void setPosition(int xPosition, int yPosition, Stage stage) {
    stage.setX(xPosition); // X position in pixels from the left edge of the screen
    stage.setY(yPosition); // Y position in pixels from the top of the screen

  }

}
