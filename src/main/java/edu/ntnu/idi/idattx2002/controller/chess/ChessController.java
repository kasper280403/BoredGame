package edu.ntnu.idi.idattx2002.controller.chess;

import edu.ntnu.idi.idattx2002.view.common.WinView;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.view.chess.ChessView;
import edu.ntnu.idi.idattx2002.module.chess.Chess;

/**
 * Main controller for the chess module.
 * <p>
 * Coordinates the chess board view, sidebar, and win screen. Initializes the board controller and attaches the win observer.
 * </p>
 *
 * @author Sindre Mjøs
 * @version 1.0
 */
public class ChessController {

  private final BoardController boardController;
  private final ChessView chessView;
  private final WinView winView;

  public ChessController(Pane mainPane, Chess chess) {
    chessView = new ChessView(mainPane);
    boardController = new BoardController(chess, chessView);

    winView = new WinView(mainPane);
    chess.addObserver(winView);
  }

  public void showView() {
    chessView.showView();
    boardController.showView();
  }

}
