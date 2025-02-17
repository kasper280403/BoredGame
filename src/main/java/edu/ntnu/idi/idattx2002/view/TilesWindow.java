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
        int tileID = 1;
        boolean leftToRight = true;

        for (int row = xDimensions-1; row >= 0; row--) {

            if (!leftToRight) {
                tileID += yDimensions - 1;
            }

            for (int col = 0; col < yDimensions; col++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);

                tileMap.put(tileID, tile);

                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.LIGHTGRAY);
                } else {
                    tile.setFill(Color.DARKGRAY);
                }

                tile.setStroke(Color.BLACK);
                Text tileText = new Text(""+tileID);
                StackPane tilePane = new StackPane();
                tilePane.getChildren().addAll(tile, tileText);

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


    public static void changeTileColor(int tileID, Color color) {
        Rectangle tile = tileMap.get(tileID);
        if (tile != null) {
            tile.setFill(color);
        }
    }
}

