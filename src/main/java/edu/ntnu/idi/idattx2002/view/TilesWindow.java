package edu.ntnu.idi.idattx2002.view;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.HashMap;

public class TilesWindow {

    private static final HashMap<Integer, StackPane> tileMap = new HashMap<>();

    public static GridPane getBoard(int xDimensions, int yDimensions, double tileSize) {

        GridPane gridPane = new GridPane();
        int tileID = 1;
        boolean leftToRight = true;

        for (int row = xDimensions - 1; row >= 0; row--) {

            if (!leftToRight) {
                tileID += yDimensions - 1;
            }

            for (int col = 0; col < yDimensions; col++) {

                StackPane tilePane = getTilePane(getTileColor(row, col), tileSize, tileID);
                gridPane.add(tilePane, col, row);
                tileMap.put(tileID, tilePane);

                tileID += tileIDUpdateAtColChange(leftToRight);
            }

            leftToRight = !leftToRight;
            if (leftToRight) {
                tileID += yDimensions + 1;
            }
        }
        return gridPane;
    }

    private static StackPane getTilePane(Color color, double tileSize, int tileID) {
        StackPane tilePane = new StackPane();
        tilePane.setPrefSize(tileSize, tileSize);
        tilePane.setMaxSize(tileSize, tileSize);
        tilePane.setMinSize(tileSize, tileSize);

        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setFill(color);
        tile.setStroke(Color.BLACK);

        Text tileText = new Text("" + tileID);

        tilePane.getChildren().addAll(tile, tileText);
        return tilePane;
    }

    private static Color getTileColor(int row, int col) {
        return (row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY;
    }

    private static int tileIDUpdateAtColChange(boolean leftToRight) {
        return leftToRight ? 1 : -1;
    }

    public static void displayPieceAtTile(int tileID, int pieceID) {
        PauseTransition pause = new PauseTransition(Duration.millis(2400));


        StackPane tilePane = tileMap.get(tileID);
        ImageView pieceView = PieceWindow.getImageView(pieceID);
        pause.setOnFinished(event -> {
            // Fjern fra tidligere tile hvis den har en parent
            if (pieceView.getParent() instanceof StackPane oldTile) {
                oldTile.getChildren().remove(pieceView);
            }
            tilePane.getChildren().add(pieceView);
        });

        pause.play();
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
