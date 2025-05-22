package edu.ntnu.idi.idattx2002.controller.chess;

import edu.ntnu.idi.idattx2002.exception.IllegalMoveException;
import edu.ntnu.idi.idattx2002.module.chess.Chess;
import edu.ntnu.idi.idattx2002.module.chess.ChessColor;
import edu.ntnu.idi.idattx2002.module.chess.Move;
import edu.ntnu.idi.idattx2002.module.chess.board.ChessSquare;
import edu.ntnu.idi.idattx2002.view.chess.BoardView;
import edu.ntnu.idi.idattx2002.view.chess.SideBarView;
import java.util.Map;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

/**
 * Controller class for handling interaction and logic on the chess board view.
 *
 * <p>Manages user input, board updates, and move execution in coordination with the model and view.
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class BoardController {

  private final Chess chess;

  private final BoardView boardView;
  private final SideBarView sideBarView;

  private ChessSquare selectedSquare;
  private Pane selectedTile;

  private ChessColor colorPerspective;
  private boolean autoFlip;

  public BoardController(Chess chess, Pane mainPane) {
    this.chess = chess;

    colorPerspective = chess.getPlayerToMove().getColor();

    boardView = new BoardView(mainPane);
    boardView.createSquares(colorPerspective, chess);

    sideBarView = new SideBarView(mainPane, this);

    init();
  }

  private void initClickableSquares() {
    for (Map.Entry<ChessSquare, Pane> entry : boardView.getTileMap().entrySet()) {
      ChessSquare square = entry.getKey();
      Pane tile = entry.getValue();

      tile.setOnMouseClicked(e -> handleSquareClick(square, tile));
    }
  }

  private void handleSquareClick(ChessSquare square, Pane tile) {
    if (selectedSquare == null && selectedTile == null && square.hasPiece()) {
      selectedSquare = square;
      selectedTile = tile;
      boardView.highlightTile(selectedTile);
    } else if (selectedSquare != null && selectedTile != null) {
      executeMove(square, tile);
    }
  }

  public void flipBoard() {
    if (!autoFlip) {
      colorPerspective = colorPerspective == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE;
      boardView.refresh(colorPerspective, chess);
      initClickableSquares();
    }
  }

  public void autoFlipButtonAction(ToggleButton button) {
    if (button.isSelected()) {
      button.setText("On");
      autoFlip = true;
      autoFlip();
    } else {
      button.setText("Off");
      autoFlip = false;
    }
  }

  private void autoFlip() {
    if (autoFlip) {
      boardView.refresh(chess.getPlayerToMove().getColor(), chess);
      initClickableSquares();
    }
  }

  private void executeMove(ChessSquare square, Pane tile) {
    Move move = new Move(selectedSquare, square, chess);

    try {
      chess.playMove(move);
    } catch (IllegalMoveException e) {
      sideBarView.setUserExceptionFeedback(e.getMessage());
    }

    if (!move.successful()) {
      boardView.refreshTile(selectedTile, selectedSquare);
      boardView.refreshTile(tile, square);
    } else {
      sideBarView.setUserFeedback(
          "To Move: "
              + chess.getPlayerToMove().getName()
              + " ("
              + chess.getPlayerToMove().getColor()
              + ")");
    }

    if (autoFlip) {
      autoFlip();
    }
    selectedTile = null;
    selectedSquare = null;
  }

  public void showView() {
    boardView.show();
    sideBarView.show();
  }

  private void init() {
    autoFlip = false;

    initClickableSquares();
  }
}
