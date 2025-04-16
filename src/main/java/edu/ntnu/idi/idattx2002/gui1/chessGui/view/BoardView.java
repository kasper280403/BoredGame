package edu.ntnu.idi.idattx2002.gui1.chessGui.view;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.Square;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.SquareObserver;

public class BoardView extends GridPane implements SquareObserver {

  private PieceView pieceView;
  private Chess chess;
  private Pane parent;

  private Map<Square, Pane> tileMap;
  private int tilesize;

  public BoardView(Chess chess, Pane parent) {
    this.chess = chess;
    this.parent = parent;

    tileMap = new HashMap<>();
    tilesize = 100;
    pieceView = new PieceView(tilesize);

    init();
  }

  public Map<Square, Pane> getTileMap() {
    return tileMap;
  }

  private void createSquares(ChessColor colorPerspective) {
    int x;
    int y;
    int xAdjustment;
    int yAdjustment;

    if(colorPerspective == ChessColor.WHITE) {
      xAdjustment = 0;
      yAdjustment = -9;
    }
    else {
      xAdjustment = -9;
      yAdjustment = 0;
    }

    for(Square square : chess.getBoard().getSquareMap().values()) {
      square.setObserver(this);
      Rectangle tile = new Rectangle(tilesize, tilesize);
      tile.setFill(getSquareColor(square));

      x = Math.abs(square.getXCoordinate() + xAdjustment);
      y = Math.abs((square.getYCoordinate() + yAdjustment));

      Pane squarePane = new StackPane();
      squarePane.getChildren().add(tile);

      if(square.hasPiece()) {
        squarePane.getChildren().add((pieceView.getPieceView(square.getPiece())));
      }

      add(squarePane, x, y);
      tileMap.put(square, squarePane);
    }
  }

  public void refreshAllTiles() {
    for(Square square : tileMap.keySet()) {
      refreshTile(tileMap.get(square), square);
    }
  }

  public void refreshTile(Pane tile, Square square) {
    tile.getChildren().clear();

    Rectangle background = new Rectangle(tilesize, tilesize);
    background.setFill(getSquareColor(square));
    tile.getChildren().add(background);

    if(square.hasPiece()) {
      tile.getChildren().add(pieceView.getPieceView(square.getPiece()));
    }
  }

  private Color getSquareColor(Square square) {
    if((square.getXCoordinate() + square.getYCoordinate()) % 2 != 0) {
      return Color.WHITESMOKE;
    }
    else{
      return Color.INDIANRED;
    }
  }

  public void highlightTile(Pane tile) {
    Rectangle highlight = new Rectangle(tilesize, tilesize);
    highlight.setFill(Color.MEDIUMVIOLETRED);
    highlight.setOpacity(0.3);

    tile.getChildren().add(highlight);
  }

  public void refresh(ChessColor colorPerspective) {
    getChildren().clear();
    createSquares(colorPerspective);
  }

  public void update(Square square) {
    refreshTile(tileMap.get(square), square);
  }

  public void init() {
    setAlignment(Pos.CENTER);
    createSquares(ChessColor.WHITE);
  }

  public void show() {
    parent.getChildren().add(this);
  }
}
