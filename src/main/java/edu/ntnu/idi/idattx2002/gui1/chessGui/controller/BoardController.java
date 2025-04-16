package edu.ntnu.idi.idattx2002.gui1.chessGui.controller;

import java.io.IOException;
import java.util.Map;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.gui1.chessGui.view.BoardView;
import edu.ntnu.idi.idattx2002.gui1.chessGui.view.SideBarView;
import edu.ntnu.idi.idattx2002.io1.chessIO.PositionIO;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.Chess;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.ChessColor;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.Move;
import edu.ntnu.idi.idattx2002.logic1.chessLogic.board.Square;

public class BoardController {

  private Chess chess;

  private BoardView boardView;
  private SideBarView sideBarView;
  private Square selectedSquare;
  private Pane selectedTile;

  private ChessColor colorPerspective;
  private boolean autoFlip;


  public BoardController(Chess chess, Pane mainPane) {
    this.chess = chess;
    boardView = new BoardView(chess, mainPane);
    sideBarView = new SideBarView(mainPane, this);
    init();
  }

  public void showView() {
    boardView.show();
    sideBarView.show();
  }

  private void init() {
    colorPerspective = chess.getPlayerToMove().getColor();
    autoFlip = false;

    initClickableSquares();
  }

  private void initClickableSquares() {
    for (Map.Entry<Square, Pane> entry : boardView.getTileMap().entrySet()) {
      Square square = entry.getKey();
      Pane tile = entry.getValue();

      tile.setOnMouseClicked(e -> handleSquareClick(square, tile));
    }
  }

  private void handleSquareClick(Square square, Pane tile) {
    if(selectedSquare == null && selectedTile == null && square.hasPiece()) {
      selectedSquare = square;
      selectedTile = tile;
      boardView.highlightTile(selectedTile);
    }
    else if (selectedSquare != null && selectedTile != null) {
      executeMove(square, tile);
    }
  }

  public void flipBoard() {
    if(!autoFlip) {
      colorPerspective = colorPerspective == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
      boardView.refresh(colorPerspective);
      initClickableSquares();
    }
  }

  public void autoFlipButtonAction(ToggleButton button) {
    if (button.isSelected()) {
      button.setText("On");
      autoFlip = true;
      autoFlip();
    }
    else {
      button.setText("Off");
      autoFlip = false;
    }
  }

  public void revertMove() throws IOException {
    chess.revertMove();
  }

  private void autoFlip() {
    if(autoFlip) {
      boardView.refresh(chess.getPlayerToMove().getColor());
      initClickableSquares();
    }
  }

  public void executeMove(Square square, Pane tile) {
    Move move = new Move(selectedSquare, square, chess);
    chess.playMove(move);

    if(!move.successful()) {
      boardView.refreshTile(selectedTile, selectedSquare);
      boardView.refreshTile(tile, square);
    }

    if(autoFlip) {
      autoFlip();
    }
    selectedTile = null;
    selectedSquare = null;
  }

  public void savePosition() throws IOException {
    PositionIO positionIO = new PositionIO();
    positionIO.savePosition("testSave.txt", chess);
  }
}
