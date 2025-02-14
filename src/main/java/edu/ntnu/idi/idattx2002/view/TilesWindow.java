package edu.ntnu.idi.idattx2002.view;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


public class TilesWindow{
    private static final HashMap<Integer, Rectangle> tileMap = new HashMap<>();

    public static GridPane getBoard(int xDimensions, int yDimensions){

        GridPane gridPane = new GridPane();
        double tileSize = 50;
        int tileID = 0;

        for (int row = 0; row < xDimensions; row++) {
            for (int col = 0; col < yDimensions; col++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);

                tileID += 1;
                tileMap.put(tileID, tile);

                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.LIGHTGRAY);
                } else {
                    tile.setFill(Color.DARKGRAY);
                }

                tile.setStroke(Color.BLACK);
                gridPane.add(tile, col, row);
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

