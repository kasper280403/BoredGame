package edu.ntnu.idi.idattx2002.view.chess;

import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.module.chess.board.SquareObserver;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Controller for the Snakes and Ladders game setup menu.
 *
 * <p>Handles player selection, game mode loading, and game initialization.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class BoardView extends GridPane implements SquareObserver {

  private final PieceView pieceView;
  private Pane parent;

  private Map<ChessSquare, Pane> tileMap;
  private int tilesize;

  public BoardView(Pane parent) {
    this.parent = parent;

    tileMap = new HashMap<>();
    tilesize = 100;
    pieceView = new PieceView(tilesize);

    init();
  }

  public Map<ChessSquare, Pane> getTileMap() {
    return tileMap;
  }

  public void createSquares(ChessColor colorPerspective, Chess chess) {
    int x;
    int y;
    int xAdjustment;
    int yAdjustment;

    if (colorPerspective == ChessColor.WHITE) {
      xAdjustment = 0;
      yAdjustment = -9;
    } else {
      xAdjustment = -9;
      yAdjustment = 0;
    }

    for (ChessSquare square : chess.getBoard().getSquareMap().values()) {
      square.setObserver(this);
      Rectangle tile = new Rectangle(tilesize, tilesize);
      tile.setFill(getSquareColor(square));

      x = Math.abs(square.getXCoordinate() + xAdjustment);
      y = Math.abs((square.getYCoordinate() + yAdjustment));

      Pane squarePane = new StackPane();
      squarePane.getChildren().add(tile);

      if (square.hasPiece()) {
        squarePane.getChildren().add((pieceView.getPieceView(square.getPiece())));
      }

      add(squarePane, x, y);
      tileMap.put(square, squarePane);
    }
  }

  public void refreshAllTiles() {
    for (ChessSquare square : tileMap.keySet()) {
      refreshTile(tileMap.get(square), square);
    }
  }

  public void refreshTile(Pane tile, ChessSquare square) {
    tile.getChildren().clear();

    Rectangle background = new Rectangle(tilesize, tilesize);
    background.setFill(getSquareColor(square));
    tile.getChildren().add(background);

    if (square.hasPiece()) {
      tile.getChildren().add(pieceView.getPieceView(square.getPiece()));
    }
  }

  private Color getSquareColor(ChessSquare square) {
    if ((square.getXCoordinate() + square.getYCoordinate()) % 2 != 0) {
      return Color.web("#D4EAF7");
    } else {
      return Color.web("#6A8BA4");
    }
  }

  public void highlightTile(Pane tile) {
    Rectangle highlight = new Rectangle(tilesize, tilesize);
    highlight.setFill(Color.MEDIUMVIOLETRED);
    highlight.setOpacity(0.3);

    tile.getChildren().add(highlight);
  }

  public void refresh(ChessColor colorPerspective, Chess chess) {
    getChildren().clear();
    createSquares(colorPerspective, chess);
  }

  public void update(ChessSquare square) {
    refreshTile(tileMap.get(square), square);
  }

  public void init() {
    setAlignment(Pos.CENTER);
  }

  public void show() {
    parent.getChildren().add(this);
  }
}
