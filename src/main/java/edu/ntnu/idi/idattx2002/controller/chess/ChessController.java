package edu.ntnu.idi.idattx2002.controller.chess;

import edu.ntnu.idi.idattx2002.view.common.WinView;
import javafx.scene.layout.Pane;
import edu.ntnu.idi.idattx2002.view.chess.ChessView;
import edu.ntnu.idi.idattx2002.module.chess.Chess;

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
