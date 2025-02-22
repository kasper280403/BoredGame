package edu.ntnu.idi.idattx2002.view;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;


public class TilesWindow {

    private static final HashMap<Integer, StackPane> tileMap = new HashMap<>();

    public static GridPane getBoard(int xDimensions, int yDimensions) {

        GridPane gridPane = new GridPane();
        double tileSize = 50;
        int tileID = 1;
        boolean leftToRight = true;

        for (int row = xDimensions - 1; row >= 0; row--) {

            if (!leftToRight) {
                tileID += yDimensions - 1;
            }

            for (int col = 0; col < yDimensions; col++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);

                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.LIGHTGRAY);
                } else {
                    tile.setFill(Color.DARKGRAY);
                }
                tile.setStroke(Color.BLACK);

                Text tileText = new Text("" + tileID);
                StackPane tilePane = new StackPane();
                tilePane.getChildren().addAll(tile, tileText);

                tileMap.put(tileID, tilePane);

                gridPane.add(tilePane, col, row);

                if (leftToRight) {
                    tileID++;
                } else {
                    tileID--;
                }
            }
            leftToRight = !leftToRight;
            if (leftToRight) {
                tileID += yDimensions + 1;
            }
        }
        return gridPane;
    }


    //TODO refactor!!
    public static void displayPieceAtTile(int tileID, int pieceID) {
        StackPane tilePane = tileMap.get(tileID);
        if (pieceID == 1) {
            Image pieceImage = new Image("/images/pieces/frogPiece.png");
            ImageView pieceView = new ImageView(pieceImage);
            pieceView.setFitWidth(60);
            pieceView.setFitHeight(60);
            tilePane.getChildren().add(pieceView);
        } else if (pieceID == 2) {
            Image pieceImage = new Image("/images/pieces/catPiece.png");
            ImageView pieceView = new ImageView(pieceImage);
            pieceView.setFitWidth(60);
            pieceView.setFitHeight(60);
            tilePane.getChildren().add(pieceView);
        }

    }

    public static void changeTileColor(int tileID, Color color) {
        StackPane tilePane = tileMap.get(tileID);
        if (tilePane != null) {
            for (Node node : tilePane.getChildren()) {
                if (node instanceof Rectangle tile) {
                    tile.setFill(color);
                    break;
                }
            }
        }
    }
}
