package edu.ntnu.idi.idattx2002.view;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;


public class TilesWindow{
    private static final HashMap<Integer, Rectangle> tileMap = new HashMap<>();

    public static GridPane getBoard(int xDimensions, int yDimensions){

        GridPane gridPane = new GridPane();
        double tileSize = 50;
        int tileID =  xDimensions * yDimensions - 1;
        boolean isDirectionNumbersRight = yDimensions % 2 == 0;

        for (int row = 0; row < xDimensions; row++) {
            if (isDirectionNumbersRight) {
                tileID -= xDimensions;
            }

            for (int col = 0; col < yDimensions; col++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);

                if (isDirectionNumbersRight) {
                    tileID += 1;
                } else {
                    tileID -= 1;
                }

                tileMap.put(tileID, tile);

                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.LIGHTGRAY);
                } else {
                    tile.setFill(Color.DARKGRAY);
                }

                tile.setStroke(Color.BLACK);
                Text tileText = new Text(""+tileID);
                tileText.setFont(Font.font(10)); // Setter skriftstørrelse
                tileText.setFill(Color.BLACK);
                StackPane tilePane = new StackPane();
                tilePane.getChildren().addAll(tile, tileText);


                gridPane.add(tilePane, col, row);
            }
            if (isDirectionNumbersRight) {
                tileID -= xDimensions;
                isDirectionNumbersRight = false;
            } else {
                isDirectionNumbersRight = true;
                tileID -= 2;
            }
        }

        return gridPane;
    }


    public static void changeTileColor(int tileID, Color color) {
        Rectangle tile = tileMap.get(tileID);
        if (tile != null) {
            tile.setFill(color);
        }
    }
}

