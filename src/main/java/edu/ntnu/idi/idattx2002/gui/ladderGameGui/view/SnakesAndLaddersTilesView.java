package edu.ntnu.idi.idattx2002.gui.ladderGameGui.view;
import edu.ntnu.idi.idattx2002.gui.common.view.PlayerIconWindow;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions.LadderAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.Actions.SwitchWithRandomAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersBoard;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.LandAction;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Board.SnakesAndLaddersSquare;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Games.SnakesAndLadders;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.PlayerObserver;
import edu.ntnu.idi.idattx2002.logic.ladderGameLogic.Player.SnakesAndLaddersPlayer;
import java.util.ArrayList;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.HashMap;

public class SnakesAndLaddersTilesView extends GridPane implements PlayerObserver {

    Pane parent;
    SnakesAndLadders game;
    double tileSize;

    private Map<Integer, StackPane> tileMap;
    private LadderView ladderView;
    private PlayerIconWindow playerIconWindow;

    int xDimensions;
    int yDimensions;

    public SnakesAndLaddersTilesView(int xDimensions, int yDimensions, double tileSize, SnakesAndLadders game, Pane parent) {
        this.parent = parent;

        tileMap = new HashMap<>();
        ladderView = new LadderView();
        playerIconWindow = new PlayerIconWindow(tileSize);

        this.game = game;
        this.tileSize = tileSize;

        this.xDimensions = xDimensions;
        this.yDimensions = yDimensions;

        init();
    }

    private StackPane getTilePane(Color color, double tileSize, int tileID) {
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

    private Color getTileColor(int row, int col) {
        return (row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY;
    }

    private int tileIDUpdateAtColChange(boolean leftToRight) {
        return leftToRight ? 1 : -1;
    }

    public void displayPieceAtTile(int tileID, int pieceID) {
        PauseTransition pause = new PauseTransition(Duration.millis(2400));


        StackPane tilePane = tileMap.get(tileID);
        ImageView pieceView = playerIconWindow.getImageView(pieceID);
        pause.setOnFinished(event -> {
            // Fjern fra tidligere tile hvis den har en parent
            if (pieceView.getParent() instanceof StackPane oldTile) {
                oldTile.getChildren().remove(pieceView);
            }
            tilePane.getChildren().add(pieceView);
        });

        pause.play();
    }

    //TODO should be refactored
    private void displayLandActionsAtTile(double tileSize, SnakesAndLadders game) {
        SnakesAndLaddersBoard board = game.getBoard();
        SnakesAndLaddersSquare tile;
        LandAction landAction;

        ArrayList<Color> colorList = getColorList();
        int colorInt = 0;

        for (Integer tileID : tileMap.keySet()) {
            tile = board.getTile(tileID);

            if (tile.hasLandAction()) {
                landAction = tile.getLandAction();
                if(landAction instanceof LadderAction) {
                    LadderAction ladderAction = (LadderAction) landAction;

                    int destinationTileID  = ladderAction.getDestinationTileId();
                    tileMap.get(tileID).getChildren().add(ladderView.getPortalEntrance(tileSize));
                    tileMap.get(destinationTileID).getChildren().add(ladderView.getPortalExit(tileSize));

                    changeTileColor(tileID, colorList.get(colorInt));
                    changeTileColor(destinationTileID, colorList.get(colorInt));
                    colorInt ++;
                }
                if(landAction instanceof SwitchWithRandomAction) {
                    tileMap.get(tileID).getChildren().add(ladderView.getSwitchWithRandom(tileSize));
                }
            }
        }
    }

    private ArrayList<Color> getColorList(){
        ArrayList<Color> colorList = new ArrayList<>();

        colorList.add(Color.web("#7F00FF"));
        colorList.add(Color.web("#FF007F"));
        colorList.add(Color.web("#33FF99"));
        colorList.add(Color.web("#0000FF"));
        colorList.add(Color.web("#FF0000"));
        colorList.add(Color.web("#00FF00"));
        colorList.add(Color.web("#FF7F00"));
        colorList.add(Color.web("#3399FF"));
        colorList.add(Color.web("#FFFF00"));
        colorList.add(Color.web("#7FFF00"));
        colorList.add(Color.web("#00FFFF"));
        colorList.add(Color.web("#FF00FF"));

        return colorList;
    }

    public void changeTileColor(int tileID, Color color) {
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

    @Override
    public void update(SnakesAndLaddersPlayer player) {
        displayPieceAtTile(player.getCurrentTileId(), player.getIconId());
    }

    public void init() {
        int tileID = 1;
        boolean leftToRight = true;

        for (int row = xDimensions - 1; row >= 0; row--) {

            if (!leftToRight) {
                tileID += yDimensions - 1;
            }

            for (int col = 0; col < yDimensions; col++) {

                StackPane tilePane = getTilePane(getTileColor(row, col), tileSize, tileID);
                add(tilePane, col, row);
                tileMap.put(tileID, tilePane);

                tileID += tileIDUpdateAtColChange(leftToRight);
            }

            leftToRight = !leftToRight;
            if (leftToRight) {
                tileID += yDimensions + 1;
            }
        }
        displayLandActionsAtTile(tileSize, game);
    }

    public void show() {
        parent.getChildren().add(this);
    }
}
