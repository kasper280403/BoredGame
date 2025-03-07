package edu.ntnu.idi.idattx2002.view;
import edu.ntnu.idi.idattx2002.Modules.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Board;
import edu.ntnu.idi.idattx2002.Modules.Board.LandAction;
import edu.ntnu.idi.idattx2002.Modules.Board.Tile;
import edu.ntnu.idi.idattx2002.Modules.Games.SnakesAndLadders;
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

    public static GridPane getBoard(int xDimensions, int yDimensions, double tileSize, SnakesAndLadders game) {

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
        displayLandActionsAtTile(tileSize, game);
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

    private static void displayLandActionsAtTile(double tileSize, SnakesAndLadders game) {
        Board board = game.getBoard();
        Tile tile;
        LandAction landAction;
        for (Integer tileID : tileMap.keySet()) {
            tile = board.getTile(tileID);
            if (tile.hasLandAction()){
                landAction = tile.getLandAction();
                if(landAction instanceof LadderAction) {
                    LadderAction ladderAction = (LadderAction) landAction;
                    if(ladderAction.getDestinationTileId() < tileID) {
                        tileMap.get(tileID).getChildren().addAll(LadderView.getRedPortal(tileSize));
                    }
                    else if(ladderAction.getDestinationTileId() > tileID) {
                        tileMap.get(tileID).getChildren().addAll(LadderView.getGreenPortal(tileSize));
                    }
                }
            }
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
