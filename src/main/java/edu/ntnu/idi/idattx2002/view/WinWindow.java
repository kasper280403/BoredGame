package edu.ntnu.idi.idattx2002.view;

import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinWindow {


  //TODO add buttons for replay and exit
  public static void createStage(int pieceID, String name) {

    Stage winStage = new Stage();
    winStage.initModality(Modality.APPLICATION_MODAL);
    winStage.setTitle("WIN");

    setSize(500, winStage);
    setPosition(500, 250, winStage);

    winStage.setScene(createScene(pieceID, name));

    winStage.show();
  }

  private static Scene createScene(int pieceID, String name) {
    return new Scene(createVbox(pieceID, name), 300, 300);
  }

  private static VBox createVbox(int pieceID, String name) {
    VBox vBox = new VBox(30);
    vBox.setAlignment(Pos.CENTER);

    vBox.getChildren().addAll(createWinText(name), getPiece(200, pieceID));

    return vBox;
  }

  private static Text createWinText(String name) {
    Text text = new Text("WUUUUHUUUUU!\n" + name + " Won!!!");

    text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
    text.setFill(Color.ORANGE);
    text.setStroke(Color.BLACK);

    return text;
  }

  private static ImageView getPiece(int imageViewSize, int pieceID){
    return createPieces(imageViewSize).get(pieceID);
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
